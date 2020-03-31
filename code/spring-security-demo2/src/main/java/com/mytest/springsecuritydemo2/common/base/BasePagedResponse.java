package com.mytest.springsecuritydemo2.common.base;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePagedResponse<T> extends BaseResponse<T> {
    private BasePagedResult<T> pager;

    public BasePagedResponse() {

    }

    public BasePagedResponse(BaseError errorInfo) {
        this(errorInfo.getCode(), errorInfo.getErrMsg());
    }

    public BasePagedResponse(String code, String errMsg) {
        this.success = false;
        this.code = code;
        this.errMsg = errMsg;
    }

    public BasePagedResponse(String code, BasePagedResult<T> pager) {
        this.code = code;
        this.pager = pager;
        this.success = true;
    }

    /**
     * 成功
     *
     * @param pager
     * @return
     */
    public static <T> BasePagedResponse<T> success(BasePagedResult<T> pager) {
        return new BasePagedResponse<>(BaseErrorEnum.SUCCESS.getCode(), pager);
    }

    /**
     * 失败
     */
    public static BasePagedResponse error(BaseError errorInfo) {
        return new BasePagedResponse(errorInfo);
    }

    /**
     * 失败
     */
    public static BasePagedResponse error(String code, String message) {
        return new BasePagedResponse(code, message);
    }

    /**
     * 失败
     */
    public static BasePagedResponse error(String message) {
        return new BasePagedResponse("-1", message);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
