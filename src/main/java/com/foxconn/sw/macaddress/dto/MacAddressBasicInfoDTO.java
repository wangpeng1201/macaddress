package com.foxconn.sw.macaddress.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MacAddressBasicInfoDTO implements Serializable {
    private static final long serialVersionUID = 5782158819218368197L;
    /**
     * 编号
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
     * 初始库存[(结束mac的十进制-初始mac的十进制数)+1]
     */
    private Integer startingInventory;
}
