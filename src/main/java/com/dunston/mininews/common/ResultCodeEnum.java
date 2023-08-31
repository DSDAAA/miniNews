package com.dunston.mininews.common;

/**
 * 返回信息Code编码
 *
 * @author dunston
 */
public enum ResultCodeEnum {

    SUCCESS(200, "success"),
    USERNAME_ERROR(501, "usernameError"),
    PASSWORD_ERROR(503, "passwordError"),
    NOTLOGIN(504, "notLogin"),
    SYSTEM_ERROR(40000, "systemError"),
    USERNAME_USED(505, "userNameUsed");

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
