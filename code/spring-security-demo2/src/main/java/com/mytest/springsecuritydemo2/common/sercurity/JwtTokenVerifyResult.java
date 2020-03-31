package com.mytest.springsecuritydemo2.common.sercurity;

import com.mytest.springsecuritydemo2.common.base.BaseResponse;
import io.jsonwebtoken.Claims;

public class JwtTokenVerifyResult extends BaseResponse<Claims> {

    public JwtTokenVerifyResult() {
        super.success = false;
    }
}
