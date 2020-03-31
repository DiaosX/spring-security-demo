/* https://github.com/orange1438 */
package com.mytest.springsecuritydemo1.model.entity;

import java.util.Date;

public class BizPermission {
    /** 
     */ 
    private Integer id;

    /** 
     */ 
    private String url;

    /** 
     */ 
    private String name;

    /** 
     */ 
    private String desc;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    private String method;

    /** 
     */ 
    private Date updateTime;

    /** 
     * 获取 biz_permission.id
     * @return biz_permission.id
     */
    public Integer getId() {
        return id;
    }

    /** 
     * 设置 biz_permission.id
     * @param id biz_permission.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 
     * 获取 biz_permission.url
     * @return biz_permission.url
     */
    public String getUrl() {
        return url;
    }

    /** 
     * 设置 biz_permission.url
     * @param url biz_permission.url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /** 
     * 获取 biz_permission.name
     * @return biz_permission.name
     */
    public String getName() {
        return name;
    }

    /** 
     * 设置 biz_permission.name
     * @param name biz_permission.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** 
     * 获取 biz_permission.desc
     * @return biz_permission.desc
     */
    public String getDesc() {
        return desc;
    }

    /** 
     * 设置 biz_permission.desc
     * @param desc biz_permission.desc
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /** 
     * 获取 biz_permission.disabled
     * @return biz_permission.disabled
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /** 
     * 设置 biz_permission.disabled
     * @param disabled biz_permission.disabled
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /** 
     * 获取 biz_permission.create_by
     * @return biz_permission.create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 biz_permission.create_by
     * @param createBy biz_permission.create_by
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /** 
     * 获取 biz_permission.create_time
     * @return biz_permission.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 
     * 设置 biz_permission.create_time
     * @param createTime biz_permission.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     * 获取 biz_permission.update_by
     * @return biz_permission.update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /** 
     * 设置 biz_permission.update_by
     * @param updateBy biz_permission.update_by
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /** 
     * 获取 biz_permission.update_time
     * @return biz_permission.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /** 
     * 设置 biz_permission.update_time
     * @param updateTime biz_permission.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}