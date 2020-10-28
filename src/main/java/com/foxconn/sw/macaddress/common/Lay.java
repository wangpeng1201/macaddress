package com.foxconn.sw.macaddress.common;

import lombok.Data;

//layui数据表格帮助类
@Data
public class Lay {
    private Integer limit;//每页显示条数

    private Integer page;//当前页数

    private int code;

    private String msg;

    private Long count;//返回数据总条数

    private Object data;
}

