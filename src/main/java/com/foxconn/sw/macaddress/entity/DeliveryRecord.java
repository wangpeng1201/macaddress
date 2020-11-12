package com.foxconn.sw.macaddress.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * mac地址交付记录，此表代表申请提交后从mac地址表中分配的记录，若存在跨段(申请数量大于mac地址表该段可用数量)，则同一个mac_id生成多个记录(DeliveryRecord)实体类
 *
 * @author makejava
 * @since 2020-10-21 17:24:20
 */
public class DeliveryRecord implements Serializable {
    private static final long serialVersionUID = 373939849780304642L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * mac地址表主键
     */
    private Integer macId;
    /**
     * 起始mac地址
     */
    private String startMacAddress;
    /**
     * 结束mac地址
     */
    private String endMacAddress;
    /**
     * application表主键
     */
    private Integer applicationId;
    /**
     * 结束mac十进制-起始mac十进制+1
     */
    private Integer amount;
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
     * 是否显示(0:不显示，1:显示,2:撤回发放)
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMacId() {
        return macId;
    }

    public void setMacId(Integer macId) {
        this.macId = macId;
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

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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