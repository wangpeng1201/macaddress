package com.foxconn.sw.macaddress.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 详情模态框
 */
@Data
public class MacAddressDetailVO implements Serializable {
    private static final long serialVersionUID = 2667865332874562587L;
    /**
     * macaddress表中主键
     */
    private Integer id;
    /**
     * 起始mac地址
     */
    private String startMacAddress;
    /**
     * 结束mac地址
     */
    private String endMacAddress;
    /**
     * 初始库存量
     */
    private Integer startingInventory;
    /**
     * 剩余库存
     */
    private Integer remainingInventory;
}
