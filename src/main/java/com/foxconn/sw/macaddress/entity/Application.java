package com.foxconn.sw.macaddress.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * mac地址申请单(Application)实体类
 *
 * @author makejava
 * @since 2020-10-16 13:59:48
 */
@Data
public class Application implements Serializable {
    private static final long serialVersionUID = 691869192854831794L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * mac表id(关联macaddress表，包含起始mac地址和结束mac地址)
     */
    private Integer macId;
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
    private Date applicationDate;
    /**
     * 发放日期
     */
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
}