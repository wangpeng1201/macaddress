package com.foxconn.sw.macaddress.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * mac地址起始创建信息表(Stock)实体类
 *
 * @author makejava
 * @since 2020-10-13 11:23:50
 */
public class Stock implements Serializable {
    private static final long serialVersionUID = 641143504398017449L;
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
     * 总库存数量
     */
    private Integer stock;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     * 状态
     */
    private Integer status;

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}