package com.foxconn.sw.macaddress.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MacAddressEditVO implements Serializable {
    private static final long serialVersionUID = -2542680796214341482L;
    private Integer id;
    /**
     * 起始mac地址
     */
    private String startMacAddress;
    /**
     * 结束mac地址
     */
    private String endMacAddress;
}
