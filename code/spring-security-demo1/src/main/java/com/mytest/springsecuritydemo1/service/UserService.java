package com.mytest.springsecuritydemo1.service;

import com.mytest.springsecuritydemo1.common.base.BaseResp;
import com.mytest.springsecuritydemo1.common.enums.UserErrorEnum;
import com.mytest.springsecuritydemo1.common.util.JwtTokenUtil;
import com.mytest.springsecuritydemo1.dao.BizUserMapperExtend;
import com.mytest.springsecuritydemo1.dao.BizUserRoleMapperExtend;
import com.mytest.springsecuritydemo1.model.dto.UserDetailsDTO;
import com.mytest.springsecuritydemo1.model.dto.UserPermissionDTO;
import com.mytest.springsecuritydemo1.model.entity.BizPermission;
import com.mytest.springsecuritydemo1.model.entity.BizUser;
import com.mytest.springsecuritydemo1.model.req.AddUserReq;
import com.mytest.springsecuritydemo1.model.req.QueryUserReq;
import com.mytest.springsecuritydemo1.model.req.SignInReq;
import com.mytest.springsecuritydemo1.model.resp.AddUserResp;
import com.mytest.springsecuritydemo1.model.resp.QueryUserResp;
import com.mytest.springsecuritydemo1.model.resp.SignInResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService extends BaseService {

    @Autowired
    private BizUserMapperExtend bizUserMapperExtend;

    @Autowired
    private BizUserRoleMapperExtend bizUserRoleMapperExtend;

    public QueryUserResp queryUser(QueryUserReq req) {
        QueryUserResp resp = new QueryUserResp();
        HashMap<String, Object> condition = new HashMap<>();
        List<BizUser> userList = bizUserMapperExtend.selectByCondition(condition);
        resp.setUserList(userList);
        return resp;
    }

    public BaseResp<AddUserResp> addUser(AddUserReq req) {
        AddUserResp resp = new AddUserResp();
        BizUser user = new BizUser();
        user.setAge(req.getAge());
        user.setDesc(req.getDesc());
        user.setName(req.getName());
        user.setCreateBy(this.getUserId());
        user.setCreateTime(new Date());
        user.setPhone(req.getPhone());
        user.setDisabled(false);
        user.setEmail(req.getEmail());
        bizUserMapperExtend.insertSelective(user);
        return BaseResp.success(resp);
    }

    public BaseResp signIn(SignInReq req) {
        UserPermissionDTO userPermissionDTO = bizUserRoleMapperExtend.selectUserByUserName(req.getUserName());
        if (userPermissionDTO == null) {
            return BaseResp.error(UserErrorEnum.USER_NOT_EXIST);
        }
        if (!userPermissionDTO.getPassword().equals(req.getPassword())) {
            return BaseResp.error(UserErrorEnum.PASSWORD_NOT_RIGHT);
        }
        SignInResp resp = new SignInResp();
        List<String> userPermission = new ArrayList<>();
        //获取用户所有角色
        for (BizPermission permission : userPermissionDTO.getPermissionList()) {
            userPermission.add(permission.getName());
        }
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setUserId(userPermissionDTO.getId().toString());
        userDetailsDTO.setPermission(userPermission);
        userDetailsDTO.setEmail(userPermissionDTO.getEmail());
        userDetailsDTO.setEnabled(userPermissionDTO.getDisabled());
        userDetailsDTO.setExpired(false);
        userDetailsDTO.setLocked(false);
        userDetailsDTO.setPassword(userPermissionDTO.getPassword());
        userDetailsDTO.setUserName(userPermissionDTO.getName());
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        resp.setToken(JwtTokenUtil.JWT_TOKEN_PREFIX + " " + jwtTokenUtil.createToken(userDetailsDTO));
        return BaseResp.success(resp);
    }
}
