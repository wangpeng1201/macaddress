package com.foxconn.sw.macaddress.controller;

import com.alibaba.fastjson.JSON;
import com.foxconn.sw.macaddress.common.Result;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/addMacAddress")
    public String addMacAddressPage(MacAddressVO macAddressVo) {
        macaddressService.insertMacAddress(macAddressVo);
        return "redirect:/mac/list";
    }

    /**
     * @param model   携带数据返回
     * @param pageNum 显示当前页--必传值
     * @return 前端页面
     */
    //分页查询数据
    @GetMapping("/list")
    public String usermanage(Model model, HttpServletRequest request,
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
            model.addAttribute("url", "list");
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "home/list";
    }

    @RequestMapping(value = "/condition")
    public String findByCondition(Model model,
                                  @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                  @RequestParam String startMacAddress,
                                  @RequestParam String createdate) {
        //为了程序的严谨性，判断非空
        if (pageNum == null) {
            //设置默认当前页
            pageNum =1;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 2);
        MacAddressDTO macAddressDTO = new MacAddressDTO();
        macAddressDTO.setStartMacAddress(startMacAddress);
        macAddressDTO.setCreatedate(createdate);

        List<Macaddress> macAddressList = macaddressService.findByStartMacAddressAndCreateDate(macAddressDTO);
        PageInfo<Macaddress> pageInfo = new PageInfo<Macaddress>(macAddressList, 5);
        //4.使用model/map/modelandview等带回前端
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("macAddressDTO", macAddressDTO);
        model.addAttribute("PAGE", pageNum);
        model.addAttribute("url", "condition");
        System.err.println("pageInfo = " + JSON.toJSONString(pageInfo));
        return "home/list";
    }

    /**
     * 根据主键逻辑删除
     * @param id
     * @return
     */
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

    /**
     * 根据主键查询对应的剩余库存
     * @param macId
     * @return
     */
    @GetMapping("/getRemainingStock/{macId}")
    @ResponseBody
    public Result getRemainingStock(@PathVariable Integer macId) {
        return macaddressService.getRemainingStock(macId);
    }
}