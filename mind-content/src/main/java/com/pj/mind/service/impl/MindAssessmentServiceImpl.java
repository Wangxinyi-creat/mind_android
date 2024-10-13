package com.pj.mind.service.impl;

import java.util.List;
import com.pj.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindAssessmentMapper;
import com.pj.mind.domain.MindAssessment;
import com.pj.mind.service.IMindAssessmentService;

/**
 * 心理测评Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-22
 */
@Service
public class MindAssessmentServiceImpl implements IMindAssessmentService
{
    @Autowired
    private MindAssessmentMapper mindAssessmentMapper;


    /**
     * 查询心理测评
     *
     * @param assessmentId 心理测评主键
     * @return 心理测评
     */
    @Override
    public MindAssessment selectMindAssessmentByAssessmentId(Long assessmentId)
    {
        return mindAssessmentMapper.selectMindAssessmentByAssessmentId(assessmentId);
    }

    /**
     * 查询心理测评列表
     *
     * @param mindAssessment 心理测评
     * @return 心理测评
     */
    @Override
    public List<MindAssessment> selectMindAssessmentList(MindAssessment mindAssessment)
    {
        return mindAssessmentMapper.selectMindAssessmentList(mindAssessment);
    }

    /**
     * 新增心理测评
     *
     * @param mindAssessment 心理测评
     * @return 结果
     */
    @Override
    public int insertMindAssessment(MindAssessment mindAssessment)
    {
        mindAssessment.setCreateTime(DateUtils.getNowDate());
        return mindAssessmentMapper.insertMindAssessment(mindAssessment);
    }

    /**
     * 修改心理测评
     *
     * @param mindAssessment 心理测评
     * @return 结果
     */
    @Override
    public int updateMindAssessment(MindAssessment mindAssessment)
    {
        mindAssessment.setUpdateTime(DateUtils.getNowDate());
        return mindAssessmentMapper.updateMindAssessment(mindAssessment);
    }

    /**
     * 批量删除心理测评
     *
     * @param assessmentIds 需要删除的心理测评主键
     * @return 结果
     */
    @Override
    public int deleteMindAssessmentByAssessmentIds(Long[] assessmentIds)
    {
        return mindAssessmentMapper.deleteMindAssessmentByAssessmentIds(assessmentIds);
    }

    /**
     * 删除心理测评信息
     *
     * @param assessmentId 心理测评主键
     * @return 结果
     */
    @Override
    public int deleteMindAssessmentByAssessmentId(Long assessmentId)
    {
        return mindAssessmentMapper.deleteMindAssessmentByAssessmentId(assessmentId);
    }
}
