package com.foxconn.sw.macaddress.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * mac地址申请单(Application)实体类
 *
 * @author makejava
 * @since 2020-10-20 17:04:58
 */
public class Application implements Serializable {
    private static final long serialVersionUID = -36133207705207917L;
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
     * 申请单位
     */
    private String applicationDepartment;
    /**
     * 客户
     */
    private String customer;
    /**
     * 申请数量
     */
    private Integer amount;
    /**
     * 申请人
     */
    private String applicant;
    /**
     * 申请日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date applicationDate;
    /**
     * 发放日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date releaseDate;
    /**
     * 费用转嫁承办人工号
     */
    private String director;
    /**
     * 费用转嫁单号
     */
    private String shiftCostNo;
    /**
     * 转嫁费用金额
     */
    private Double shiftCost;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     * 修改人
     */
    private String updator;
    /**
     * 申请状态(0:不显示，1：新建申请，2：分发mac地址成功，3：分发mac地址失败）
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

    public String getApplicationDepartment() {
        return applicationDepartment;
    }

    public void setApplicationDepartment(String applicationDepartment) {
        this.applicationDepartment = applicationDepartment;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getShiftCostNo() {
        return shiftCostNo;
    }

    public void setShiftCostNo(String shiftCostNo) {
        this.shiftCostNo = shiftCostNo;
    }

    public Double getShiftCost() {
        return shiftCost;
    }

    public void setShiftCost(Double shiftCost) {
        this.shiftCost = shiftCost;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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