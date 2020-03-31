package com.mytest.springsecuritydemo2.common.sercurity;

import com.alibaba.fastjson.JSON;
import com.mytest.springsecuritydemo2.common.base.BaseResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtSignInFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = exception.getMessage();
        if (exception.getCause() != null) {
            errorMessage = errorMessage + ":" + exception.getCause().getMessage();
        }
        String responseBody = JSON.toJSONString(BaseResponse.error(errorMessage));
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(responseBody);
    }
}
