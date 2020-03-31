package com.mytest.springsecuritydemo3.controller;

import com.mytest.springsecuritydemo3.common.base.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/admin")
@Api(value = "系统管理", tags = {"系统管理"})
public class AdminController {

    @RequestMapping(value = "/info", method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation("获取管理员信息,拥有[admin:info]权限才能访问")
    @Secured("admin:info")
    public BaseResp adminInfo() {
        return BaseResp.success("这个是管理员");
    }

    @RequestMapping(value = "/info", method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation("获取管理员信息,拥有[admin:info]权限才能访问")
    @Secured("admin:info")
    public BaseResp adminInfo1() {
        return BaseResp.success("这个是管理员");
    }

    @ApiOperation("任何人都可以访问此接口")
    @RequestMapping(value = "/accessToAny", method = {RequestMethod.GET})
    @ResponseBody
    public BaseResp accessToAny() {
        return BaseResp.success("任何人都可以访问");
    }
}
