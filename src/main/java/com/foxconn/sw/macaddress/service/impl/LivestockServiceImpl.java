package com.foxconn.sw.macaddress.service.impl;

import com.foxconn.sw.macaddress.dao.LivestockDao;
import com.foxconn.sw.macaddress.entity.Livestock;
import com.foxconn.sw.macaddress.service.LivestockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实时库存表(Livestock)表服务实现类
 *
 * @author makejava
 * @since 2020-10-13 11:23:49
 */
@Service("livestockService")
public class LivestockServiceImpl implements LivestockService {
    @Resource
    private LivestockDao livestockDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Livestock queryById(Integer id) {
        return this.livestockDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Livestock> queryAllByLimit(int offset, int limit) {
        return this.livestockDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param livestock 实例对象
     * @return 实例对象
     */
    @Override
    public Livestock insert(Livestock livestock) {
        this.livestockDao.insert(livestock);
        return livestock;
    }

    /**
     * 修改数据
     *
     * @param livestock 实例对象
     * @return 实例对象
     */
    @Override
    public Livestock update(Livestock livestock) {
        this.livestockDao.update(livestock);
        return this.queryById(livestock.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.livestockDao.deleteById(id) > 0;
    }
}