package com.foxconn.sw.macaddress.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApplicationDTO implements Serializable {
    private static final long serialVersionUID = -3859025676685626902L;
    //客户
    private String customer;
    //申请人
    private String applicant;
    //申请时间
    private String applicationDate;
    //当前页
    private Integer page;
    //每页条数
    private Integer limit;
}
