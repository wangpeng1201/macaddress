package com.foxconn.sw.macaddress.controller;

import com.foxconn.sw.macaddress.dto.MacAddressDTO;
import com.foxconn.sw.macaddress.entity.Macaddress;
import com.foxconn.sw.macaddress.service.MacaddressService;
import com.foxconn.sw.macaddress.vo.MacAddressVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * (Macaddress)表控制层
 *
 * @author makejava
 * @since 2020-10-13 11:23:48
 */
@Slf4j
@Controller
public class MacaddressController {
    /**
     * 服务对象
     */
    @Resource
    private MacaddressService macaddressService;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Macaddress selectOne(Integer id) {
        return this.macaddressService.queryById(id);
    }

    //    @GetMapping("list")
    public String getAll(Model model, HttpServletRequest request) {
        List<Macaddress> macAddressList = macaddressService.queryAll();
        model.addAttribute("macAddressList", macAddressList);
        model.addAttribute("PROJECT_PATH", request.getContextPath());
        return "home/list";
    }

    @PostMapping("/addMacAddress")
    public String addMacAddressPage(MacAddressVO macAddressVo) {
        macaddressService.insertMacAddress(macAddressVo);
        return "redirect:/list";
    }

    /**
     * @param model   携带数据返回
     * @param pageNum 显示当前页--必传值
     * @return 前端页面
     */
    //分页查询数据
    @GetMapping("/list")
    public String usermanage(Model model,
                             @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum) {
        //为了程序的严谨性，判断非空
        if (pageNum == null) {
            //设置默认当前页
            pageNum = 1;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, 5);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Macaddress> macAddressList = macaddressService.queryAll();
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Macaddress> pageInfo = new PageInfo<Macaddress>(macAddressList, 5);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "home/list";
    }

    /**
     * 条件查询
     *
     * @param model
     * @param macAddressDTO
     * @return
     */
    @PostMapping("/condition")
    public String findByCondition(Model model, @RequestParam MacAddressDTO macAddressDTO) {
        if (ObjectUtils.isEmpty(macAddressDTO)) {
            log.error("参数{}为空", macAddressDTO);
            throw new RuntimeException("参数为空");
        }
        Date createDate = macAddressDTO.getCreateDate();
        String startMacAddress = macAddressDTO.getStartMacAddress();
        Integer pageNum = macAddressDTO.getPageNum();
        PageHelper.startPage(pageNum, 5);
        List<Macaddress> macAddressList = macaddressService.findByStartMacAddressAndCreateDate(macAddressDTO);
        model.addAttribute("macAddressList", macAddressList);
        return "home/list";
    }

    @RequestMapping(value = "/mac/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delMacAddress(@PathVariable("id") Integer id) {
        Macaddress macaddress = new Macaddress();
        macaddress.setId(id);
        //逻辑删除
        macaddress.setStatus(0);
        macaddress.setUpdatedate(new Date());
        try {
            macaddressService.update(macaddress);
        } catch (Exception e) {
            log.error("根据主键逻辑删除失败");
            throw new RuntimeException("根据主键逻辑删除失败");
        }
        return true;
    }

}