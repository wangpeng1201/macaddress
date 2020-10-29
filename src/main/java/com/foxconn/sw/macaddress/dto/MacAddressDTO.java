package com.foxconn.sw.macaddress.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 条件查询的搜索条件
 */
@Data
public class MacAddressDTO implements Serializable {
    private static final long serialVersionUID = -1124265870617763243L;
    //起始mac地址
    private String startMacAddress;
    //创建时间
    private String createdate;
    //当前页
    private Integer page;
    //每页条数
    private Integer limit;
}
