package com.foxconn.sw.macaddress.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeliveryRecordDTO implements Serializable {
    private static final long serialVersionUID = -900870017507750941L;
    private Integer macId;
    //group by id查询到的总数
    private Integer amountSum;
}
