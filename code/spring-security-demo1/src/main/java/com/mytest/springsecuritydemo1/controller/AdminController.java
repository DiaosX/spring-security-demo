package com.mytest.springsecuritydemo1.controller;

import com.mytest.springsecuritydemo1.common.base.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @ApiOperation("获取管理员信息,需要[admin:info]权限才能访问")
    @PreAuthorize("hasAuthority('admin:info')")
    public BaseResp adminInfo() {
        return BaseResp.success("这个是管理员");
    }

    @RequestMapping(value = "/info", method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation("获取管理员信息，需要[admin:info]权限才能访问")
    @PreAuthorize("hasAuthority('admin:info')")
    public BaseResp adminInfo1() {
        return BaseResp.success("这个是管理员");
    }

    @RequestMapping(value = "/accessToAny", method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation("任何权限都能访问")
    public BaseResp accessToAny() {
        return BaseResp.success("任何人都可以访问");
    }
}
