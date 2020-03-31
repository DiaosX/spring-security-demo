/* https://github.com/orange1438 */
package com.mytest.springsecuritydemo1.model.entity;

import java.util.Date;

public class BizUserRole {
    /** 
     */ 
    private Integer id;

    /** 
     */ 
    private Integer userId;

    /** 
     */ 
    private Integer roleId;

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
     * 获取 biz_user_role.id
     * @return biz_user_role.id
     */
    public Integer getId() {
        return id;
    }

    /** 
     * 设置 biz_user_role.id
     * @param id biz_user_role.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 
     * 获取 biz_user_role.user_id
     * @return biz_user_role.user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /** 
     * 设置 biz_user_role.user_id
     * @param userId biz_user_role.user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 
     * 获取 biz_user_role.role_id
     * @return biz_user_role.role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /** 
     * 设置 biz_user_role.role_id
     * @param roleId biz_user_role.role_id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /** 
     * 获取 biz_user_role.disabled
     * @return biz_user_role.disabled
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /** 
     * 设置 biz_user_role.disabled
     * @param disabled biz_user_role.disabled
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /** 
     * 获取 biz_user_role.create_by
     * @return biz_user_role.create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 biz_user_role.create_by
     * @param createBy biz_user_role.create_by
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /** 
     * 获取 biz_user_role.create_time
     * @return biz_user_role.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 
     * 设置 biz_user_role.create_time
     * @param createTime biz_user_role.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     * 获取 biz_user_role.update_by
     * @return biz_user_role.update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /** 
     * 设置 biz_user_role.update_by
     * @param updateBy biz_user_role.update_by
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /** 
     * 获取 biz_user_role.update_time
     * @return biz_user_role.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /** 
     * 设置 biz_user_role.update_time
     * @param updateTime biz_user_role.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}