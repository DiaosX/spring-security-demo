/* https://github.com/orange1438 */
package com.mytest.springsecuritydemo2.model.entity;

import java.util.Date;

public class BizRolePermission {
    /** 
     */ 
    private Integer id;

    /** 
     */ 
    private Integer roleId;

    /** 
     */ 
    private Integer permissionId;

    /** 
     */ 
    private Boolean disabled;

    /** 
     */ 
    private String createBy;

    /** 
     */ 
    private Date createTime;

    /** 
     */ 
    private String updateBy;

    /** 
     */ 
    private Date updateTime;

    /** 
     * 获取 biz_role_permission.id
     * @return biz_role_permission.id
     */
    public Integer getId() {
        return id;
    }

    /** 
     * 设置 biz_role_permission.id
     * @param id biz_role_permission.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 
     * 获取 biz_role_permission.role_id
     * @return biz_role_permission.role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /** 
     * 设置 biz_role_permission.role_id
     * @param roleId biz_role_permission.role_id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /** 
     * 获取 biz_role_permission.permission_id
     * @return biz_role_permission.permission_id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /** 
     * 设置 biz_role_permission.permission_id
     * @param permissionId biz_role_permission.permission_id
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /** 
     * 获取 biz_role_permission.disabled
     * @return biz_role_permission.disabled
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /** 
     * 设置 biz_role_permission.disabled
     * @param disabled biz_role_permission.disabled
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /** 
     * 获取 biz_role_permission.create_by
     * @return biz_role_permission.create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 biz_role_permission.create_by
     * @param createBy biz_role_permission.create_by
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /** 
     * 获取 biz_role_permission.create_time
     * @return biz_role_permission.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 
     * 设置 biz_role_permission.create_time
     * @param createTime biz_role_permission.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     * 获取 biz_role_permission.update_by
     * @return biz_role_permission.update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /** 
     * 设置 biz_role_permission.update_by
     * @param updateBy biz_role_permission.update_by
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /** 
     * 获取 biz_role_permission.update_time
     * @return biz_role_permission.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /** 
     * 设置 biz_role_permission.update_time
     * @param updateTime biz_role_permission.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}