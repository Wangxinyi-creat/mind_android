package com.pj.mind.controller;

import java.util.ArrayList;
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
import com.pj.mind.domain.MindArticle;
import com.pj.mind.service.IMindArticleService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 文章Controller
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/mind/article")
public class MindArticleController extends BaseController
{
    @Autowired
    private IMindArticleService mindArticleService;

    /**
     * 查询文章列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindArticle mindArticle)
    {
        startPage();
        List<MindArticle> list = mindArticleService.selectMindArticleList(mindArticle);
        return getDataTable(list);
    }

    @GetMapping("/listVideoByIds")
    public AjaxResult listVideoByIds(Long[] ids)
    {
        if (ids == null || ids.length == 0) {
            return success(new ArrayList<>());
        }
        List<MindArticle> list = mindArticleService.selectMindArticleListVideoByIds(ids);
        return success(list.size() > 0 ? list : new ArrayList<>());
    }

    /**
     * 导出文章列表
     */
    @PreAuthorize("@ss.hasPermi('mind:article:export')")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindArticle mindArticle)
    {
        List<MindArticle> list = mindArticleService.selectMindArticleList(mindArticle);
        ExcelUtil<MindArticle> util = new ExcelUtil<MindArticle>(MindArticle.class);
        util.exportExcel(response, list, "文章数据");
    }

    /**
     * 获取文章详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mindArticleService.selectMindArticleById(id));
    }

    /**
     * 新增文章
     */
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindArticle mindArticle)
    {
        return toAjax(mindArticleService.insertMindArticle(mindArticle));
    }

    /**
     * 修改文章
     */
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindArticle mindArticle)
    {
        return toAjax(mindArticleService.updateMindArticle(mindArticle));
    }

    /**
     * 删除文章
     */
    @PreAuthorize("@ss.hasPermi('mind:article:remove')")
    @Log(title = "文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mindArticleService.deleteMindArticleByIds(ids));
    }

    @DeleteMapping("/delete/{id}")
    public AjaxResult removeById(@PathVariable Long id)
    {
        return toAjax(mindArticleService.deleteMindArticleById(id));
    }
}
