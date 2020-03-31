package com.mytest.springsecuritydemo2.controller;

import com.mytest.springsecuritydemo2.common.base.BaseResponse;
import com.mytest.springsecuritydemo2.model.req.AddUserReq;
import com.mytest.springsecuritydemo2.model.req.QueryUserReq;
import com.mytest.springsecuritydemo2.model.resp.AddUserResp;
import com.mytest.springsecuritydemo2.model.resp.QueryUserResp;
import com.mytest.springsecuritydemo2.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@Api(value = "用户管理", tags = {"用户管理"})
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("新增用户")
    @PostMapping("/add")
    public BaseResponse<AddUserResp> add(@Validated @RequestBody AddUserReq req) {
        return BaseResponse.success(userService.addUser(req));
    }

    @ApiOperation("查询用户")
    @PostMapping("/query")
    public BaseResponse<QueryUserResp> queryUser(@RequestBody QueryUserReq req) {
        return BaseResponse.success(userService.queryUser(req));
    }
}
