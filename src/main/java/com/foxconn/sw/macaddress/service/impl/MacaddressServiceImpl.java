package com.foxconn.sw.macaddress.service.impl;

import com.foxconn.sw.macaddress.dao.MacaddressDao;
import com.foxconn.sw.macaddress.dto.MacAddressDTO;
import com.foxconn.sw.macaddress.entity.Macaddress;
import com.foxconn.sw.macaddress.service.MacaddressService;
import com.foxconn.sw.macaddress.vo.MacAddressVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * (Macaddress)表服务实现类
 *
 * @author makejava
 * @since 2020-10-13 11:23:46
 */
@Service("macaddressService")
@Slf4j
public class MacaddressServiceImpl implements MacaddressService {
    @Resource
    private MacaddressDao macaddressDao;

    @Autowired
    private HttpSession httpSession;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Macaddress queryById(Integer id) {
        return this.macaddressDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Macaddress> queryAllByLimit(int offset, int limit) {
        return this.macaddressDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param macaddress 实例对象
     * @return 实例对象
     */
    @Override
    public Macaddress insert(Macaddress macaddress) {
        this.macaddressDao.insert(macaddress);
        return macaddress;
    }

    /**
     * 修改数据
     *
     * @param macaddress 实例对象
     * @return 实例对象
     */
    @Override
    public Macaddress update(Macaddress macaddress) {
        this.macaddressDao.update(macaddress);
        return this.queryById(macaddress.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.macaddressDao.deleteById(id) > 0;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Macaddress> queryAll() {
        return macaddressDao.queryAll(null);
    }

    /**
     * 新增Mac地址
     *
     * @param macAddressVO
     */
    @Override
    @Transactional
    public Boolean insertMacAddress(MacAddressVO macAddressVO) {
        if (ObjectUtils.isEmpty(macAddressVO)) {
            System.out.println("macAddressVO = " + macAddressVO);
            log.error("参数{}为空", macAddressVO);
            return Boolean.FALSE;
        }
        Macaddress macaddress = new Macaddress();
        BeanUtils.copyProperties(macAddressVO, macaddress);
        //结束mac地址代表的十进制数
        long endLong = Long.parseLong(macAddressVO.getEndMacAddress(), 16);
        long startLong = Long.parseLong(macAddressVO.getStartMacAddress(), 16);
        macaddress.setStartingInventory((int) (endLong-startLong+1));
        String signs = macAddressVO.getStartMacAddress().substring(0, 6);
        macaddress.setSigns(signs);
        macaddress.setCreatedate(new Date());
        macaddress.setCreator(httpSession.getAttribute("LoginState").toString());
        macaddress.setUpdatedate(new Date());
        macaddress.setStatus(1);
        try {
            macaddressDao.insert(macaddress);
        } catch (Exception e) {
            log.error("新增mac地址失败,原因{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 条件查询
     *
     * @param macAddressDTO
     * @return
     */
    @Override
    public List<Macaddress> findByStartMacAddressAndCreateDate(MacAddressDTO macAddressDTO) {
        if (ObjectUtils.isEmpty(macAddressDTO)) {
            throw new RuntimeException("参数为空");
        }
        List<Macaddress> macaddresses;
        try {
            macaddresses = macaddressDao.queryByCreateDateAndStartMacAddress(macAddressDTO);
        } catch (Exception e) {
            log.error("根据创建时间和起始mac地址查询失败", e.getMessage());
            throw new RuntimeException("根据创建时间和起始mac地址查询失败");
        }
        return macaddresses;
    }
}