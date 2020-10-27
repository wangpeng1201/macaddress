package com.foxconn.sw.macaddress.controller;

import com.foxconn.sw.macaddress.common.Result;
import com.foxconn.sw.macaddress.common.RetResponse;
import com.foxconn.sw.macaddress.dto.ApplicationDTO;
import com.foxconn.sw.macaddress.entity.Application;
import com.foxconn.sw.macaddress.entity.Macaddress;
import com.foxconn.sw.macaddress.service.ApplicationService;
import com.foxconn.sw.macaddress.vo.ApplicationVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * mac地址申请单(Application)表控制层
 *
 * @author makejava
 * @since 2020-10-16 14:06:29
 */
@Log4j2
@Controller
public class ApplicationController {
    /**
     * 服务对象
     */
    @Resource
    private ApplicationService applicationService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/applications")
    public String applications(Model model,
                               @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, 15);
        try {
            List<Application> applications = applicationService.queryAll();
            //使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Application> pageInfo = new PageInfo<Application>(applications, 5);
            //使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
//            model.addAttribute("url", "application/list");
            model.addAttribute("url", "applications");
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "application/list";
    }

    @PostMapping("/applications")
    @ResponseBody
    public Result addApplication(ApplicationVO applicationVO) {
        applicationService.insertApplication(applicationVO);
        return RetResponse.makeOKRsp();
//        return "redirect:/applications";
    }

    @PostMapping(value = "/applicationBycondition")
    public String findByCondition(Model model, ApplicationDTO applicationDTO,
                                  @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum) {
        if (ObjectUtils.isEmpty(applicationDTO)) {
            log.error("参数{}为空", applicationDTO);
            throw new RuntimeException("参数为空");
        }
        String customer = applicationDTO.getCustomer();
        String applicant = applicationDTO.getApplicant();
        String applicationDate = applicationDTO.getApplicationDate();
        //为了程序的严谨性，判断非空
        if (pageNum == null) {
            //设置默认当前页
            pageNum = 1;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 5);

        List<Application> applicationList = applicationService.findByCondition(applicationDTO);
        PageInfo<Application> pageInfo = new PageInfo<Application>(applicationList, 5);
        //4.使用model/map/modelandview等带回前端
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("applicationDTO", applicationDTO);
        model.addAttribute("url", "applicationBycondition");
        return "application/list";
    }

    @RequestMapping(value = "/application/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delApplication(@PathVariable("id") Integer id) {
        Application application = new Application();
        application.setId(id);
        application.setStatus(0);
        application.setUpdateDate(new Date());
        application.setUpdator(httpSession.getAttribute("LoginState").toString());
        try {
            applicationService.update(application);
        } catch (Exception e) {
            log.error("根据主键逻辑删除失败");
            throw new RuntimeException("根据主键逻辑删除失败");
        }
        return true;
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("selectOne/{id}")
    @ResponseBody
    public Result selectOne(@PathVariable Integer id) {
        return applicationService.queryById(id);
    }
}