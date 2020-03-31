/* https://github.com/orange1438 */
package com.mytest.springsecuritydemo3.model.entity;

import java.util.Date;

public class BizRole {
    /** 
     */ 
    private Integer id;

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

    /** 
     */ 
    private Date updateTime;

    /** 
     * 获取 biz_role.id
     * @return biz_role.id
     */
    public Integer getId() {
        return id;
    }

    /** 
     * 设置 biz_role.id
     * @param id biz_role.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 
     * 获取 biz_role.name
     * @return biz_role.name
     */
    public String getName() {
        return name;
    }

    /** 
     * 设置 biz_role.name
     * @param name biz_role.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** 
     * 获取 biz_role.desc
     * @return biz_role.desc
     */
    public String getDesc() {
        return desc;
    }

    /** 
     * 设置 biz_role.desc
     * @param desc biz_role.desc
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /** 
     * 获取 biz_role.disabled
     * @return biz_role.disabled
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /** 
     * 设置 biz_role.disabled
     * @param disabled biz_role.disabled
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /** 
     * 获取 biz_role.create_by
     * @return biz_role.create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 biz_role.create_by
     * @param createBy biz_role.create_by
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /** 
     * 获取 biz_role.create_time
     * @return biz_role.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 
     * 设置 biz_role.create_time
     * @param createTime biz_role.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     * 获取 biz_role.update_by
     * @return biz_role.update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /** 
     * 设置 biz_role.update_by
     * @param updateBy biz_role.update_by
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /** 
     * 获取 biz_role.update_time
     * @return biz_role.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /** 
     * 设置 biz_role.update_time
     * @param updateTime biz_role.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}