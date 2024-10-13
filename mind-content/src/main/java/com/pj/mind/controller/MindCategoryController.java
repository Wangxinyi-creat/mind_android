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
import com.pj.mind.domain.MindCategory;
import com.pj.mind.service.IMindCategoryService;
import com.pj.common.utils.poi.ExcelUtil;

/**
 * 分类Controller
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/mind/category")
public class MindCategoryController extends BaseController
{
    @Autowired
    private IMindCategoryService mindCategoryService;

    /**
     * 查询分类列表
     */
    @GetMapping("/list")
    public AjaxResult list(MindCategory mindCategory)
    {
        List<MindCategory> list = mindCategoryService.selectMindCategoryList(mindCategory);
        return success(list);
    }

    /**
     * 导出分类列表
     */
    @PreAuthorize("@ss.hasPermi('mind:category:export')")
    @Log(title = "分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindCategory mindCategory)
    {
        List<MindCategory> list = mindCategoryService.selectMindCategoryList(mindCategory);
        ExcelUtil<MindCategory> util = new ExcelUtil<MindCategory>(MindCategory.class);
        util.exportExcel(response, list, "分类数据");
    }

    /**
     * 获取分类详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mindCategoryService.selectMindCategoryById(id));
    }

    /**
     * 新增分类
     */
    @PreAuthorize("@ss.hasPermi('mind:category:add')")
    @Log(title = "分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindCategory mindCategory)
    {
        return toAjax(mindCategoryService.insertMindCategory(mindCategory));
    }

    /**
     * 修改分类
     */
    @PreAuthorize("@ss.hasPermi('mind:category:edit')")
    @Log(title = "分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindCategory mindCategory)
    {
        return toAjax(mindCategoryService.updateMindCategory(mindCategory));
    }

    /**
     * 删除分类
     */
    @PreAuthorize("@ss.hasPermi('mind:category:remove')")
    @Log(title = "分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mindCategoryService.deleteMindCategoryByIds(ids));
    }
}
