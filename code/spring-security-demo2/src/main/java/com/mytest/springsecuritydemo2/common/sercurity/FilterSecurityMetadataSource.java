package com.mytest.springsecuritydemo2.common.sercurity;

import com.mytest.springsecuritydemo2.model.entity.BizPermission;
import com.mytest.springsecuritydemo2.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class FilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static final Map<String, List<ConfigAttribute>> urlPermissionMap = new HashMap<>();

    @Autowired
    private PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String method = fi.getHttpRequest().getMethod();
        String resourceId = url + "_" + method;
        if (urlPermissionMap.containsKey(resourceId)) {
            return urlPermissionMap.get(resourceId);
        }
        List<BizPermission> permissionList = permissionService.queryPermissionByResource(url, method);
        if (!CollectionUtils.isEmpty(permissionList)) {
            String[] attributes = new String[permissionList.size()];
            for (int i = 0; i < permissionList.size(); i++) {
                attributes[i] = permissionList.get(i).getName();
            }
            List<ConfigAttribute> configAttributes = SecurityConfig.createList(attributes);
            urlPermissionMap.put(resourceId, configAttributes);
            return configAttributes;
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<ConfigAttribute> attributeList = new ArrayList<>();
        for (String resourceId : urlPermissionMap.keySet()) {
            attributeList.addAll(urlPermissionMap.get(resourceId));
        }
        return attributeList;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
