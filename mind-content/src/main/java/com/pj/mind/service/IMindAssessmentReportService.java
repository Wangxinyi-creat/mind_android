package com.pj.mind.service;

import java.util.List;
import com.pj.mind.domain.MindAssessmentReport;

/**
 * 测试报告Service接口
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
public interface IMindAssessmentReportService 
{
    /**
     * 查询测试报告
     * 
     * @param reportId 测试报告主键
     * @return 测试报告
     */
    public MindAssessmentReport selectMindAssessmentReportByReportId(Long reportId);

    /**
     * 查询测试报告列表
     * 
     * @param mindAssessmentReport 测试报告
     * @return 测试报告集合
     */
    public List<MindAssessmentReport> selectMindAssessmentReportList(MindAssessmentReport mindAssessmentReport);

    /**
     * 新增测试报告
     * 
     * @param mindAssessmentReport 测试报告
     * @return 结果
     */
    public int insertMindAssessmentReport(MindAssessmentReport mindAssessmentReport);

    /**
     * 修改测试报告
     * 
     * @param mindAssessmentReport 测试报告
     * @return 结果
     */
    public int updateMindAssessmentReport(MindAssessmentReport mindAssessmentReport);

    /**
     * 批量删除测试报告
     * 
     * @param reportIds 需要删除的测试报告主键集合
     * @return 结果
     */
    public int deleteMindAssessmentReportByReportIds(Long[] reportIds);

    /**
     * 删除测试报告信息
     * 
     * @param reportId 测试报告主键
     * @return 结果
     */
    public int deleteMindAssessmentReportByReportId(Long reportId);
}
