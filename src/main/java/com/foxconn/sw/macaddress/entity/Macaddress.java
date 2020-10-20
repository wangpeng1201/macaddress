package com.foxconn.sw.macaddress.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Macaddress)实体类
 *
 * @author makejava
 * @since 2020-10-20 15:44:37
 */
public class Macaddress implements Serializable {
    private static final long serialVersionUID = -51139694256553422L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 以前6位作为标识
     */
    private String signs;
    /**
     * 起始mac地址
     */
    private String startMacAddress;
    /**
     * 结束mac地址
     */
    private String endMacAddress;
    /**
     * 初始库存[(结束mac的十进制-初始mac的十进制数)+1]
     */
    private Integer startingInventory;
    /**
     * 创建时间
     */
    private Date createdate;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 修改时间
     */
    private Date updatedate;
    /**
     * 修改人
     */
    private String updator;
    /**
     * 是否显示(0:不显示，1:显示)
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigns() {
        return signs;
    }

    public void setSigns(String signs) {
        this.signs = signs;
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

    public Integer getStartingInventory() {
        return startingInventory;
    }

    public void setStartingInventory(Integer startingInventory) {
        this.startingInventory = startingInventory;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}