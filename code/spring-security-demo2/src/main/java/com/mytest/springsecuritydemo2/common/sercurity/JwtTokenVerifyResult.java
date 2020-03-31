package com.mytest.springsecuritydemo2.common.sercurity;

import com.mytest.springsecuritydemo2.common.base.BaseResp;
import io.jsonwebtoken.Claims;

public class JwtTokenVerifyResult extends BaseResp<Claims> {

    public JwtTokenVerifyResult() {
        super.success = false;
    }
}
