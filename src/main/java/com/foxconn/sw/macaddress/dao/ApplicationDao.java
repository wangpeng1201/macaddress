package com.foxconn.sw.macaddress.dao;

import com.foxconn.sw.macaddress.dto.ApplicationDTO;
import com.foxconn.sw.macaddress.entity.Application;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mac地址申请单(Application)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-16 13:59:53
 */
public interface ApplicationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Application queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Application> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param application 实例对象
     * @return 对象列表
     */
    List<Application> queryAll(Application application);

    /**
     * 新增数据
     *
     * @param application 实例对象
     * @return 影响行数
     */
    int insert(Application application);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Application> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Application> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Application> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Application> entities);

    /**
     * 修改数据
     *
     * @param application 实例对象
     * @return 影响行数
     */
    int update(Application application);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 条件查询
     * @param applicationDTO
     * @return
     */
    List<Application> findByCondition(ApplicationDTO applicationDTO);
}