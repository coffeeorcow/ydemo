package com.yi.demo.sample.model;

import com.yi.demo.sample.common.enums.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class YResult extends LinkedHashMap<String, Object> {

    private int code;
    private String msg;
    private Object data;

    public YResult(int code, String msg) {
        this.put("code", code);
        this.put("msg", msg);
    }

    public YResult(int code, String msg, Object data) {
        this.put("code", code);
        this.put("msg", msg);
        this.put("data", data);
    }

    public static YResult of(int code, String msg) {
        return new YResult(code, msg);
    }

    public static YResult of(int code, String msg, Object data) {
        return new YResult(code, msg, data);
    }

    public static YResult success() {
        return new YResult(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg());
    }

    public static YResult success(String msg) {
        return new YResult(ResultCodeEnum.SUCCESS.getCode(), msg);
    }

    public static YResult successWith(Object data) {
        return new YResult(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg(), data);
    }

    public static YResult successWith(String msg, Object data) {
        return new YResult(ResultCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static YResult fail() {
        return new YResult(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMsg());
    }

    public static YResult fail(String msg) {
        return new YResult(ResultCodeEnum.ERROR.getCode(), msg);
    }

}
