package com.foxconn.sw.macaddress.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataDTO implements Serializable {
    private static final long serialVersionUID = 1570411801945217380L;
    private Integer key;
    private Integer value;
}
