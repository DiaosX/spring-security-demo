package com.mytest.springsecuritydemo3.common.base;

public interface BaseError {

    /**
     * 返回码
     */
    String getCode();

    /**
     * 错误描述
     */
    String getErrMsg();
}
