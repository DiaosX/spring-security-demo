package com.mytest.springsecuritydemo3.common.sercurity;

import com.mytest.springsecuritydemo3.common.base.BaseResp;
import io.jsonwebtoken.Claims;

public class JwtTokenVerifyResult extends BaseResp<Claims> {

    public JwtTokenVerifyResult() {
        super.success = false;
    }
}
