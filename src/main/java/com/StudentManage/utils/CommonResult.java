package com.StudentManage.utils;


import com.StudentManage.enums.AppHttpCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public static <T> CommonResult<T> success(T t) {
        return new CommonResult<T>(200, "操作成功", t);
    }

    public static <T> CommonResult<T> error(T t) {
        return new CommonResult<T>(400, "操作失败", t);
    }

    public CommonResult() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public CommonResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public CommonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static CommonResult errorResult(int code, String msg) {
        CommonResult result = new CommonResult();
        return result.error(code, msg);
    }

    public static CommonResult okResult() {
        CommonResult result = new CommonResult();
        return result;
    }

    public static CommonResult okResult(int code, String msg) {
        CommonResult result = new CommonResult();
        return result.ok(code, null, msg);
    }

    public static CommonResult okResult(Object data) {
        CommonResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static CommonResult errorResult(AppHttpCodeEnum enums) {
        return setAppHttpCodeEnum(enums, enums.getMsg());
    }

    public static CommonResult errorResult(AppHttpCodeEnum enums, String msg) {
        return setAppHttpCodeEnum(enums, msg);
    }

    public static CommonResult setAppHttpCodeEnum(AppHttpCodeEnum enums) {
        return okResult(enums.getCode(), enums.getMsg());
    }

    private static CommonResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg) {
        return okResult(enums.getCode(), msg);
    }

    public CommonResult<?> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public CommonResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public CommonResult<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public CommonResult<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

//    @Override
//    public String toString() {
//        return "CommonResult{" +
//                "code=" + code +
//                ", msg='" + msg + '\'' +
//                ", data=" + data +
//                '}';
//    }
}

