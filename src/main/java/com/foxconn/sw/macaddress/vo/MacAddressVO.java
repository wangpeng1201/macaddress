package com.foxconn.sw.macaddress.vo;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class MacAddressVO implements Serializable {
    private static final long serialVersionUID = 4745907803610755060L;
    /**
     * 6位标识
     */
    private String signs;
    /**
     * 起始mac地址
     */
    @NotEmpty
    private String startMacAddress;
    /**
     * 结束mac地址
     */
    @NotEmpty
    private String endMacAddress;

    /**
     * 初始库存量
     */
    @NotEmpty
    @DecimalMin("0")
    private Integer startingInventory;
}
