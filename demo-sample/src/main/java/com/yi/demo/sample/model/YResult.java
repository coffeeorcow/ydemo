package com.yi.demo.sample.model;

import com.yi.demo.sample.common.enums.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class YResult<T> extends LinkedHashMap<String, Object> {

    /**
     * 请求响应码
     */
    private int code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public YResult(int code, String msg) {
        this.put("code", code);
        this.put("msg", msg);
    }

    public YResult(int code, String msg, Object data) {
        this.put("code", code);
        this.put("msg", msg);
        this.put("data", data);
    }

    public static <T> YResult<T> of(int code, String msg) {
        return new YResult<>(code, msg);
    }

    public static <T> YResult<T> of(int code, String msg, Object data) {
        return new YResult<>(code, msg, data);
    }

    public static <T> YResult<T> success() {
        return new YResult<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg());
    }

    public static <T> YResult<T> success(String msg) {
        return new YResult<>(ResultCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> YResult<T> successWith(Object data) {
        return new YResult<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> YResult<T> successWith(String msg, Object data) {
        return new YResult<>(ResultCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> YResult<T> fail() {
        return new YResult<>(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMsg());
    }

    public static <T> YResult<T> fail(String msg) {
        return new YResult<>(ResultCodeEnum.ERROR.getCode(), msg);
    }

}
