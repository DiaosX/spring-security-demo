package com.mytest.springsecuritydemo2.service;

import com.mytest.springsecuritydemo2.dao.BizUserMapperExtend;
import com.mytest.springsecuritydemo2.model.entity.BizUser;
import com.mytest.springsecuritydemo2.model.req.AddUserReq;
import com.mytest.springsecuritydemo2.model.req.QueryUserReq;
import com.mytest.springsecuritydemo2.model.resp.AddUserResp;
import com.mytest.springsecuritydemo2.model.resp.QueryUserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService extends BaseService {

    @Autowired
    private BizUserMapperExtend bizUserMapperExt;

    public QueryUserResp queryUser(QueryUserReq req) {
        QueryUserResp resp = new QueryUserResp();
        HashMap<String, Object> condition = new HashMap<>();
        List<BizUser> userList = bizUserMapperExt.selectByCondition(condition);
        resp.setUserList(userList);
        return resp;
    }

    public AddUserResp addUser(AddUserReq req) {
        AddUserResp resp = new AddUserResp();
        SecurityContextHolder.getContext().getAuthentication();
        BizUser user = new BizUser();
        user.setAge(req.getAge());
        user.setDesc(req.getDesc());
        user.setName(req.getName());
        user.setCreateBy(this.getUserId());
        user.setCreateTime(new Date());
        user.setPhone(req.getPhone());
        user.setDisabled(false);
        user.setEmail(req.getEmail());
        bizUserMapperExt.insertSelective(user);
        return resp;
    }
}
