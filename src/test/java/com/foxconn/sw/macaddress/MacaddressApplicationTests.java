package com.foxconn.sw.macaddress;

import com.alibaba.fastjson.JSON;
import com.foxconn.sw.macaddress.dao.MacaddressDao;
import com.foxconn.sw.macaddress.entity.Macaddress;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest(classes = MacaddressApplication.class)
// 与上文启动类-极简版对应
class MacaddressApplicationTests {
    @Autowired
    MacaddressDao macaddressDao;

    @Test
    void contextLoads() {
        List<Macaddress> macaddresses = macaddressDao.queryAll(null);
        Map<Integer, Integer> startMap = macaddresses.stream().collect(Collectors.toMap(Macaddress::getId, Macaddress::getStartingInventory));
        System.out.println(" startMap= " + JSON.toJSONString(startMap));
    }

}
