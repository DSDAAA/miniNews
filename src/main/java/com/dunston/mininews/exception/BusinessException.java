package com.dunston.mininews.exception;

import lombok.Getter;

/**
 * 用户异常类
 *
 * @author dunston
 */
@Getter
public class BusinessException extends RuntimeException {
    /**
     * code 错误代码
     * message 错误信息
     * description 错误信息描述
     */
    private final int code;
    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }
}
