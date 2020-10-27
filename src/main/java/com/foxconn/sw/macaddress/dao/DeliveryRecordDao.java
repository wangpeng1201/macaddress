package com.foxconn.sw.macaddress.dao;

import com.foxconn.sw.macaddress.dto.DeliveryRecordDTO;
import com.foxconn.sw.macaddress.dto.MacAddressDTO;
import com.foxconn.sw.macaddress.entity.DeliveryRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mac地址交付记录，此表代表申请提交后从mac地址表中分配的记录，若存在跨段(申请数量大于mac地址表该段可用数量)，则同一个mac_id生成多个记录(DeliveryRecord)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-21 17:24:23
 */
public interface DeliveryRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DeliveryRecord queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DeliveryRecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param deliveryRecord 实例对象
     * @return 对象列表
     */
    List<DeliveryRecord> queryAll(DeliveryRecord deliveryRecord);

    /**
     * 新增数据
     *
     * @param deliveryRecord 实例对象
     * @return 影响行数
     */
    int insert(DeliveryRecord deliveryRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DeliveryRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DeliveryRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DeliveryRecord> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<DeliveryRecord> entities);

    /**
     * 修改数据
     *
     * @param deliveryRecord 实例对象
     * @return 影响行数
     */
    int update(DeliveryRecord deliveryRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<DeliveryRecordDTO> findByGroupById();

    List<DeliveryRecord> findByCondition(MacAddressDTO macAddressDTO);
}