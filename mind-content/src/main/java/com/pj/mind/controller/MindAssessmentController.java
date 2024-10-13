package com.pj.mind.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.pj.mind.domain.MindAssessment;
import com.pj.mind.service.IMindAssessmentService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 心理测评Controller
 *
 * @author ruoyi
 * @date 2024-04-22
 */
@RestController
@RequestMapping("/mind/assessment")
public class MindAssessmentController extends BaseController
{
    @Autowired
    private IMindAssessmentService mindAssessmentService;

    /**
     * 查询心理测评列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindAssessment mindAssessment)
    {
        startPage();
        List<MindAssessment> list = mindAssessmentService.selectMindAssessmentList(mindAssessment);
        return getDataTable(list);
    }

    /**
     * 导出心理测评列表
     */
    @PreAuthorize("@ss.hasPermi('mind:assessment:export')")
    @Log(title = "心理测评", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindAssessment mindAssessment)
    {
        List<MindAssessment> list = mindAssessmentService.selectMindAssessmentList(mindAssessment);
        ExcelUtil<MindAssessment> util = new ExcelUtil<MindAssessment>(MindAssessment.class);
        util.exportExcel(response, list, "心理测评数据");
    }

    /**
     * 获取心理测评详细信息
     */
    @GetMapping(value = "/{assessmentId}")
    public AjaxResult getInfo(@PathVariable("assessmentId") Long assessmentId)
    {
        return success(mindAssessmentService.selectMindAssessmentByAssessmentId(assessmentId));
    }

    /**
     * 新增心理测评
     */
    @PreAuthorize("@ss.hasPermi('mind:assessment:add')")
    @Log(title = "心理测评", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindAssessment mindAssessment)
    {
        return toAjax(mindAssessmentService.insertMindAssessment(mindAssessment));
    }

    /**
     * 修改心理测评
     */
    @PreAuthorize("@ss.hasPermi('mind:assessment:edit')")
    @Log(title = "心理测评", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindAssessment mindAssessment)
    {
        return toAjax(mindAssessmentService.updateMindAssessment(mindAssessment));
    }

    /**
     * 删除心理测评
     */
    @PreAuthorize("@ss.hasPermi('mind:assessment:remove')")
    @Log(title = "心理测评", businessType = BusinessType.DELETE)
	@DeleteMapping("/{assessmentIds}")
    public AjaxResult remove(@PathVariable Long[] assessmentIds)
    {
        return toAjax(mindAssessmentService.deleteMindAssessmentByAssessmentIds(assessmentIds));
    }
}
