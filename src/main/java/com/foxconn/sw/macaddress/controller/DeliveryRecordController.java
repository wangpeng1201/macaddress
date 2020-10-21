package com.foxconn.sw.macaddress.controller;

import com.foxconn.sw.macaddress.entity.DeliveryRecord;
import com.foxconn.sw.macaddress.service.DeliveryRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * mac地址交付记录，此表代表申请提交后从mac地址表中分配的记录，若存在跨段(申请数量大于mac地址表该段可用数量)，则同一个mac_id生成多个记录(DeliveryRecord)表控制层
 *
 * @author makejava
 * @since 2020-10-21 17:24:25
 */
@RestController
@RequestMapping("deliveryRecord")
public class DeliveryRecordController {
    /**
     * 服务对象
     */
    @Resource
    private DeliveryRecordService deliveryRecordService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public DeliveryRecord selectOne(Integer id) {
        return this.deliveryRecordService.queryById(id);
    }

}