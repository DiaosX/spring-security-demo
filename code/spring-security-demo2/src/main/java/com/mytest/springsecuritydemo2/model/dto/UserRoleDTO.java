package com.mytest.springsecuritydemo2.model.dto;

import com.mytest.springsecuritydemo2.model.entity.BizRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserRoleDTO {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer age;

    /**
     *
     */
    private String desc;

    /**
     *
     */
    private String createBy;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private String updateBy;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Boolean disabled;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private String email;

    private String password;
    /**
     * 角色列表
     */
    private List<BizRole> roleList;
}
