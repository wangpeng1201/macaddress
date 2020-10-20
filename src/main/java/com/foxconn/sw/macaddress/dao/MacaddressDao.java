package com.foxconn.sw.macaddress.dao;

import com.foxconn.sw.macaddress.dto.MacAddressDTO;
import com.foxconn.sw.macaddress.entity.Macaddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Macaddress)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-16 14:23:27
 */
public interface MacaddressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Macaddress queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Macaddress> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param macaddress 实例对象
     * @return 对象列表
     */
    List<Macaddress> queryAll(Macaddress macaddress);

    /**
     * 新增数据
     *
     * @param macaddress 实例对象
     * @return 影响行数
     */
    int insert(Macaddress macaddress);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Macaddress> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Macaddress> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Macaddress> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Macaddress> entities);

    /**
     * 修改数据
     *
     * @param macaddress 实例对象
     * @return 影响行数
     */
    int update(Macaddress macaddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据创建时间和起始Mac地址查询
     */
    List<Macaddress> queryByCreateDateAndStartMacAddress(MacAddressDTO macAddressDTO);
}