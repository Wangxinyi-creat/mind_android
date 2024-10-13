package com.pj.mind.service;

import java.util.List;
import com.pj.mind.domain.MindAssessment;

/**
 * 心理测评Service接口
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
public interface IMindAssessmentService 
{
    /**
     * 查询心理测评
     * 
     * @param assessmentId 心理测评主键
     * @return 心理测评
     */
    public MindAssessment selectMindAssessmentByAssessmentId(Long assessmentId);

    /**
     * 查询心理测评列表
     * 
     * @param mindAssessment 心理测评
     * @return 心理测评集合
     */
    public List<MindAssessment> selectMindAssessmentList(MindAssessment mindAssessment);

    /**
     * 新增心理测评
     * 
     * @param mindAssessment 心理测评
     * @return 结果
     */
    public int insertMindAssessment(MindAssessment mindAssessment);

    /**
     * 修改心理测评
     * 
     * @param mindAssessment 心理测评
     * @return 结果
     */
    public int updateMindAssessment(MindAssessment mindAssessment);

    /**
     * 批量删除心理测评
     * 
     * @param assessmentIds 需要删除的心理测评主键集合
     * @return 结果
     */
    public int deleteMindAssessmentByAssessmentIds(Long[] assessmentIds);

    /**
     * 删除心理测评信息
     * 
     * @param assessmentId 心理测评主键
     * @return 结果
     */
    public int deleteMindAssessmentByAssessmentId(Long assessmentId);
}
