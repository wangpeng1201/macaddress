package com.foxconn.sw.macaddress.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeliveryRecordConditionDTO implements Serializable {

    private static final long serialVersionUID = 5699779335410447567L;
    //起始mac地址
    private String startMacAddress;
    //创建时间
    private String createdate;
    //申请表主键id
    private Integer applicationId;
    //当前页
    private Integer page;
    //每页条数
    private Integer limit;
}
