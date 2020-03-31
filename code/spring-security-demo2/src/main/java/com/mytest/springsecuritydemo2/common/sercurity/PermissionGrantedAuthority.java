package com.mytest.springsecuritydemo2.common.sercurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

/**
 * 角色职权
 */
public class PermissionGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String permission;

    public PermissionGrantedAuthority(String permission) {
        Assert.hasText(permission, "A granted authority textual representation is required");
        this.permission = permission;
    }

    @Override
    public String getAuthority() {
        return permission;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof PermissionGrantedAuthority) {
            return permission.equals(((PermissionGrantedAuthority) obj).permission);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.permission.hashCode();
    }

    @Override
    public String toString() {
        return this.permission;
    }
}
