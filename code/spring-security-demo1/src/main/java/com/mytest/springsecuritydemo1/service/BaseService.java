package com.mytest.springsecuritydemo1.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseService {

    /**
     * 获取当前登录用户的用户ID
     *
     * @return 用户ID
     */
    public String getUserId() {
        //当前登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        return userId;
    }
}
