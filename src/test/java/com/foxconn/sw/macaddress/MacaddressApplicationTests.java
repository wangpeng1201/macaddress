package com.foxconn.sw.macaddress;

import com.alibaba.fastjson.JSON;
import com.foxconn.sw.macaddress.dao.DeliveryRecordDao;
import com.foxconn.sw.macaddress.dao.MacaddressDao;
import com.foxconn.sw.macaddress.dto.DeliveryRecordDTO;
import com.foxconn.sw.macaddress.entity.Macaddress;
import com.foxconn.sw.macaddress.service.ApplicationService;
import com.foxconn.sw.macaddress.service.DeliveryRecordService;
import com.foxconn.sw.macaddress.vo.ApplicationVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest(classes = MacaddressApplication.class)
// 与上文启动类-极简版对应
class MacaddressApplicationTests {
    @Resource
    MacaddressDao macaddressDao;

    @Resource
    DeliveryRecordDao deliveryRecordDao;

    @Resource
    DeliveryRecordService deliveryRecordService;

    @Autowired
    ApplicationService applicationService;

    /**
     * 查询初始库存
     */
    @Test
    void contextLoads() {
        List<Macaddress> macaddresses = macaddressDao.queryAll(null);
        Map<Integer, Integer> startMap = macaddresses.stream().collect(Collectors.toMap(Macaddress::getId, Macaddress::getStartingInventory));
        System.out.println(" startMap= " + JSON.toJSONString(startMap));
    }

    /**
     * 根据group by mac id 查询DeliveryRecord表
     */
    @Test
    void testSumByGroupId() {
        List<Macaddress> macaddresses = macaddressDao.queryAll(null);
        Map<Integer, Integer> startMap = macaddresses.stream().collect(Collectors.toMap(Macaddress::getId, Macaddress::getStartingInventory));
        //初始库存
        System.out.println(" startMap= " + JSON.toJSONString(startMap));

        List<DeliveryRecordDTO> byGroupById = deliveryRecordDao.findByGroupById();
        System.err.println("byGroupById = " + byGroupById);
        Map<Integer, Integer> macIdMap = new TreeMap<>();
        for (DeliveryRecordDTO deliveryRecordDTO : byGroupById) {
            Integer macId1 = deliveryRecordDTO.getMacId();
            Integer amountSum = deliveryRecordDTO.getAmountSum();
            macIdMap.put(macId1, amountSum);
        }
        //已使用库存
        System.out.println("macIdMap= " + JSON.toJSONString(macIdMap));

        Map<Integer, Integer> surplusStockMap=new TreeMap<>();
        //构造剩余库存map
        Integer surplusStock;
        Integer usedStock;
        for (Map.Entry<Integer, Integer> entry : startMap.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
            if (macIdMap.containsKey(entry.getKey())) {
                //当前macId对应的已使用库存量
                usedStock = macIdMap.get(entry.getKey());
            } else {
                usedStock=0;
            }
            //剩余库存量
            surplusStock = entry.getValue() - usedStock;
            surplusStockMap.put(entry.getKey(), surplusStock);
        }
        System.err.println("surplusStockMap = " + JSON.toJSONString(surplusStockMap));

        //surplusStockMap = {1:1,2:192,25:1048576,26:3000000,27:3000000,28:1777215}
    }

    @Test
    public void addApplication() {
        ApplicationVO applicationVO=new ApplicationVO();

        applicationVO.setApplicationDepartment("部门");
        applicationVO.setCustomer("客户");
        applicationVO.setAmount(8825983);
        applicationVO.setApplicant("申请人");
        applicationVO.setApplicationDate(new Date());
        applicationVO.setReleaseDate(new Date());
        applicationVO.setDirector("11111111111");
        applicationVO.setShiftCostNo("111111111");
        applicationVO.setShiftCost(7.0D);
        deliveryRecordService.assignMac(applicationVO);
    }

}
