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
import com.pj.mind.domain.MindActivity;
import com.pj.mind.service.IMindActivityService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 校园心理活动Controller
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/mind/activity")
public class MindActivityController extends BaseController
{
    @Autowired
    private IMindActivityService mindActivityService;

    /**
     * 查询校园心理活动列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindActivity mindActivity)
    {
        startPage();
        List<MindActivity> list = mindActivityService.selectMindActivityList(mindActivity);
        return getDataTable(list);
    }

    /**
     * 导出校园心理活动列表
     */
    @PreAuthorize("@ss.hasPermi('mind:activity:export')")
    @Log(title = "校园心理活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindActivity mindActivity)
    {
        List<MindActivity> list = mindActivityService.selectMindActivityList(mindActivity);
        ExcelUtil<MindActivity> util = new ExcelUtil<MindActivity>(MindActivity.class);
        util.exportExcel(response, list, "校园心理活动数据");
    }

    /**
     * 获取校园心理活动详细信息
     */
    @GetMapping(value = "/{activityId}")
    public AjaxResult getInfo(@PathVariable("activityId") Long activityId)
    {
        return success(mindActivityService.selectMindActivityByActivityId(activityId));
    }

    /**
     * 新增校园心理活动
     */
    @PreAuthorize("@ss.hasPermi('mind:activity:add')")
    @Log(title = "校园心理活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindActivity mindActivity)
    {
        return toAjax(mindActivityService.insertMindActivity(mindActivity));
    }

    /**
     * 修改校园心理活动
     */
    @PreAuthorize("@ss.hasPermi('mind:activity:edit')")
    @Log(title = "校园心理活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindActivity mindActivity)
    {
        return toAjax(mindActivityService.updateMindActivity(mindActivity));
    }

    /**
     * 删除校园心理活动
     */
    @Log(title = "校园心理活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable Long[] activityIds)
    {
        return toAjax(mindActivityService.deleteMindActivityByActivityIds(activityIds));
    }
}
