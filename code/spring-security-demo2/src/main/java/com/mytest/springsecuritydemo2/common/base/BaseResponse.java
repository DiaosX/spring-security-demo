package com.mytest.springsecuritydemo2.common.base;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    protected boolean success;
    protected String code;
    protected String errMsg;
    protected T data;

    public BaseResponse() {

    }

    public BaseResponse(BaseError errorInfo) {
        this(errorInfo.getCode(), errorInfo.getErrMsg());
    }

    public BaseResponse(String code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
        this.success = false;
    }

    public BaseResponse(String code, T data) {
        this.code = code;
        this.data = data;
        this.success = true;
    }

    /**
     * 成功
     *
     * @return
     */
    public static BaseResponse success() {
        return BaseResponse.success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(BaseErrorEnum.SUCCESS.getCode(), data);
    }

    /**
     * 失败
     */
    public static BaseResponse error(BaseError errorInfo) {
        return new BaseResponse(errorInfo);
    }

    /**
     * 失败
     */
    public static BaseResponse error(String code, String message) {
        return new BaseResponse(code, message);
    }

    /**
     * 失败
     */
    public static BaseResponse error(String message) {
        return new BaseResponse("-1", message);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}