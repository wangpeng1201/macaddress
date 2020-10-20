package com.foxconn.sw.macaddress.service;

import com.foxconn.sw.macaddress.entity.Livestock;

import java.util.List;

/**
 * 实时库存表(Livestock)表服务接口
 *
 * @author makejava
 * @since 2020-10-13 11:23:49
 */
public interface LivestockService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Livestock queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Livestock> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param livestock 实例对象
     * @return 实例对象
     */
    Livestock insert(Livestock livestock);

    /**
     * 修改数据
     *
     * @param livestock 实例对象
     * @return 实例对象
     */
    Livestock update(Livestock livestock);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}