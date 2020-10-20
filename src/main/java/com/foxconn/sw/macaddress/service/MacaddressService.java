package com.foxconn.sw.macaddress.service;

import com.foxconn.sw.macaddress.dto.MacAddressDTO;
import com.foxconn.sw.macaddress.entity.Macaddress;
import com.foxconn.sw.macaddress.vo.MacAddressVO;

import java.util.List;

/**
 * (Macaddress)表服务接口
 *
 * @author makejava
 * @since 2020-10-13 11:23:45
 */
public interface MacaddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Macaddress queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Macaddress> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param macaddress 实例对象
     * @return 实例对象
     */
    Macaddress insert(Macaddress macaddress);

    /**
     * 修改数据
     *
     * @param macaddress 实例对象
     * @return 实例对象
     */
    Macaddress update(Macaddress macaddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询所有
     *
     * @return
     */
    List<Macaddress> queryAll();

    /**
     * 新增mac地址
     * @param macAddressVO
     * @return
     */
    Boolean insertMacAddress(MacAddressVO macAddressVO);

    /**
     * 条件查询
     * @param macAddressDTO
     * @return
     */
    List<Macaddress> findByStartMacAddressAndCreateDate(MacAddressDTO macAddressDTO);
}