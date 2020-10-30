package com.foxconn.sw.macaddress.service.impl;

import com.foxconn.sw.macaddress.common.Lay;
import com.foxconn.sw.macaddress.common.ListUtil;
import com.foxconn.sw.macaddress.common.Result;
import com.foxconn.sw.macaddress.common.RetResponse;
import com.foxconn.sw.macaddress.dao.ApplicationDao;
import com.foxconn.sw.macaddress.dao.DeliveryRecordDao;
import com.foxconn.sw.macaddress.dao.MacaddressDao;
import com.foxconn.sw.macaddress.dto.ApplicationDTO;
import com.foxconn.sw.macaddress.entity.Application;
import com.foxconn.sw.macaddress.service.ApplicationService;
import com.foxconn.sw.macaddress.vo.ApplicationVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
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

    @Resource
    private MacaddressDao macaddressDao;

    @Resource
    private DeliveryRecordDao deliveryRecordDao;

    @Autowired
    private HttpSession httpSession;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer id) {
        try {
            Application application = this.applicationDao.queryById(id);
            return RetResponse.success(application);
        } catch (Exception e) {
            e.printStackTrace();
            return RetResponse.error("根据主键查询mac申请失败");
        }
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
        Result result = this.queryById(application.getId());
        if (result.getCode().intValue() == HttpStatus.OK.value()) {
            return (Application) result.getData();
        } else {
            throw new RuntimeException("修改申请失败!");
        }
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
        BeanUtils.copyProperties(applicationVO, application);
        application.setApplicationDate(new Date());
        application.setCreateDate(new Date());
        application.setCreator(httpSession.getAttribute("LoginState").toString());
        application.setUpdateDate(new Date());
        application.setStatus(1);
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
    public List<Application> findByCondition(ApplicationDTO applicationDTO) {
        return applicationDao.findByCondition(applicationDTO);
    }

    @Override
    public Lay findByConditionLayUI(ApplicationDTO applicationDTO) throws ParseException {
        if (ObjectUtils.isEmpty(applicationDTO)) {
            throw new RuntimeException("参数为空");
        }
        if (ObjectUtils.isEmpty(applicationDTO.getPage())) {
            applicationDTO.setPage(1);
        }
        if (ObjectUtils.isEmpty(applicationDTO.getLimit())) {
            applicationDTO.setLimit(10);
        }

        //需要进行分页
        PageHelper.startPage(applicationDTO.getPage(), applicationDTO.getLimit());
        Application application = new Application();
        application.setId(applicationDTO.getApplicationId());
        application.setCustomer(applicationDTO.getCustomer());
        application.setApplicant(applicationDTO.getApplicant());
        if (!StringUtils.isEmpty(applicationDTO.getApplicationDate())) {
            application.setApplicationDate(DateUtils.parseDate(applicationDTO.getApplicationDate(),"yyyy-MM-dd"));
        }
        List<Application> applications = null;
        Lay lay = new Lay();
        try {
            applications = applicationDao.queryAll(application);
            PageInfo info = new PageInfo(applications);//创建pageinfo，包含分页的信息
            lay.setLimit(applicationDTO.getLimit());
            lay.setPage(applicationDTO.getPage());
            lay.setCount(info.getTotal());//总条数
            lay.setData(info.getList());//显示的数据
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询申请失败");
        }
        return lay;
    }

    @Override
    @Transactional
    public Boolean deleteBatch(String ids) {
        List<String> list = ListUtil.getList(ids);
        try {
            applicationDao.deleteBatch(list);
        } catch (Exception e) {
            //显式回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }
}