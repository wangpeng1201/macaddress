package com.foxconn.sw.macaddress.entity;

import java.io.Serializable;

/**
 * 实时库存表(Livestock)实体类
 *
 * @author makejava
 * @since 2020-10-13 11:23:49
 */
public class Livestock implements Serializable {
    private static final long serialVersionUID = -65970647402115301L;
    /**
     * 主键
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
     * 实时库存总数
     */
    private Integer currentStock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartMacAddress() {
        return startMacAddress;
    }

    public void setStartMacAddress(String startMacAddress) {
        this.startMacAddress = startMacAddress;
    }

    public String getEndMacAddress() {
        return endMacAddress;
    }

    public void setEndMacAddress(String endMacAddress) {
        this.endMacAddress = endMacAddress;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

}