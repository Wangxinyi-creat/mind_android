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
import com.pj.mind.domain.MindArticleComment;
import com.pj.mind.service.IMindArticleCommentService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 评论Controller
 *
 * @author ruoyi
 * @date 2024-04-21
 */
@RestController
@RequestMapping("/mind/comment")
public class MindArticleCommentController extends BaseController
{
    @Autowired
    private IMindArticleCommentService mindArticleCommentService;

    /**
     * 查询评论列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindArticleComment mindArticleComment)
    {
        startPage();
        List<MindArticleComment> list = mindArticleCommentService.selectMindArticleCommentList(mindArticleComment);
        return getDataTable(list);
    }

    @GetMapping("/listAll")
    public AjaxResult listAll(MindArticleComment mindArticleComment) {
        List<MindArticleComment> list = mindArticleCommentService.selectMindArticleCommentList(mindArticleComment);
        return success(list == null ? new ArrayList<>() : list);
    }

    /**
     * 导出评论列表
     */
    @PreAuthorize("@ss.hasPermi('mind:comment:export')")
    @Log(title = "评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindArticleComment mindArticleComment)
    {
        List<MindArticleComment> list = mindArticleCommentService.selectMindArticleCommentList(mindArticleComment);
        ExcelUtil<MindArticleComment> util = new ExcelUtil<MindArticleComment>(MindArticleComment.class);
        util.exportExcel(response, list, "评论数据");
    }

    /**
     * 获取评论详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mindArticleCommentService.selectMindArticleCommentById(id));
    }

    /**
     * 新增评论
     */
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindArticleComment mindArticleComment)
    {
        return toAjax(mindArticleCommentService.insertMindArticleComment(mindArticleComment));
    }

    /**
     * 修改评论
     */
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindArticleComment mindArticleComment)
    {
        return toAjax(mindArticleCommentService.updateMindArticleComment(mindArticleComment));
    }

    /**
     * 删除评论
     */
    @PreAuthorize("@ss.hasPermi('mind:comment:remove')")
    @Log(title = "评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mindArticleCommentService.deleteMindArticleCommentByIds(ids));
    }
}
