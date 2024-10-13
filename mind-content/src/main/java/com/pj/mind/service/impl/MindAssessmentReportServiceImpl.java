package com.pj.mind.service.impl;

import java.util.List;
import java.util.function.Consumer;

import com.pj.common.utils.DateUtils;
import com.pj.mind.domain.MindAssessment;
import com.pj.mind.mapper.MindAssessmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindAssessmentReportMapper;
import com.pj.mind.domain.MindAssessmentReport;
import com.pj.mind.service.IMindAssessmentReportService;

/**
 * 测试报告Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-22
 */
@Service
public class MindAssessmentReportServiceImpl implements IMindAssessmentReportService {
    @Autowired
    private MindAssessmentReportMapper mindAssessmentReportMapper;
    @Autowired
    private MindAssessmentMapper mindAssessmentMapper;

    /**
     * 查询测试报告
     *
     * @param reportId 测试报告主键
     * @return 测试报告
     */
    @Override
    public MindAssessmentReport selectMindAssessmentReportByReportId(Long reportId) {
        MindAssessmentReport mindAssessmentReport = mindAssessmentReportMapper.selectMindAssessmentReportByReportId(reportId);
        MindAssessment mindAssessment = mindAssessmentMapper.selectMindAssessmentByAssessmentId(mindAssessmentReport.getAssessmentId());
        if (mindAssessment != null) {
            mindAssessmentReport.setPicUrl(mindAssessment.getPicUrl());
        }
        return mindAssessmentReport;
    }

    /**
     * 查询测试报告列表
     *
     * @param mindAssessmentReport 测试报告
     * @return 测试报告
     */
    @Override
    public List<MindAssessmentReport> selectMindAssessmentReportList(MindAssessmentReport mindAssessmentReport) {
        List<MindAssessmentReport> list = mindAssessmentReportMapper.selectMindAssessmentReportList(mindAssessmentReport);
        list.forEach(mindAssessmentReport1 -> {
            MindAssessment mindAssessment = mindAssessmentMapper.selectMindAssessmentByAssessmentId(mindAssessmentReport1.getAssessmentId());
            if (mindAssessment != null) {
                mindAssessmentReport1.setPicUrl(mindAssessment.getPicUrl());
            }
        });

        return list;
    }

    /**
     * 新增测试报告
     *
     * @param mindAssessmentReport 测试报告
     * @return 结果
     */
    @Override
    public int insertMindAssessmentReport(MindAssessmentReport mindAssessmentReport) {
        mindAssessmentReport.setCreateTime(DateUtils.getNowDate());
        int res = mindAssessmentReportMapper.insertMindAssessmentReport(mindAssessmentReport);
        if (res > 0) {
            MindAssessment mindAssessment = mindAssessmentMapper.selectMindAssessmentByAssessmentId(mindAssessmentReport.getAssessmentId());
            if (mindAssessment != null) {
                mindAssessment.setNum(mindAssessment.getNum() + 1);
                mindAssessmentMapper.updateMindAssessment(mindAssessment);
            }
        }
        return res;
    }

    /**
     * 修改测试报告
     *
     * @param mindAssessmentReport 测试报告
     * @return 结果
     */
    @Override
    public int updateMindAssessmentReport(MindAssessmentReport mindAssessmentReport) {
        mindAssessmentReport.setUpdateTime(DateUtils.getNowDate());
        return mindAssessmentReportMapper.updateMindAssessmentReport(mindAssessmentReport);
    }

    /**
     * 批量删除测试报告
     *
     * @param reportIds 需要删除的测试报告主键
     * @return 结果
     */
    @Override
    public int deleteMindAssessmentReportByReportIds(Long[] reportIds) {
        return mindAssessmentReportMapper.deleteMindAssessmentReportByReportIds(reportIds);
    }

    /**
     * 删除测试报告信息
     *
     * @param reportId 测试报告主键
     * @return 结果
     */
    @Override
    public int deleteMindAssessmentReportByReportId(Long reportId) {
        return mindAssessmentReportMapper.deleteMindAssessmentReportByReportId(reportId);
    }
}
