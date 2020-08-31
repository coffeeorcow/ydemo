package com.yi.demo.sample.config;

import com.google.common.base.Joiner;
import com.yi.demo.sample.common.enums.ResultCodeEnum;
import com.yi.demo.sample.common.exception.YException;
import com.yi.demo.sample.model.YResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@ControllerAdvice
public class YExceptionHandler {

    private String parseErrorMessage(List<ObjectError> allErrors) {
        return Joiner.on(" | ").join(allErrors);
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public YResult handleBindException(BindException e) {
        return YResult.of(ResultCodeEnum.PARAM_VALIDATION_ERROR.getCode(), parseErrorMessage(e.getAllErrors()));
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public YResult handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return YResult.of(ResultCodeEnum.PARAM_VALIDATION_ERROR.getCode(), parseErrorMessage(e.getBindingResult().getAllErrors()));
    }

    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public YResult handleNoHandlerException(NoHandlerFoundException e) {
        return YResult.of(ResultCodeEnum.HANDLER_NOT_FOUND.getCode(), ResultCodeEnum.HANDLER_NOT_FOUND.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(YException.class)
    public YResult handleYException(YException e) {
        return YResult.of(e.getCode(), e.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public YResult handleThrowable(Throwable t) {
        return YResult.fail(t.getMessage());
    }

}
