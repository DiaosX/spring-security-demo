package com.mytest.springsecuritydemo1.common.enums;

import com.mytest.springsecuritydemo1.common.base.BaseError;

/**
 * 用户相关枚举
 */
public enum UserErrorEnum implements BaseError {
    USER_NOT_EXIST("10001", "用户不存在"),
    PASSWORD_NOT_RIGHT("10002", "密码不匹配"),
    USERNAME_CAN_NOT_EMPTY("10003", "用户名不能为空"),
    PASSWORD_CAN_NOT_EMPTY("10004", "密码不能为空");

    UserErrorEnum(String code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    private String code;
    private String errMsg;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }
}
