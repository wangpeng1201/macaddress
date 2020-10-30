package com.foxconn.sw.macaddress.service.impl;

import com.alibaba.fastjson.JSON;
import com.foxconn.sw.macaddress.common.Lay;
import com.foxconn.sw.macaddress.common.ListUtil;
import com.foxconn.sw.macaddress.common.Result;
import com.foxconn.sw.macaddress.common.RetResponse;
import com.foxconn.sw.macaddress.dao.DeliveryRecordDao;
import com.foxconn.sw.macaddress.dao.MacaddressDao;
import com.foxconn.sw.macaddress.dto.DeliveryRecordDTO;
import com.foxconn.sw.macaddress.dto.MacAddressDTO;
import com.foxconn.sw.macaddress.entity.Macaddress;
import com.foxconn.sw.macaddress.service.MacaddressService;
import com.foxconn.sw.macaddress.vo.MacAddressDetailVO;
import com.foxconn.sw.macaddress.vo.MacAddressVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (Macaddress)表服务实现类
 *
 * @author makejava
 * @since 2020-10-13 11:23:46
 */
@Service("macaddressService")
@Log4j2
public class MacaddressServiceImpl implements MacaddressService {
    @Resource
    private MacaddressDao macaddressDao;

    @Autowired
    private HttpSession httpSession;

    @Resource
    private DeliveryRecordDao deliveryRecordDao;

    //    public static Map<Integer, Integer> remainingStockMap = new TreeMap<>();
    Map<Integer, Integer> remainingStockMap = new TreeMap<>();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Macaddress queryById(Integer id) {
        return this.macaddressDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Macaddress> queryAllByLimit(int offset, int limit) {
        return this.macaddressDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param macaddress 实例对象
     * @return 实例对象
     */
    @Override
    public Macaddress insert(Macaddress macaddress) {
        this.macaddressDao.insert(macaddress);
        return macaddress;
    }

    /**
     * 修改数据
     *
     * @param macaddress 实例对象
     * @return 实例对象
     */
    @Override
    public Macaddress update(Macaddress macaddress) {
        this.macaddressDao.update(macaddress);
        return this.queryById(macaddress.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.macaddressDao.deleteById(id) > 0;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Macaddress> queryAll() {
        return macaddressDao.queryAll(null);
    }

    /**
     * 新增Mac地址
     *
     * @param macAddressVO
     */
    @Override
    @Transactional
    public Boolean insertMacAddress(MacAddressVO macAddressVO) {
        if (ObjectUtils.isEmpty(macAddressVO)) {
            System.out.println("macAddressVO = " + macAddressVO);
            log.error("参数{}为空", macAddressVO);
            return Boolean.FALSE;
        }
        Macaddress macaddress = new Macaddress();
        BeanUtils.copyProperties(macAddressVO, macaddress);
        //结束mac地址代表的十进制数
        long endLong = Long.parseLong(macAddressVO.getEndMacAddress(), 16);
        long startLong = Long.parseLong(macAddressVO.getStartMacAddress(), 16);
        macaddress.setStartingInventory((int) (endLong - startLong + 1));
        String signs = macAddressVO.getStartMacAddress().substring(0, 6);
        macaddress.setSigns(signs);
        macaddress.setCreatedate(new Date());
        macaddress.setCreator(httpSession.getAttribute("LoginState").toString());
        macaddress.setUpdatedate(new Date());
        macaddress.setStatus(1);
        try {
            macaddressDao.insert(macaddress);
        } catch (Exception e) {
            log.error("新增mac地址失败,原因{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 条件查询
     *
     * @param macAddressDTO
     * @return
     */
    @Override
    public List<Macaddress> findByStartMacAddressAndCreateDate(MacAddressDTO macAddressDTO) {
        if (ObjectUtils.isEmpty(macAddressDTO)) {
            throw new RuntimeException("参数为空");
        }
        List<Macaddress> macaddresses;
        try {
            macaddresses = macaddressDao.queryByCreateDateAndStartMacAddress(macAddressDTO);
        } catch (Exception e) {
            log.error("根据创建时间和起始mac地址查询失败", e.getMessage());
            throw new RuntimeException("根据创建时间和起始mac地址查询失败");
        }
        return macaddresses;
    }

    /**
     * 查询mac id对应的剩余库存
     *
     * @param macId
     * @return
     */
    @Override
    public Result getRemainingStock(Integer macId) {
        //查询全部库存
        Map<Integer, Integer> startStockMap = getAllStartingMacAddress();
        //查询已用库存
        Map<Integer, Integer> macIdMap = getUsedMacAddressMap();
        //构造剩余库存map
        Integer remainingStock = null;
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
            remainingStock = entry.getValue() - usedStock;
            //剩余库存不为0的macaddress才添加到list和map中，保证数据可再分配
            remainingStockMap.put(entry.getKey(), remainingStock);
        }
        //剩余库存map(macId,库存数)
        //surplusStockMap = {1:1,2:192,25:1048576,26:3000000,27:3000000,28:1777215}
        System.err.println("remainingStockMap = " + JSON.toJSONString(remainingStockMap));
        Integer remaining = 0;
        if (remainingStockMap.containsKey(macId)) {
            remaining = remainingStockMap.get(macId);
        } else {
            log.error("未查询到该mac段对应的库存信息");
            throw new RuntimeException("未查询到该mac段对应的库存信息");
        }
        MacAddressDetailVO macAddressDetailVO = new MacAddressDetailVO();
        try {
            Macaddress macaddress = macaddressDao.queryById(macId);
            macAddressDetailVO.setId(macId);
            macAddressDetailVO.setStartMacAddress(macaddress.getStartMacAddress());
            macAddressDetailVO.setEndMacAddress(macaddress.getEndMacAddress());
            macAddressDetailVO.setStartingInventory(macaddress.getStartingInventory());
            macAddressDetailVO.setRemainingInventory(remaining);
        } catch (Exception e) {
            log.error("根据主键查询mac地址信息失败");
            e.printStackTrace();
            throw new RuntimeException("根据主键查询mac地址信息失败");
        }
        return RetResponse.success(macAddressDetailVO);
    }

    /**
     * 按条件查询
     *
     * @param macAddressDTO
     * @return
     */
    @Override
    public Lay findByConditionLayUI(MacAddressDTO macAddressDTO) {
        if (ObjectUtils.isEmpty(macAddressDTO)) {
            throw new RuntimeException("参数为空");
        }
        if (ObjectUtils.isEmpty(macAddressDTO.getPage())) {
            macAddressDTO.setPage(1);
        }
        if (ObjectUtils.isEmpty(macAddressDTO.getLimit())) {
            macAddressDTO.setLimit(10);
        }

        //需要进行分页
        PageHelper.startPage(macAddressDTO.getPage(), macAddressDTO.getLimit());
        Macaddress macaddress = new Macaddress();
        macaddress.setStartMacAddress(macAddressDTO.getStartMacAddress());
        if (!StringUtils.isEmpty(macAddressDTO.getCreatedate())) {
            try {
                macaddress.setCreatedate(DateUtils.parseDate(macAddressDTO.getCreatedate(), "yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<Macaddress> macaddresses;
        Lay lay = new Lay();
        try {
            macaddresses = macaddressDao.queryAll(macaddress);
            PageInfo info = new PageInfo(macaddresses);//创建pageinfo，包含分页的信息
            lay.setLimit(macAddressDTO.getLimit());
            lay.setPage(macAddressDTO.getPage());
            lay.setCount(info.getTotal());//总条数
            lay.setData(info.getList());//显示的数据
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询申请失败");
        }
        return lay;
    }

    @Override
    @Transactional
    public Boolean deleteBatch(String ids) {
        List<String> list = ListUtil.getList(ids);
        try {
            macaddressDao.deleteBatch(list);
        } catch (Exception e) {
            //显式回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
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
}