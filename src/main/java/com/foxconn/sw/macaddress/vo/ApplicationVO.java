package com.foxconn.sw.macaddress.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApplicationVO implements Serializable {
    private static final long serialVersionUID = 7193858019608227639L;

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
     * 申请人部门(单位)
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
}
