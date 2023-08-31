package com.dunston.mininews.exception.handler;

import com.dunston.mininews.common.Result;
import com.dunston.mininews.common.ResultCodeEnum;
import com.dunston.mininews.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 全局businessException处理方法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(BusinessException e) {
        log.error("businessException", e.getMessage(), e);
        return Result.build(e.getMessage(), e.getCode(), e.getDescription());
    }

    /**
     * 全局运行时异常处理方法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionhandler(RuntimeException e) {
        log.error("runtimException", e.getMessage(), e);
        return Result.build(e.getMessage(), ResultCodeEnum.SYSTEM_ERROR.getCode(), "");
    }
}
