package com.foxconn.sw.macaddress.dao;

import com.foxconn.sw.macaddress.entity.Livestock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 实时库存表(Livestock)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-13 11:23:49
 */
public interface LivestockDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Livestock queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Livestock> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param livestock 实例对象
     * @return 对象列表
     */
    List<Livestock> queryAll(Livestock livestock);

    /**
     * 新增数据
     *
     * @param livestock 实例对象
     * @return 影响行数
     */
    int insert(Livestock livestock);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Livestock> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Livestock> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Livestock> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Livestock> entities);

    /**
     * 修改数据
     *
     * @param livestock 实例对象
     * @return 影响行数
     */
    int update(Livestock livestock);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}