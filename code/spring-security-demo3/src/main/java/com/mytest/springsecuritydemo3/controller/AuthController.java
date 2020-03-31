package com.mytest.springsecuritydemo3.controller;

import com.mytest.springsecuritydemo3.common.base.BaseResp;
import com.mytest.springsecuritydemo3.model.req.SignInReq;
import com.mytest.springsecuritydemo3.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@Api(value = "授权管理", tags = {"授权管理"})
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/signin", method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation("登录")
    public BaseResp signIn(@Validated @RequestBody SignInReq req) {
        return this.userService.signIn(req);
    }

    @RequestMapping(value = "/signout", method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation("登出")
    public BaseResp signOut() {
        return BaseResp.success("登出成功");
    }
}
