package com.foxconn.sw.macaddress.controller;

import com.foxconn.sw.macaddress.entity.Stock;
import com.foxconn.sw.macaddress.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * mac地址起始创建信息表(Stock)表控制层
 *
 * @author makejava
 * @since 2020-10-13 11:23:51
 */
@RestController
@RequestMapping("stock")
public class StockController {
    /**
     * 服务对象
     */
    @Resource
    private StockService stockService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Stock selectOne(Integer id) {
        return this.stockService.queryById(id);
    }

}