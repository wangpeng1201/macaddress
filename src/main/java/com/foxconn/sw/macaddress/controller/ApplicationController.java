package com.foxconn.sw.macaddress.controller;

import com.foxconn.sw.macaddress.service.ApplicationService;
import com.foxconn.sw.macaddress.vo.ApplicationVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * mac地址申请单(Application)表控制层
 *
 * @author makejava
 * @since 2020-10-16 14:06:29
 */
@Controller
public class ApplicationController {
    /**
     * 服务对象
     */
    @Resource
    private ApplicationService applicationService;

    @GetMapping("/applications")
    public String applications(Model model,
                               @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, 5);
        try {
            List<ApplicationVO> applications = applicationService.getAll();
            //使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<ApplicationVO> pageInfo = new PageInfo<ApplicationVO>(applications, 5);
            //使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "application/list";
    }

    @PostMapping("/applications")
    public String addApplication(ApplicationVO applicationVO) {
        applicationService.addApplication(applicationVO);
        return "redirect:/applications";
    }
}