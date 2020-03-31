/* https://github.com/orange1438 */
package com.mytest.springsecuritydemo1.dao.auto;

import com.mytest.springsecuritydemo1.model.entity.BizRole;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

/**
 * 本文件由 https://github.com/orange1438/mybatis-generator-core-chinese-annotation1.3.5-chinese-annotation 自动生成
 * @author orange1438 code generator
 */
public interface BizRoleMapper {
    /** 
     * 根据ID查询
     * @param id 主键ID
     * @return 返回查询的结果
     */
    BizRole selectByPrimaryKey(Integer id);

    /** 
     * 添加对象对应字段
     * @param record 插入字段对象(必须含ID）
     * @return 返回添加成功的数量
     */
    int insertSelective(BizRole record);

    /** 
     * 添加List集合对象所有字段
     * @param records
     * @return 返回添加成功的数量
     */
    int insertBatch(java.util.List<BizRole> records);

    /** 
     * 根据ID修改对应字段
     * @param record 修改字段对象(必须含ID）
     * @return 返回更新成功的数量
     */
    int updateByPrimaryKeySelective(BizRole record);

    /** 
     * 根据主键，批量更新对应字段数据
     * @param records
     * @return 返回更新成功的数量
     */
    int updateBatchByPrimaryKeySelective(java.util.List<BizRole> records);

    /** 
     * 根据ID删除
     * @param id 主键ID
     * @param deletedBy
     * @return 返回删除成功的数量
     */
    int deleteByPrimaryKey(Integer id, String deletedBy);

    /** 
     *
     * @param primaryKeys
     * @param deletedBy
     * @return 返回删除成功的数量
     */
    int deleteBatchByPrimaryKey(java.util.List<Long> primaryKeys, String deletedBy);

    /** 
     *
     * @param condition
     * @return 返回查询的结果
     */
    java.util.List<BizRole> selectByCondition(@Param("condition") HashMap<String, Object> condition);
}