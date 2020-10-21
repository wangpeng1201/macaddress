package com.foxconn.sw.macaddress.service.impl;

import com.foxconn.sw.macaddress.dao.ApplicationDao;
import com.foxconn.sw.macaddress.entity.Application;
import com.foxconn.sw.macaddress.service.ApplicationService;
import com.foxconn.sw.macaddress.vo.ApplicationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * mac地址申请单(Application)表服务实现类
 *
 * @author makejava
 * @since 2020-10-13 11:23:50
 */
@Slf4j
@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {
    @Resource
    private ApplicationDao applicationDao;

    @Autowired
    private HttpSession httpSession;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Application queryById(Integer id) {
        return this.applicationDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Application> queryAllByLimit(int offset, int limit) {
        return this.applicationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param application 实例对象
     * @return 实例对象
     */
    @Override
    public Application insert(Application application) {
        this.applicationDao.insert(application);
        return application;
    }

    /**
     * 修改数据
     *
     * @param application 实例对象
     * @return 实例对象
     */
    @Override
    public Application update(Application application) {
        this.applicationDao.update(application);
        return this.queryById(application.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.applicationDao.deleteById(id) > 0;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Application> queryAll() {
        return applicationDao.queryAll(null);
    }

    @Override
    public Boolean insertApplication(ApplicationVO applicationVO) {
        if (ObjectUtils.isEmpty(applicationVO)) {
            System.out.println("applicationVO = " + applicationVO);
            log.error("参数{}为空", applicationVO);
            return Boolean.FALSE;
        }
        Application application = new Application();
        application.setCreateDate(new Date());
        application.setCreator(httpSession.getAttribute("LoginState").toString());
        application.setUpdateDate(new Date());
        BeanUtils.copyProperties(applicationVO, application);
        try {
            applicationDao.insert(application);
        } catch (Exception e) {
            log.error("新增mac申请失败,原因{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public List<ApplicationVO> getAll() {
        List<ApplicationVO> result = new ArrayList<>();
        List<Application> applications = applicationDao.queryAll(null);
        for (Application application : applications) {
            ApplicationVO applicationVO = new ApplicationVO();
            BeanUtils.copyProperties(application, applicationVO);
            result.add(applicationVO);
        }
        return result;
    }

    @Override
    @Transactional
    public Boolean addApplication(ApplicationVO applicationVO) {
        if (ObjectUtils.isEmpty(applicationVO)) {
            System.out.println("macAddressVO = " + applicationVO);
            log.error("参数{}为空", applicationVO);
            return false;
        }
        Application application = new Application();
        BeanUtils.copyProperties(applicationVO, application);
        application.setCreateDate(new Date());
        application.setCreator(httpSession.getAttribute("LoginState").toString());
        application.setUpdateDate(new Date());
        application.setStatus(1);
        BeanUtils.copyProperties(applicationVO, application);
        try {
            applicationDao.insert(application);
        } catch (Exception e) {
            log.error("新增mac地址申请失败,原因{}", e.getMessage());
            return false;
        }
        return true;
    }
}