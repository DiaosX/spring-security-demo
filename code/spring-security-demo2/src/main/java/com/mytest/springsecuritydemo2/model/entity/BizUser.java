/* https://github.com/orange1438 */
package com.mytest.springsecuritydemo2.model.entity;

import java.util.Date;

public class BizUser {
    /** 
     */ 
    private Integer id;

    /** 
     */ 
    private String name;

    /** 
     */ 
    private Integer age;

    /** 
     */ 
    private String desc;

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
     */ 
    private Boolean disabled;

    /** 
     */ 
    private String phone;

    /** 
     */ 
    private String email;

    /** 
     * 获取 biz_user.id
     * @return biz_user.id
     */
    public Integer getId() {
        return id;
    }

    /** 
     * 设置 biz_user.id
     * @param id biz_user.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 
     * 获取 biz_user.name
     * @return biz_user.name
     */
    public String getName() {
        return name;
    }

    /** 
     * 设置 biz_user.name
     * @param name biz_user.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** 
     * 获取 biz_user.age
     * @return biz_user.age
     */
    public Integer getAge() {
        return age;
    }

    /** 
     * 设置 biz_user.age
     * @param age biz_user.age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /** 
     * 获取 biz_user.desc
     * @return biz_user.desc
     */
    public String getDesc() {
        return desc;
    }

    /** 
     * 设置 biz_user.desc
     * @param desc biz_user.desc
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /** 
     * 获取 biz_user.create_by
     * @return biz_user.create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 biz_user.create_by
     * @param createBy biz_user.create_by
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /** 
     * 获取 biz_user.create_time
     * @return biz_user.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 
     * 设置 biz_user.create_time
     * @param createTime biz_user.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     * 获取 biz_user.update_by
     * @return biz_user.update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /** 
     * 设置 biz_user.update_by
     * @param updateBy biz_user.update_by
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /** 
     * 获取 biz_user.update_time
     * @return biz_user.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /** 
     * 设置 biz_user.update_time
     * @param updateTime biz_user.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /** 
     * 获取 biz_user.disabled
     * @return biz_user.disabled
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /** 
     * 设置 biz_user.disabled
     * @param disabled biz_user.disabled
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /** 
     * 获取 biz_user.phone
     * @return biz_user.phone
     */
    public String getPhone() {
        return phone;
    }

    /** 
     * 设置 biz_user.phone
     * @param phone biz_user.phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /** 
     * 获取 biz_user.email
     * @return biz_user.email
     */
    public String getEmail() {
        return email;
    }

    /** 
     * 设置 biz_user.email
     * @param email biz_user.email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}