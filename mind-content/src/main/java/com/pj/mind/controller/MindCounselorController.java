package com.pj.mind.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.pj.common.utils.SecurityUtils;
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
import com.pj.mind.domain.MindCounselor;
import com.pj.mind.service.IMindCounselorService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 咨询师Controller
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/mind/counselor")
public class MindCounselorController extends BaseController
{
    @Autowired
    private IMindCounselorService mindCounselorService;

    /**
     * 查询咨询师列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindCounselor mindCounselor)
    {
        startPage();
        List<MindCounselor> list = mindCounselorService.selectMindCounselorList(mindCounselor);
        return getDataTable(list);
    }

    /**
     * 导出咨询师列表
     */
    @PreAuthorize("@ss.hasPermi('mind:counselor:export')")
    @Log(title = "咨询师", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindCounselor mindCounselor)
    {
        List<MindCounselor> list = mindCounselorService.selectMindCounselorList(mindCounselor);
        ExcelUtil<MindCounselor> util = new ExcelUtil<MindCounselor>(MindCounselor.class);
        util.exportExcel(response, list, "咨询师数据");
    }

    /**
     * 获取咨询师详细信息
     */
    @GetMapping(value = "/getCounselorByUserId")
    public AjaxResult getCounselorByUserId()
    {

        return success(mindCounselorService.selectMindCounselorByUserId(SecurityUtils.getUserId()));
    }

    /**
     * 获取咨询师详细信息
     */
    @GetMapping(value = "/{counselorId}")
    public AjaxResult getInfo(@PathVariable("counselorId") Long counselorId)
    {
        return success(mindCounselorService.selectMindCounselorByCounselorId(counselorId));
    }

    /**
     * 新增咨询师
     */
    @PreAuthorize("@ss.hasPermi('mind:counselor:add')")
    @Log(title = "咨询师", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindCounselor mindCounselor)
    {
        return toAjax(mindCounselorService.insertMindCounselor(mindCounselor));
    }

    /**
     * 修改咨询师
     */
    @PreAuthorize("@ss.hasPermi('mind:counselor:edit')")
    @Log(title = "咨询师", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindCounselor mindCounselor)
    {
        return toAjax(mindCounselorService.updateMindCounselor(mindCounselor));
    }

    /**
     * 删除咨询师
     */
    @PreAuthorize("@ss.hasPermi('mind:counselor:remove')")
    @Log(title = "咨询师", businessType = BusinessType.DELETE)
	@DeleteMapping("/{counselorIds}")
    public AjaxResult remove(@PathVariable Long[] counselorIds)
    {
        return toAjax(mindCounselorService.deleteMindCounselorByCounselorIds(counselorIds));
    }
}
