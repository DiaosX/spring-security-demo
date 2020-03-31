package com.mytest.springsecuritydemo2.common.sercurity;

import com.mytest.springsecuritydemo2.common.base.BaseException;
import com.mytest.springsecuritydemo2.common.enums.UserErrorEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录过滤器
 */
public class JwtSignInProcessingFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * 拦截/signin路径，从http的query参数获取用户名和密码
     */
    public JwtSignInProcessingFilter() {
        super(new AntPathRequestMatcher("/auth/signin", "GET"));
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            String userName = request.getParameter("userName");
            if (StringUtils.isEmpty(userName)) {
                throw new BaseException(UserErrorEnum.USERNAME_CAN_NOT_EMPTY);
            }
            String password = request.getParameter("password");
            if (StringUtils.isEmpty(password)) {
                throw new BaseException(UserErrorEnum.PASSWORD_CAN_NOT_EMPTY);
            }
            //创建未认证的凭证(etAuthenticated(false)),注意此时凭证中的主体principal为用户名
            JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(userName, password);
            //将认证详情(ip,sessionId)写到凭证
            jwtAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
            //AuthenticationManager获取受支持的AuthenticationProvider(这里也就是JwtAuthenticationProvider),
            //生成已认证的凭证,此时凭证中的主体为userDetails
            return this.getAuthenticationManager().authenticate(jwtAuthenticationToken);
        } catch (Exception e) {
            throw new BadCredentialsException("坏的凭证", e);
        }
    }
}
