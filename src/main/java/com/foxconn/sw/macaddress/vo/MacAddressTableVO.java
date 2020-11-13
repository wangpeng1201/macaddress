package com.foxconn.sw.macaddress.vo;

import com.foxconn.sw.macaddress.entity.Macaddress;
import lombok.Data;

/**
 * 表格显示数据
 */
@Data
public class MacAddressTableVO extends Macaddress {
    private static final long serialVersionUID = 417612623111558830L;
    /**
     * 剩余库存
     */
    private Integer remainingInventory;
}
