package com.mytest.springsecuritydemo3.service;

import com.mytest.springsecuritydemo3.dao.BizPermissionMapperExtend;
import com.mytest.springsecuritydemo3.model.entity.BizPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PermissionService extends BaseService {

    @Autowired
    private BizPermissionMapperExtend permissionMapperExtend;

    public List<BizPermission> queryPermissionByResource(String url, String method) {
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("url", url);
        condition.put("method", method);
        condition.put("disabled", 0);
        List<BizPermission> permissionList = permissionMapperExtend.selectByCondition(condition);
        return permissionList;
    }
}
