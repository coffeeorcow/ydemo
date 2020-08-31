package com.yi.demo.sample.common.exception;

import com.yi.demo.sample.common.enums.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class YException extends RuntimeException {

    private int code;

    private String msg;

    public YException(String msg) {
        this.code = ResultCodeEnum.ERROR.getCode();
        this.msg = msg;
    }

    public YException(ResultCodeEnum code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public YException(ResultCodeEnum code, String msg) {
        this.code = code.getCode();
        this.msg = msg;
    }

}
