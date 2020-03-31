package com.mytest.springsecuritydemo2.common.sercurity;

import com.mytest.springsecuritydemo2.dao.BizUserRoleMapperExtend;
import com.mytest.springsecuritydemo2.model.dto.UserDetailsDTO;
import com.mytest.springsecuritydemo2.model.dto.UserRoleDTO;
import com.mytest.springsecuritydemo2.model.entity.BizRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BizUserRoleMapperExtend bizUserRoleMapperExtend;

    /**
     * 根据用户名加载用户信息
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        UserRoleDTO userRoleDTO = bizUserRoleMapperExtend.selectUserByUserName(s);
        if (userRoleDTO == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //获取用户所有角色
        for (BizRole userRole : userRoleDTO.getRoleList()) {
            PermissionGrantedAuthority grantedAuthority = new PermissionGrantedAuthority(userRole.getName());
            userAuthorities.add(grantedAuthority);
        }
        UserDetailsDTO userDetails = new UserDetailsDTO();
        userDetails.setUserId(userRoleDTO.getId().toString());
        userDetails.setAuthorities(userAuthorities);
        userDetails.setEmail(userRoleDTO.getEmail());
        userDetails.setEnabled(userRoleDTO.getDisabled());
        userDetails.setExpired(false);
        userDetails.setLocked(false);
        userDetails.setPassword(userRoleDTO.getPassword());
        userDetails.setUserName(userRoleDTO.getName());
        return userDetails;
    }
}
