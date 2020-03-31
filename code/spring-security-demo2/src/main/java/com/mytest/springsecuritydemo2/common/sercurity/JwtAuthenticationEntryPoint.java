package com.mytest.springsecuritydemo2.common.sercurity;

import com.alibaba.fastjson.JSON;
import com.mytest.springsecuritydemo2.common.base.BaseErrorEnum;
import com.mytest.springsecuritydemo2.common.base.BaseResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录时返回给前端的数据
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String responseBody = JSON.toJSONString(BaseResponse.error(BaseErrorEnum.NOT_AUTHENTICATION));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(responseBody);
    }
}
