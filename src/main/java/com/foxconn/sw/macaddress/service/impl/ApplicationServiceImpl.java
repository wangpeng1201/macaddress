package com.foxconn.sw.macaddress.service.impl;

import com.alibaba.fastjson.JSON;
import com.foxconn.sw.macaddress.common.Result;
import com.foxconn.sw.macaddress.common.RetResponse;
import com.foxconn.sw.macaddress.dao.ApplicationDao;
import com.foxconn.sw.macaddress.dao.DeliveryRecordDao;
import com.foxconn.sw.macaddress.dao.MacaddressDao;
import com.foxconn.sw.macaddress.entity.Application;
import com.foxconn.sw.macaddress.entity.DeliveryRecord;
import com.foxconn.sw.macaddress.entity.Macaddress;
import com.foxconn.sw.macaddress.service.ApplicationService;
import com.foxconn.sw.macaddress.vo.ApplicationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Result addApplication(ApplicationVO applicationVO) {
        if (ObjectUtils.isEmpty(applicationVO)) {
            System.out.println("macAddressVO = " + applicationVO);
            log.error("参数{}为空", applicationVO);
            return RetResponse.error("参数为空");
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
            return RetResponse.error("新增mac地址申请失败,原因" + e.getMessage());
        }
        return RetResponse.makeOKRsp();
    }

    /**
     * 根据申请数量分配mac地址段给申请人
     * @param amount
     */
    private void assignMac(Integer amount,Integer macId) {
        //查询库存量
        List<Macaddress> macaddresses = macaddressDao.queryAll(null);
        if (CollectionUtils.isEmpty(macaddresses)) {
            log.error("mac地址数据为空，请检查数据");
            throw new RuntimeException("mac地址数据为空，请检查数据");
        }
        //(id,可用数量)拼成一个map
        //(id,总数)拼一个map
        Map<Integer, Integer> startMap = macaddresses.stream().collect(Collectors.toMap(Macaddress::getId, Macaddress::getStartingInventory));

        DeliveryRecord deliveryRecord=new DeliveryRecord();
        deliveryRecord.setMacId(macId);
        //查询出macid对应的使用量
        List<DeliveryRecord> deliveryRecords = deliveryRecordDao.queryAll(deliveryRecord);
        Integer usedMacAddressSum = deliveryRecords.stream().collect(Collectors.summingInt(d -> d.getAmount()));

    }
}