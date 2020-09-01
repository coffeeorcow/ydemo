package com.yi.demo.sample.config;

import com.yi.demo.sample.common.enums.ResultCodeEnum;
import com.yi.demo.sample.common.exception.YException;
import com.yi.demo.sample.model.YResult;
import org.springframework.util.CollectionUtils;
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
        if (!CollectionUtils.isEmpty(allErrors)) {
            return allErrors.get(0).getDefaultMessage();
        }
        return "请求参数有误";
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public YResult<Object> handleBindException(BindException e) {
        return YResult.of(ResultCodeEnum.PARAM_VALIDATION_ERROR.getCode(), parseErrorMessage(e.getAllErrors()));
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public YResult<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return YResult.of(ResultCodeEnum.PARAM_VALIDATION_ERROR.getCode(), parseErrorMessage(e.getBindingResult().getAllErrors()));
    }

    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public YResult<Object> handleNoHandlerException(NoHandlerFoundException e) {
        return YResult.of(ResultCodeEnum.HANDLER_NOT_FOUND.getCode(), ResultCodeEnum.HANDLER_NOT_FOUND.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(YException.class)
    public YResult<Object> handleYException(YException e) {
        return YResult.of(e.getCode(), e.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public YResult<Object> handleThrowable(Throwable t) {
        return YResult.fail(t.getMessage());
    }

}
