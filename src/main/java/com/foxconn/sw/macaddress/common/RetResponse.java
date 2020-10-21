package com.foxconn.sw.macaddress.common;

import org.springframework.http.HttpStatus;

public class RetResponse {

    private final static String SUCCESS = "success";
    private final static String ERROR = "error";

    public static <T> Result<T> makeOKRsp() {
        return new Result<T>().setCode(HttpStatus.OK.value()).setMsg(SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>().setCode(HttpStatus.OK.value()).setMsg(SUCCESS).setData(data);
    }

    public static <T> Result<T> error(String message) {
//        return new Result<T>().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).setMsg(ERROR);
        return new Result<T>().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).setMsg(message);
    }

    public static <T> Result<T> makeRsp(int code, String msg) {
        return new Result<T>().setCode(code).setMsg(msg);
    }

    public static <T> Result<T> makeRsp(int code, String msg, T data) {
        return new Result<T>().setCode(code).setMsg(msg).setData(data);
    }

    /**
     * 参数错误
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> paramError(String msg, T data) {
        return new Result<T>().setCode(HttpStatus.BAD_REQUEST.value()).setMsg(msg).setData(data);
    }
}