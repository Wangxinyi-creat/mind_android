package com.pj.mind.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.pj.mind.mapper.MindAssessmentMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pj.common.annotation.Log;
import com.pj.common.core.controller.BaseController;
import com.pj.common.core.domain.AjaxResult;
import com.pj.common.enums.BusinessType;
import com.pj.mind.domain.MindAssessmentReport;
import com.pj.mind.service.IMindAssessmentReportService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 测试报告Controller
 *
 * @author ruoyi
 * @date 2024-04-22
 */
@RestController
@RequestMapping("/mind/report")
public class MindAssessmentReportController extends BaseController
{
    @Autowired
    private IMindAssessmentReportService mindAssessmentReportService;

    /**
     * 查询测试报告列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindAssessmentReport mindAssessmentReport)
    {
        startPage();
        List<MindAssessmentReport> list = mindAssessmentReportService.selectMindAssessmentReportList(mindAssessmentReport);
        return getDataTable(list);
    }

    /**
     * 导出测试报告列表
     */
    @PreAuthorize("@ss.hasPermi('mind:report:export')")
    @Log(title = "测试报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindAssessmentReport mindAssessmentReport)
    {
        List<MindAssessmentReport> list = mindAssessmentReportService.selectMindAssessmentReportList(mindAssessmentReport);
        ExcelUtil<MindAssessmentReport> util = new ExcelUtil<MindAssessmentReport>(MindAssessmentReport.class);
        util.exportExcel(response, list, "测试报告数据");
    }

    /**
     * 获取测试报告详细信息
     */
    @GetMapping(value = "/{reportId}")
    public AjaxResult getInfo(@PathVariable("reportId") Long reportId)
    {
        return success(mindAssessmentReportService.selectMindAssessmentReportByReportId(reportId));
    }

    /**
     * 新增测试报告
     */
    @Log(title = "测试报告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindAssessmentReport mindAssessmentReport)
    {
        return toAjax(mindAssessmentReportService.insertMindAssessmentReport(mindAssessmentReport));
    }

    /**
     * 修改测试报告
     */
    @PreAuthorize("@ss.hasPermi('mind:report:edit')")
    @Log(title = "测试报告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindAssessmentReport mindAssessmentReport)
    {
        return toAjax(mindAssessmentReportService.updateMindAssessmentReport(mindAssessmentReport));
    }

    /**
     * 删除测试报告
     */
    @PreAuthorize("@ss.hasPermi('mind:report:remove')")
    @Log(title = "测试报告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reportIds}")
    public AjaxResult remove(@PathVariable Long[] reportIds)
    {
        return toAjax(mindAssessmentReportService.deleteMindAssessmentReportByReportIds(reportIds));
    }
}
