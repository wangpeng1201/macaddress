package com.foxconn.sw.macaddress.service;

import com.foxconn.sw.macaddress.common.Lay;
import com.foxconn.sw.macaddress.common.Result;
import com.foxconn.sw.macaddress.dto.DeliveryRecordConditionDTO;
import com.foxconn.sw.macaddress.dto.MacAddressDTO;
import com.foxconn.sw.macaddress.entity.DeliveryRecord;
import com.foxconn.sw.macaddress.vo.ApplicationVO;

import java.util.List;

/**
 * mac地址交付记录，此表代表申请提交后从mac地址表中分配的记录，若存在跨段(申请数量大于mac地址表该段可用数量)，则同一个mac_id生成多个记录(DeliveryRecord)表服务接口
 *
 * @author makejava
 * @since 2020-10-21 17:24:24
 */
public interface DeliveryRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Result queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DeliveryRecord> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param deliveryRecord 实例对象
     * @return 实例对象
     */
    DeliveryRecord insert(DeliveryRecord deliveryRecord);

    /**
     * 修改数据
     *
     * @param deliveryRecord 实例对象
     * @return 实例对象
     */
    Boolean update(DeliveryRecord deliveryRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Result assignMac(ApplicationVO applicationVO);

    Result queryAll();

    /**
     * 条件查询
     * @param macAddressDTO
     * @return
     */
    List<DeliveryRecord> findByCondition(MacAddressDTO macAddressDTO);

    /**
     * 条件查询封装layui结果
     * @param deliveryRecordConditionDTO
     * @return
     */
    Lay findByConditionLayUI(DeliveryRecordConditionDTO deliveryRecordConditionDTO);
}