package com.mytest.springsecuritydemo2.service;

import com.mytest.springsecuritydemo2.model.dto.UserDetailsDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseService {

    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
        return userDetailsDTO.getUserId();
    }
}
