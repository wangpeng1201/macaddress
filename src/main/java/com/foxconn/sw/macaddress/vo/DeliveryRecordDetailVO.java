package com.foxconn.sw.macaddress.vo;

import com.foxconn.sw.macaddress.entity.Application;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeliveryRecordDetailVO implements Serializable {
    private static final long serialVersionUID = 4480497420298917203L;
    /**
     * mac地址表信息
     */
    private MacAddressDetailVO macAddressDetailVO;
    /**
     * 起始mac地址
     */
    private String startMacAddress;
    /**
     * 结束mac地址
     */
    private String endMacAddress;
    /**
     * 结束mac十进制-起始mac十进制+1
     */
    private Integer amount;
    /**
     * 申请
     */
    private Application application;
}
