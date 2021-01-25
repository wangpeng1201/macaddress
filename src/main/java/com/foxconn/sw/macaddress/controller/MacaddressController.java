package com.foxconn.sw.macaddress.controller;

import com.foxconn.sw.macaddress.common.Box;
import com.foxconn.sw.macaddress.common.Lay;
import com.foxconn.sw.macaddress.common.Result;
import com.foxconn.sw.macaddress.common.RetResponse;
import com.foxconn.sw.macaddress.dto.MacAddressDTO;
import com.foxconn.sw.macaddress.entity.Macaddress;
import com.foxconn.sw.macaddress.service.MacaddressService;
import com.foxconn.sw.macaddress.vo.MacAddressEditVO;
import com.foxconn.sw.macaddress.vo.MacAddressVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private HttpSession httpSession;

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
        return "redirect:/list";
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
        PageHelper.startPage(pageNum, 10);
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
    @ResponseBody
    public Box findByCondition(MacAddressDTO macAddressDTO) {
        Lay byConditionLayUI = macaddressService.findByConditionLayUI(macAddressDTO);
        Long count = byConditionLayUI.getCount();
        Object data = byConditionLayUI.getData();
        return Box.success(data).put("count", count);
    }

    /**
     * 根据主键逻辑删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/mac/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delMacAddress(@PathVariable("id") Integer id) {
        Macaddress macaddress = new Macaddress();
        macaddress.setId(id);
        //逻辑删除
        macaddress.setStatus(0);
        macaddress.setUpdatedate(new Date());
        try {
            Macaddress update = macaddressService.update(macaddress);
            return RetResponse.success(update);
        } catch (Exception e) {
            log.error("根据主键逻辑删除失败");
            return RetResponse.error("删除失败");
        }

    }

    @PostMapping("/delMacAddress/{ids}")
    @ResponseBody
    public Result delIds(@PathVariable String ids) {
        Boolean flag = macaddressService.deleteBatch(ids);
        if (flag) {
            return RetResponse.success("批量删除成功");
        } else {
            return RetResponse.error("批量删除失败");
        }
    }

    /**
     * 根据主键查询对应的剩余库存
     *
     * @param macId
     * @return
     */
    @GetMapping("/getRemainingStock/{macId}")
    @ResponseBody
    public Result getRemainingStock(@PathVariable Integer macId) {
        return macaddressService.getRemainingStock(macId);
    }

    @PostMapping("editMacAddress")
    @ResponseBody
    public Result editOne(MacAddressEditVO macAddressEditVO) {
        Macaddress macaddress = new Macaddress();
        BeanUtils.copyProperties(macAddressEditVO, macaddress);
        long endLong = Long.parseLong(macAddressEditVO.getEndMacAddress(), 16);
        long startLong = Long.parseLong(macAddressEditVO.getStartMacAddress(), 16);
        macaddress.setStartingInventory((int) (endLong - startLong + 1));
        String signs = macAddressEditVO.getStartMacAddress().substring(0, 6);
        macaddress.setSigns(signs);
        macaddress.setUpdatedate(new Date());
        macaddress.setUpdator(httpSession.getAttribute("LoginState").toString());
        try {
            Macaddress update = macaddressService.update(macaddress);
            return RetResponse.success(update);
        } catch (Exception e) {
            log.error("修改失败");
            return RetResponse.error("修改失败");
        }
    }
}