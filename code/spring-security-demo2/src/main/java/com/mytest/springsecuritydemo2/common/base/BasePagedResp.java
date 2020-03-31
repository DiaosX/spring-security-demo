package com.mytest.springsecuritydemo2.common.base;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePagedResp<T> extends BaseResp<T> {
    private BasePagedResult<T> pager;

    public BasePagedResp() {

    }

    public BasePagedResp(BaseError errorInfo) {
        this(errorInfo.getCode(), errorInfo.getErrMsg());
    }

    public BasePagedResp(String code, String errMsg) {
        this.success = false;
        this.code = code;
        this.errMsg = errMsg;
    }

    public BasePagedResp(String code, BasePagedResult<T> pager) {
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
    public static <T> BasePagedResp<T> success(BasePagedResult<T> pager) {
        return new BasePagedResp<>(BaseErrorEnum.SUCCESS.getCode(), pager);
    }

    /**
     * 失败
     */
    public static BasePagedResp error(BaseError errorInfo) {
        return new BasePagedResp(errorInfo);
    }

    /**
     * 失败
     */
    public static BasePagedResp error(String code, String message) {
        return new BasePagedResp(code, message);
    }

    /**
     * 失败
     */
    public static BasePagedResp error(String message) {
        return new BasePagedResp("-1", message);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
