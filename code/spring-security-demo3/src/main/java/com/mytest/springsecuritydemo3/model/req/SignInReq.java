package com.mytest.springsecuritydemo3.model.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SignInReq {
    @NotBlank(message = "用户名不能为空")
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    @NotEmpty(message = "密码不能为空")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
