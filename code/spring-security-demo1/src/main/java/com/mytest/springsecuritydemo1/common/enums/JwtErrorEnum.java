package com.mytest.springsecuritydemo1.common.enums;

import com.mytest.springsecuritydemo1.common.base.BaseError;

/**
 * 认证token相关枚举
 */
public enum JwtErrorEnum implements BaseError {

    TOKEN_EXPIRED("20001", "token过期"),
    TOKEN_FORMAT_ERROR("20002", "token格式错误"),
    TOKEN_SIGNATURE_ERROR("20003", "token签名错误"),
    TOKEN_INVALID_ERROR("20004", "token无效"),
    TOKEN_CAN_NOT_EMPTY("20004", "认证token不能为空");

    JwtErrorEnum(String code, String errMsg) {
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
