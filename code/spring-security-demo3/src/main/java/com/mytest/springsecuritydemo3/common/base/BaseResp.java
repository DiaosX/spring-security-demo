package com.mytest.springsecuritydemo3.common.base;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResp<T> {
    protected boolean success;
    protected String code;
    protected String errMsg;
    protected T data;

    public BaseResp() {

    }

    public BaseResp(BaseError errorInfo) {
        this(errorInfo.getCode(), errorInfo.getErrMsg());
    }

    public BaseResp(String code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
        this.success = false;
    }

    public BaseResp(String code, T data) {
        this.code = code;
        this.data = data;
        this.success = true;
    }

    /**
     * 成功
     *
     * @return
     */
    public static BaseResp success() {
        return BaseResp.success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static <T> BaseResp<T> success(T data) {
        return new BaseResp<>(BaseErrorEnum.SUCCESS.getCode(), data);
    }

    /**
     * 失败
     */
    public static BaseResp error(BaseError errorInfo) {
        return new BaseResp(errorInfo);
    }

    /**
     * 失败
     */
    public static BaseResp error(String code, String message) {
        return new BaseResp(code, message);
    }

    /**
     * 失败
     */
    public static BaseResp error(String message) {
        return new BaseResp("-1", message);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}