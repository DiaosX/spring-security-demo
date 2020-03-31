package com.mytest.springsecuritydemo2.controller;

import com.mytest.springsecuritydemo2.common.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("获取管理员信息")
    public BaseResponse adminInfo() {
        return BaseResponse.success("这个是管理员");
    }

    @RequestMapping(value = "/info", method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation("获取管理员信息")
    public BaseResponse adminInfo1() {
        return BaseResponse.success("这个是管理员");
    }

    @RequestMapping(value = "/accessToAny", method = {RequestMethod.GET})
    @ResponseBody
    public BaseResponse accessToAny() {
        return BaseResponse.success("任何人都可以访问");
    }
}
