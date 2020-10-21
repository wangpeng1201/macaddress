package com.foxconn.sw.macaddress.service.impl;

import com.foxconn.sw.macaddress.dao.DeliveryRecordDao;
import com.foxconn.sw.macaddress.entity.DeliveryRecord;
import com.foxconn.sw.macaddress.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * mac地址交付记录，此表代表申请提交后从mac地址表中分配的记录，若存在跨段(申请数量大于mac地址表该段可用数量)，则同一个mac_id生成多个记录(DeliveryRecord)表服务实现类
 *
 * @author makejava
 * @since 2020-10-21 17:24:24
 */
@Service("deliveryRecordService")
public class DeliveryRecordServiceImpl implements DeliveryRecordService {
    @Resource
    private DeliveryRecordDao deliveryRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DeliveryRecord queryById(Integer id) {
        return this.deliveryRecordDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DeliveryRecord> queryAllByLimit(int offset, int limit) {
        return this.deliveryRecordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param deliveryRecord 实例对象
     * @return 实例对象
     */
    @Override
    public DeliveryRecord insert(DeliveryRecord deliveryRecord) {
        this.deliveryRecordDao.insert(deliveryRecord);
        return deliveryRecord;
    }

    /**
     * 修改数据
     *
     * @param deliveryRecord 实例对象
     * @return 实例对象
     */
    @Override
    public DeliveryRecord update(DeliveryRecord deliveryRecord) {
        this.deliveryRecordDao.update(deliveryRecord);
        return this.queryById(deliveryRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.deliveryRecordDao.deleteById(id) > 0;
    }
}