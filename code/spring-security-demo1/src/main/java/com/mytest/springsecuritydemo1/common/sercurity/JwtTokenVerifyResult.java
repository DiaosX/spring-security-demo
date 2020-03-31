package com.mytest.springsecuritydemo1.common.sercurity;

import com.mytest.springsecuritydemo1.common.base.BaseResp;
import io.jsonwebtoken.Claims;

public class JwtTokenVerifyResult extends BaseResp<Claims> {

    public JwtTokenVerifyResult() {
        super.success = false;
    }
}
