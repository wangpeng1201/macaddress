package com.foxconn.sw.macaddress.controller;

import com.alibaba.fastjson.JSON;
import com.foxconn.sw.macaddress.common.Box;
import com.foxconn.sw.macaddress.common.Lay;
import com.foxconn.sw.macaddress.common.Result;
import com.foxconn.sw.macaddress.common.RetResponse;
import com.foxconn.sw.macaddress.dto.DeliveryRecordConditionDTO;
import com.foxconn.sw.macaddress.dto.MacAddressDTO;
import com.foxconn.sw.macaddress.entity.DeliveryRecord;
import com.foxconn.sw.macaddress.service.DeliveryRecordService;
import com.foxconn.sw.macaddress.vo.ApplicationVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * mac地址交付记录，此表代表申请提交后从mac地址表中分配的记录，若存在跨段(申请数量大于mac地址表该段可用数量)，则同一个mac_id生成多个记录(DeliveryRecord)表控制层
 *
 * @author makejava
 * @since 2020-10-21 17:24:25
 */
@Log4j2
@Controller
public class DeliveryRecordController {
    /**
     * 服务对象
     */
    @Resource
    private DeliveryRecordService deliveryRecordService;

    @Autowired
    private HttpSession httpSession;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectDeliveryRecord/{id}")
    @ResponseBody
    public Result selectOne(@PathVariable Integer id) {
        return this.deliveryRecordService.queryById(id);
    }

    @GetMapping("/deliveryRecords")
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
            Result result = deliveryRecordService.queryAll();
            if (Integer.toString(HttpStatus.OK.value()).equals(result.getCode().toString())) {
                List<DeliveryRecord> deliveryRecords = (List<DeliveryRecord>) result.getData();
                //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
                PageInfo<DeliveryRecord> pageInfo = new PageInfo<DeliveryRecord>(deliveryRecords, 5);
                //4.使用model/map/modelandview等带回前端
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("url", "deliveryRecords");
            } else {
                return "error/5xx";
            }
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "deliveryRecord/list";
    }

    @PostMapping(value = "/deliveryRecordByCondition")
    @ResponseBody
    public Box findByCondition(DeliveryRecordConditionDTO deliveryRecordConditionDTO) throws ParseException {
        Lay byConditionLayUI = deliveryRecordService.findByConditionLayUI(deliveryRecordConditionDTO);
        Long count = byConditionLayUI.getCount();
        Object data = byConditionLayUI.getData();
        return Box.success(data).put("count", count);
    }

    @PostMapping("/assignMac")
    @ResponseBody
    public Result assignMac(ApplicationVO applicationVO) {
        Result result = deliveryRecordService.assignMac(applicationVO);
        return result;
    }

    @RequestMapping(value = "/deliveryRecord/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delMacAddress(@PathVariable("id") Integer id) {
        DeliveryRecord deliveryRecord = new DeliveryRecord();
        deliveryRecord.setId(id);
        //逻辑删除
        deliveryRecord.setStatus(0);
        deliveryRecord.setUpdatedate(new Date());
        deliveryRecord.setUpdator(httpSession.getAttribute("LoginState").toString());
        try {
            Boolean update = deliveryRecordService.update(deliveryRecord);
            if (update) {
                return RetResponse.success("删除成功");
            } else {
                return RetResponse.success("删除失败");
            }
        } catch (Exception e) {
            log.error("根据主键逻辑删除失败");
            return RetResponse.error("删除失败");
        }
    }
}