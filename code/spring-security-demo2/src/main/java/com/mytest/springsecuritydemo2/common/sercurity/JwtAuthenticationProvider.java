package com.mytest.springsecuritydemo2.common.sercurity;

import com.mytest.springsecuritydemo2.common.enums.UserErrorEnum;
import com.mytest.springsecuritydemo2.model.dto.UserDetailsDTO;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    /**
     * 供根据用户名查询用户,获取UserDetails的方法
     */
    private UserDetailsService userDetailsService;
    /**
     * 提供加密方式,密码验证时,需要加密后进行对比
     */
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        //获取用户
        UserDetailsDTO userDetails = (UserDetailsDTO) userDetailsService.loadUserByUsername(userName);
        //转换authentication 认证时会先调用support方法,受支持才会调用,所以能强转
        JwtAuthenticationToken jwtLoginToken = (JwtAuthenticationToken) authentication;
        //检查
        defaultCheck(userDetails);
        additionalAuthenticationChecks(userDetails, jwtLoginToken);
        //构造已认证的authentication
        JwtAuthenticationToken authenticatedToken = new JwtAuthenticationToken(userDetails, jwtLoginToken.getCredentials(), userDetails.getAuthorities());
        authenticatedToken.setDetails(jwtLoginToken.getDetails());
        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private void additionalAuthenticationChecks(UserDetails userDetails,
                                                JwtAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("Bad credentials");
        }
        //密码不相等
        String presentedPassword = authentication.getCredentials().toString();
        if (!presentedPassword.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Bad credentials:" + UserErrorEnum.PASSWORD_NOT_RIGHT.getErrMsg());
        }
    }

    /**
     * 用户默认检查,用户是否锁定过期等
     */
    private void defaultCheck(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            throw new LockedException("User account is locked");
        }

        if (!user.isEnabled()) {
            throw new DisabledException("User is disabled");
        }

        if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("User account has expired");
        }
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
