package com.foxconn.sw.macaddress.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MacAddressDTO implements Serializable {
    private static final long serialVersionUID = -1124265870617763243L;
    //起始mac地址
    private String startMacAddress;
    //创建时间
    private Date createDate;
    //当前页码
    private Integer pageNum;
}
