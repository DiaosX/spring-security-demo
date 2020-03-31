package com.mytest.springsecuritydemo1.controller;

import com.mytest.springsecuritydemo1.common.base.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/product")
@Api(value = "产品管理", tags = {"产品管理"})
public class ProductController {

    @RequestMapping(value = "/info", method = {RequestMethod.GET})
    @ApiOperation("获取产品信息")
    @PreAuthorize("hasAuthority('product:info')")
    public BaseResp productInfo() {
        return BaseResp.success("这个是产品");
    }
}
