/* https://github.com/orange1438 */
package com.mytest.springsecuritydemo2.dao;

import com.mytest.springsecuritydemo2.dao.auto.BizUserRoleMapper;
import com.mytest.springsecuritydemo2.model.dto.UserRoleDTO;

import javax.annotation.Resource;

/**
 * 本文件由 https://github.com/orange1438/mybatis-generator-core-chinese-annotation1.3.5-chinese-annotation 自动生成
 *
 * @author orange1438 code generator
 */
@Resource
public interface BizUserRoleMapperExtend extends BizUserRoleMapper {

    UserRoleDTO selectUserByUserName(String userName);
}