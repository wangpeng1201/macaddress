package com.foxconn.sw.macaddress.controller;

import com.foxconn.sw.macaddress.entity.Livestock;
import com.foxconn.sw.macaddress.service.LivestockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 实时库存表(Livestock)表控制层
 *
 * @author makejava
 * @since 2020-10-13 11:23:49
 */
@RestController
@RequestMapping("livestock")
public class LivestockController {
    /**
     * 服务对象
     */
    @Resource
    private LivestockService livestockService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Livestock selectOne(Integer id) {
        return this.livestockService.queryById(id);
    }

}