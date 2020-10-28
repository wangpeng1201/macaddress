package com.foxconn.sw.macaddress.common;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Project Name: el
 * Des:
 * Package:com.ssbu.el.beans
 * Created by Arlo on 2019/7/12 - 10:43
 */
public class Box extends HashMap<String, Object> implements Serializable {

    final private static String success = "success";

    final private static String fail = "fail";

    private Box(Integer code, String message, Object data, Boolean successful) {
        put("code", code);
        put("message", message);
        put("successful", successful);
        if (Objects.nonNull(data)) {
            put("data", data);
        }
    }

    public static Box success() {
        return instance(200, success, null, true);
    }

    public static Box success(String message) {
        return instance(200, message, null, true);
    }

    public static Box success(Object data) {
        return instance(200, success, data, true);
    }

    public static Box instance(Integer code, String message, Object data, Boolean successful) {
        return new Box(code, message, data, successful);
    }

    public static Box fail(Exception e) {
        return instance(406, e.getMessage(), e, false);
    }

    public static Box fail() {
        return instance(406, fail, null, false);
    }

    public static Box fail(String message) {
        return instance(406, message, null, false);
    }

    public static Box uploadSuccess() {
        return instance(0, "File verification success", null, true);
    }

    public static Box uploadFail(String message) {
        return instance(500, message, null, false);
    }

    /**
     * @param key   鍵
     * @param value 值
     * @return Box
     * @desc 用以追加屬性，lay-table  必須要有data是數據，還要有count字段， 蛋疼
     */
    @Override
    public Box put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * @desc 兼容lay-table 的分页
     * @param page JPA 分页查询的结果
     * @return Box
     */
    public static Box successLayPage(Page page) {
        List content = page.getContent();
        return success(content).put("count", page.getTotalElements());
    }
}
