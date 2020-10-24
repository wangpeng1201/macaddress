package com.foxconn.sw.macaddress.service.impl;

import com.foxconn.sw.macaddress.dao.ApplicationDao;
import com.foxconn.sw.macaddress.dao.DeliveryRecordDao;
import com.foxconn.sw.macaddress.dao.MacaddressDao;
import com.foxconn.sw.macaddress.dto.DataDTO;
import com.foxconn.sw.macaddress.entity.Application;
import com.foxconn.sw.macaddress.service.ApplicationService;
import com.foxconn.sw.macaddress.vo.ApplicationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * mac地址申请单(Application)表服务实现类
 *
 * @author makejava
 * @since 2020-10-13 11:23:50
 */
@Slf4j
@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {
    @Resource
    private ApplicationDao applicationDao;

    @Resource
    private MacaddressDao macaddressDao;

    @Resource
    private DeliveryRecordDao deliveryRecordDao;

    @Autowired
    private HttpSession httpSession;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Application queryById(Integer id) {
        return this.applicationDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Application> queryAllByLimit(int offset, int limit) {
        return this.applicationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param application 实例对象
     * @return 实例对象
     */
    @Override
    public Application insert(Application application) {
        this.applicationDao.insert(application);
        return application;
    }

    /**
     * 修改数据
     *
     * @param application 实例对象
     * @return 实例对象
     */
    @Override
    public Application update(Application application) {
        this.applicationDao.update(application);
        return this.queryById(application.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.applicationDao.deleteById(id) > 0;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Application> queryAll() {
        return applicationDao.queryAll(null);
    }

    @Override
    public Boolean insertApplication(ApplicationVO applicationVO) {
        if (ObjectUtils.isEmpty(applicationVO)) {
            System.out.println("applicationVO = " + applicationVO);
            log.error("参数{}为空", applicationVO);
            return Boolean.FALSE;
        }
        Application application = new Application();
        BeanUtils.copyProperties(applicationVO, application);
        application.setCreateDate(new Date());
        application.setCreator(httpSession.getAttribute("LoginState").toString());
        application.setUpdateDate(new Date());
        application.setStatus(1);
        try {
            applicationDao.insert(application);
        } catch (Exception e) {
            log.error("新增mac申请失败,原因{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public List<ApplicationVO> getAll() {
        List<ApplicationVO> result = new ArrayList<>();
        List<Application> applications = applicationDao.queryAll(null);
        for (Application application : applications) {
            ApplicationVO applicationVO = new ApplicationVO();
            BeanUtils.copyProperties(application, applicationVO);
            result.add(applicationVO);
        }
        return result;
    }


//    public Result addApplication(ApplicationVO applicationVO) {
//        ////////////////////////////////////////////////////参数校验非空开始//////////////////////////////////////////////////////
//        if (ObjectUtils.isEmpty(applicationVO)) {
//            System.out.println("macAddressVO = " + applicationVO);
//            log.error("参数{}为空", applicationVO);
//            return RetResponse.error("参数为空");
//        }
//        ////////////////////////////////////////////////////参数校验非空结束//////////////////////////////////////////////////////
//
//        //查询全部库存
//        Map<Integer, Integer> startStockMap = ListUtil.getAllStartingMacAddress();
//
//        //查询已用库存
//        Map<Integer, Integer> macIdMap = getUsedMacAddressMap();
//        ////////////////////////////////////////////////////查询可用库存开始//////////////////////////////////////////////////////
//        Map<Integer, Integer> surplusStockMap = new TreeMap<>();
//        //每段macId对应的剩余库存
//        List<Integer> surplusStockList = new ArrayList<>();
//        //macId集合
//        List<Integer> macIdList = new ArrayList<>();
//        //构造剩余库存map
//        Integer surplusStock = null;
//        Integer usedStock = null;
//        for (Map.Entry<Integer, Integer> entry : startStockMap.entrySet()) {
//            if (macIdMap.containsKey(entry.getKey())) {
//                //当前macId对应的已使用库存量
//                usedStock = macIdMap.get(entry.getKey());
//            } else {
//                //当前mac段未使用
//                usedStock = 0;
//            }
//            //剩余库存量
//            surplusStock = entry.getValue() - usedStock;
//            //剩余库存不为0的macaddress才添加到list和map中，保证数据可再分配
//            if (surplusStock != 0) {
//                surplusStockMap.put(entry.getKey(), surplusStock);
//                //剩余库存对应的集合
//                surplusStockList.add(surplusStock);
//                macIdList.add(entry.getKey());
//            }
//        }
//        //剩余库存map(macId,库存数)
//        //surplusStockMap = {1:1,2:192,25:1048576,26:3000000,27:3000000,28:1777215}
//        System.err.println("surplusStockMap = " + JSON.toJSONString(surplusStockMap));
//        //剩余库存List(上方map去除key)
//        //surplusStockList = [1,192,1048576,3000000,3000000,1777215]
//        System.err.println("surplusStockList = " + JSON.toJSONString(surplusStockList));
//        ////////////////////////////////////////////////////查询可用库存结束//////////////////////////////////////////////////////
//
//        //申请数量
//        Integer amount = applicationVO.getAmount();
//        ArrayList<Integer> valueList = new ArrayList<>();
//        ArrayList<Integer> valueSumList = new ArrayList<>();
//
//        for (Map.Entry<Integer, Integer> entry : surplusStockMap.entrySet()) {
//            valueList.add(entry.getValue());
//            sumList(valueList, valueList.size());
//        }
//
//        for (int i = 0; i < valueList.size(); i++) {
//            valueSumList.add(sumList(valueList, i + 1));
//        }
//        //剩余库存的前n项和集合：[1, 193, 1048769, 4048769, 7048769, 8825984]
//        System.out.println("剩余库存的前n项和集合：" + valueSumList);
//
//        List<DeliveryRecord> result = new ArrayList<>();
//        //将申请数量添加到前n项和中求索引
//        //list = [1, 30, 193, 1048769, 4048769, 7048769, 8825984]
//        //list.indexOf(n) = 1
//        int importantIndex = index(valueSumList, amount);
//        Integer stockEnough = surplusStockList.get(importantIndex);
//        int surplusStockWithoutTail = 0;
//        int kuaDuanTail = 0;
//        if (importantIndex != 0) {
//            surplusStockWithoutTail = sumList(surplusStockList, importantIndex);
//            //跨段多出来的
//            kuaDuanTail = amount - surplusStockWithoutTail;
//        } else {
//            surplusStockWithoutTail = sumList(surplusStockList, importantIndex + 1);
//            //未跨段
//            kuaDuanTail = amount;
//        }
//
//        //[1,2,25,26,27,28]
//        for (int i = importantIndex; i >= 0; i--) {
//            Integer mac = macIdList.get(i);
//            DeliveryRecord deliveryRecord = new DeliveryRecord();
//            deliveryRecord.setMacId(mac);
//            //TODO 待计算
//            deliveryRecord.setStartMacAddress("");
//            deliveryRecord.setEndMacAddress("");
//            deliveryRecord.setApplicationId(applicationVO.getId());
//            if (i == importantIndex) {
//                deliveryRecord.setAmount(kuaDuanTail);
//            } else {
//                deliveryRecord.setAmount(surplusStockMap.get(mac));
//            }
//            deliveryRecord.setCreatedate(new Date());
////            deliveryRecord.setCreator(httpSession.getAttribute("LoginState").toString());
//            deliveryRecord.setUpdatedate(new Date());
//            deliveryRecord.setStatus(1);
//            result.add(deliveryRecord);
//        }
//
//        System.out.println("result = " + JSON.toJSONString(result));
//        try {
//            deliveryRecordDao.insertBatch(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("分配mac地址失败，原因{}", e.getMessage());
//            throw new RuntimeException("分配mac地址失败");
//        }
////        Application application = new Application();
////        BeanUtils.copyProperties(applicationVO, application);
////        application.setCreateDate(new Date());
////        application.setCreator(httpSession.getAttribute("LoginState").toString());
////        application.setUpdateDate(new Date());
////        application.setStatus(1);
////        BeanUtils.copyProperties(applicationVO, application);
////        try {
////            applicationDao.insert(application);
////        } catch (Exception e) {
////            log.error("新增mac地址申请失败,原因{}", e.getMessage());
////            return RetResponse.error("新增mac地址申请失败,原因" + e.getMessage());
////        }
//        return RetResponse.makeOKRsp();
//    }

    private List<Integer> mapToList(Map<Integer, Integer> map, int n) {
        List list = new ArrayList();
        map.forEach((k, v) -> {
            DataDTO dataDTO = new DataDTO();
            dataDTO.setKey(k);
            dataDTO.setValue(v);
            list.add(dataDTO);
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
        }
        mapToList(map, n - 1);
        return null;
    }
}