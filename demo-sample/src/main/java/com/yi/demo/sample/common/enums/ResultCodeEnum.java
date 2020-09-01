package com.yi.demo.sample.common.enums;

import com.yi.demo.sample.common.exception.YException;
import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(0, "success"),

    DTO_NOT_FOUND(40001, "请求信息找不到"),
    HANDLER_NOT_FOUND(40404, "请求地址找不到"),

    ERROR(50000, "未知错误"),
    PARAM_VALIDATION_ERROR(50001, "请求参数错误")
    ;

    private final int code;
    private final String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public YException toException() {
        return new YException(this);
    }

}
