package com.foxconn.sw.macaddress.service.impl;

import com.alibaba.fastjson.JSON;
import com.foxconn.sw.macaddress.common.Constant;
import com.foxconn.sw.macaddress.common.ListUtil;
import com.foxconn.sw.macaddress.common.Result;
import com.foxconn.sw.macaddress.common.RetResponse;
import com.foxconn.sw.macaddress.dao.ApplicationDao;
import com.foxconn.sw.macaddress.dao.DeliveryRecordDao;
import com.foxconn.sw.macaddress.dao.MacaddressDao;
import com.foxconn.sw.macaddress.dto.DeliveryRecordDTO;
import com.foxconn.sw.macaddress.entity.Application;
import com.foxconn.sw.macaddress.entity.DeliveryRecord;
import com.foxconn.sw.macaddress.entity.Macaddress;
import com.foxconn.sw.macaddress.service.DeliveryRecordService;
import com.foxconn.sw.macaddress.vo.ApplicationVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * mac地址交付记录，此表代表申请提交后从mac地址表中分配的记录，若存在跨段(申请数量大于mac地址表该段可用数量)，则同一个mac_id生成多个记录(DeliveryRecord)表服务实现类
 *
 * @author makejava
 * @since 2020-10-21 17:24:24
 */
@Log4j2
@Service("deliveryRecordService")
public class DeliveryRecordServiceImpl implements DeliveryRecordService {
    @Resource
    private DeliveryRecordDao deliveryRecordDao;

    @Resource
    MacaddressDao macaddressDao;

    @Resource
    ApplicationDao applicationDao;

    @Resource
    HttpSession httpSession;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DeliveryRecord queryById(Integer id) {
        return this.deliveryRecordDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DeliveryRecord> queryAllByLimit(int offset, int limit) {
        return this.deliveryRecordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param deliveryRecord 实例对象
     * @return 实例对象
     */
    @Override
    public DeliveryRecord insert(DeliveryRecord deliveryRecord) {
        this.deliveryRecordDao.insert(deliveryRecord);
        return deliveryRecord;
    }

    /**
     * 修改数据
     *
     * @param deliveryRecord 实例对象
     * @return 实例对象
     */
    @Override
    public DeliveryRecord update(DeliveryRecord deliveryRecord) {
        this.deliveryRecordDao.update(deliveryRecord);
        return this.queryById(deliveryRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.deliveryRecordDao.deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result assignMac(ApplicationVO applicationVO) {
        ////////////////////////////////////////////////////参数校验非空开始//////////////////////////////////////////////////////
        if (ObjectUtils.isEmpty(applicationVO)) {
            System.out.println("macAddressVO = " + applicationVO);
            log.error("参数{}为空", applicationVO);
            return RetResponse.error("参数为空");
        }

        ////////////////////////////////////////////////////参数校验非空结束//////////////////////////////////////////////////////

        //查询全部库存
        Map<Integer, Integer> startStockMap = getAllStartingMacAddress();

        //查询已用库存
        Map<Integer, Integer> macIdMap = getUsedMacAddressMap();
        ////////////////////////////////////////////////////查询可用库存开始//////////////////////////////////////////////////////
        Map<Integer, Integer> surplusStockMap = new TreeMap<>();
        //每段macId对应的剩余库存
        List<Integer> surplusStockList = new ArrayList<>();
        //macId集合
        List<Integer> macIdList = new ArrayList<>();
        //构造剩余库存map
        Integer surplusStock = null;
        Integer usedStock = null;
        for (Map.Entry<Integer, Integer> entry : startStockMap.entrySet()) {
            if (macIdMap.containsKey(entry.getKey())) {
                //当前macId对应的已使用库存量
                usedStock = macIdMap.get(entry.getKey());
            } else {
                //当前mac段未使用
                usedStock = 0;
            }
            //剩余库存量
            surplusStock = entry.getValue() - usedStock;
            //剩余库存不为0的macaddress才添加到list和map中，保证数据可再分配
            if (surplusStock != 0) {
                surplusStockMap.put(entry.getKey(), surplusStock);
                //剩余库存对应的集合
                surplusStockList.add(surplusStock);
                macIdList.add(entry.getKey());
            }
        }
        //剩余库存map(macId,库存数)
        //surplusStockMap = {1:1,2:192,25:1048576,26:3000000,27:3000000,28:1777215}
        System.err.println("surplusStockMap = " + JSON.toJSONString(surplusStockMap));
        //剩余库存List(上方map去除key)
        //surplusStockList = [1,192,1048576,3000000,3000000,1777215]
        System.err.println("surplusStockList = " + JSON.toJSONString(surplusStockList));
        ////////////////////////////////////////////////////查询可用库存结束//////////////////////////////////////////////////////

        //申请数量
        Integer amount = applicationVO.getAmount();
        //剩余库存总数
        int shengyuStockSum = ListUtil.sumList(surplusStockList, surplusStockList.size());
        if (amount > shengyuStockSum) {
            log.error("总库存不足");
            return RetResponse.error("总库存不足");
        }
        ArrayList<Integer> valueList = new ArrayList<>();
        ArrayList<Integer> valueSumList = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : surplusStockMap.entrySet()) {
            valueList.add(entry.getValue());
            ListUtil.sumList(valueList, valueList.size());
        }

        for (int i = 0; i < valueList.size(); i++) {
            valueSumList.add(ListUtil.sumList(valueList, i + 1));
        }
        //剩余库存的前n项和集合：[1, 193, 1048769, 4048769, 7048769, 8825984]
        System.out.println("剩余库存的前n项和集合：" + valueSumList);

        List<DeliveryRecord> result = new ArrayList<>();
        //将申请数量添加到前n项和中求索引
        //list = [1, 30, 193, 1048769, 4048769, 7048769, 8825984]
        //list.indexOf(n) = 1
        int importantIndex = ListUtil.index(valueSumList, amount);
        //Integer stockEnough = surplusStockList.get(importantIndex);
        int surplusStockWithoutTail = 0;
        int kuaDuanTail = 0;
        if (importantIndex != 0) {
            surplusStockWithoutTail = ListUtil.sumList(surplusStockList, importantIndex);
            //跨段多出来的
            kuaDuanTail = amount - surplusStockWithoutTail;
        } else {
            surplusStockWithoutTail = ListUtil.sumList(surplusStockList, importantIndex + 1);
            //未跨段
            kuaDuanTail = amount;
        }

        //[1,2,25,26,27,28]
        for (int i = importantIndex; i >= 0; i--) {
            Integer mac = macIdList.get(i);
            DeliveryRecord deliveryRecord = new DeliveryRecord();
            deliveryRecord.setMacId(mac);
            //TODO 待计算
            deliveryRecord.setStartMacAddress("");
            deliveryRecord.setEndMacAddress("");
            deliveryRecord.setApplicationId(applicationVO.getId());
            if (i == importantIndex) {
                deliveryRecord.setAmount(kuaDuanTail);
            } else {
                deliveryRecord.setAmount(surplusStockMap.get(mac));
            }
            deliveryRecord.setCreatedate(new Date());
            deliveryRecord.setCreator(httpSession.getAttribute("LoginState").toString());
            deliveryRecord.setUpdatedate(new Date());
            deliveryRecord.setStatus(1);
            result.add(deliveryRecord);
        }

        System.err.println("分发结果json为 = " + JSON.toJSONString(result));
        try {
            deliveryRecordDao.insertBatch(result);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("分配mac地址失败，原因{}", e.getMessage());
            throw new RuntimeException("分配mac地址失败");
        }
        //减剩余库存
        for (DeliveryRecord deliveryRecord : result) {
            Integer macId = deliveryRecord.getMacId();
            Integer amount1 = deliveryRecord.getAmount();
            Integer mapValue = surplusStockMap.get(macId);
            //调整mac id对应的剩余库存
            int remainingInventory = mapValue - amount1;
            surplusStockMap.put(macId, remainingInventory);
        }
        System.out.println("调整后的剩余库存为 = " + JSON.toJSONString(surplusStockMap));
        //修改申请状态
        Application application=new Application();
        BeanUtils.copyProperties(applicationVO,application);
        try {
            application.setStatus(Constant.ASSIGNSUCCESS);
            applicationDao.update(application);
        } catch (Exception e) {
            application.setStatus(Constant.ASSIGNFAILURE);
            e.printStackTrace();
            log.error("修改申请状态失败");
            return RetResponse.error("修改申请状态失败");
        }
        return RetResponse.success(surplusStockMap);
    }

    @Override
    public Result queryAll() {
        List<DeliveryRecord> deliveryRecords = deliveryRecordDao.queryAll(null);
        return RetResponse.success(deliveryRecords);
    }

    private Map<Integer, Integer> getUsedMacAddressMap() {
        List<DeliveryRecordDTO> byGroupById = deliveryRecordDao.findByGroupById();
        Map<Integer, Integer> macIdMap = new TreeMap<>();
        for (DeliveryRecordDTO deliveryRecordDTO : byGroupById) {
            Integer macId1 = deliveryRecordDTO.getMacId();
            Integer amountSum = deliveryRecordDTO.getAmountSum();
            //同一macId(一个大段)分配了若干记录
            macIdMap.put(macId1, amountSum);
        }
        return macIdMap;
    }

    private Map<Integer, Integer> getAllStartingMacAddress() {
        //查询库存量
        List<Macaddress> macaddresses = macaddressDao.queryAll(null);
        if (CollectionUtils.isEmpty(macaddresses)) {
            log.error("mac地址数据为空，请检查数据");
            throw new RuntimeException("mac地址数据为空，请检查数据");
        }
        /**
         * (id,已使用数量)拼成一个map
         * (id,总数)拼一个map
         * 得到(id,剩余库存)map
         */
        Map<Integer, Integer> startStockMap = macaddresses.stream().collect(Collectors.toMap(Macaddress::getId, Macaddress::getStartingInventory));
        System.out.println("全部初始库存 = " + JSON.toJSONString(startStockMap));
        //{1:5,2:208,25:1048576,26:3000000,27:3000000,28:1777215}
        return startStockMap;
    }
}