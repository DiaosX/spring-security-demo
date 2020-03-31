package com.mytest.springsecuritydemo2.common.sercurity;

import com.alibaba.fastjson.JSON;
import com.mytest.springsecuritydemo2.common.base.BaseResp;
import com.mytest.springsecuritydemo2.common.util.JwtTokenUtil;
import com.mytest.springsecuritydemo2.model.dto.UserDetailsDTO;
import com.mytest.springsecuritydemo2.model.resp.SignInResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 * 登录成功后返回jwt token
 */
public class JwtSignInSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetailsDTO userDetails = (UserDetailsDTO) authentication.getPrincipal();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String jwtToken = jwtTokenUtil.createToken(userDetails);
        SignInResp signInResp = new SignInResp();
        signInResp.setToken(JwtTokenUtil.JWT_TOKEN_PREFIX + " " + jwtToken);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(JSON.toJSONString(BaseResp.success(signInResp)));
    }

    private RsaSigner signer;

    public void setSigner(RsaSigner signer) {
        this.signer = signer;
    }
}
