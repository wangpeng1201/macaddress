package com.foxconn.sw.macaddress.vo;

import com.foxconn.sw.macaddress.entity.DeliveryRecord;
import lombok.Data;

import java.util.Date;

@Data
public class DeliveryRecordVO extends DeliveryRecord {
    private static final long serialVersionUID = -4431442947051473053L;
    /**
     * 发放日期
     */
    private Date releaseDate;
    /**
     * 费用转嫁承办人工号
     */
    private String director;
    /**
     * 费用转嫁单号
     */
    private String shiftCostNo;
    /**
     * 转嫁费用金额
     */
    private Double shiftCost;
}
