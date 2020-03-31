package com.mytest.springsecuritydemo2.model.req;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddUserReq {

    @NotNull(message = "用户名不能为空")
    @NotEmpty(message = "用户名不能为空")
    private String name;

    @Min(value = 1, message = "年龄不合法")
    private Integer age;

    private String desc;

    @NotNull(message = "电话号码不能为空")
    @NotEmpty(message = "电话号码不能为空")
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;
}
