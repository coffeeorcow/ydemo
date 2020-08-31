package com.yi.demo.sample.common.exception;

import com.yi.demo.sample.common.enums.ResultCodeEnum;

public class YInfoNotFoundException extends YException {

    public YInfoNotFoundException() {
        super(ResultCodeEnum.DTO_NOT_FOUND);
    }

    public YInfoNotFoundException(String msg) {
        super(ResultCodeEnum.DTO_NOT_FOUND, msg);
    }

}
