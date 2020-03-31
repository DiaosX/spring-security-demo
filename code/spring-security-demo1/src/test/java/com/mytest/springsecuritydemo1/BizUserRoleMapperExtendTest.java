package com.mytest.springsecuritydemo1;

import com.mytest.springsecuritydemo1.dao.BizUserRoleMapperExtend;
import com.mytest.springsecuritydemo1.model.dto.UserPermissionDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BizUserRoleMapperExtendTest {

    @Autowired
    private BizUserRoleMapperExtend bizUserRoleMapperExtend;

    @Test
    public void test() {
        String userName = "admin";
        UserPermissionDTO userPermissionDTO = this.bizUserRoleMapperExtend.selectUserByUserName(userName);
        System.out.println(userPermissionDTO.getName());

    }
}
