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
import com.pj.mind.domain.MindMessageInbox;
import com.pj.mind.service.IMindMessageInboxService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 心晴信箱Controller
 *
 * @author ruoyi
 * @date 2024-04-21
 */
@RestController
@RequestMapping("/mind/inbox")
public class MindMessageInboxController extends BaseController
{
    @Autowired
    private IMindMessageInboxService mindMessageInboxService;

    /**
     * 查询心晴信箱列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindMessageInbox mindMessageInbox)
    {
        startPage();
        List<MindMessageInbox> list = mindMessageInboxService.selectMindMessageInboxList(mindMessageInbox);
        return getDataTable(list);
    }

    @GetMapping("/listAll")
    public TableDataInfo listAll(MindMessageInbox mindMessageInbox)
    {
        List<MindMessageInbox> list = mindMessageInboxService.selectMindMessageInboxList(mindMessageInbox);
        return getDataTable(list);
    }

    /**
     * 导出心晴信箱列表
     */
    @PreAuthorize("@ss.hasPermi('mind:inbox:export')")
    @Log(title = "心晴信箱", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindMessageInbox mindMessageInbox)
    {
        List<MindMessageInbox> list = mindMessageInboxService.selectMindMessageInboxList(mindMessageInbox);
        ExcelUtil<MindMessageInbox> util = new ExcelUtil<MindMessageInbox>(MindMessageInbox.class);
        util.exportExcel(response, list, "心晴信箱数据");
    }

    /**
     * 获取心晴信箱详细信息
     */
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable("messageId") Long messageId)
    {
        return success(mindMessageInboxService.selectMindMessageInboxByMessageId(messageId));
    }

    /**
     * 新增心晴信箱
     */
    @Log(title = "心晴信箱", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindMessageInbox mindMessageInbox)
    {
        return toAjax(mindMessageInboxService.insertMindMessageInbox(mindMessageInbox));
    }

    /**
     * 修改心晴信箱
     */
    @PreAuthorize("@ss.hasPermi('mind:inbox:edit')")
    @Log(title = "心晴信箱", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindMessageInbox mindMessageInbox)
    {
        return toAjax(mindMessageInboxService.updateMindMessageInbox(mindMessageInbox));
    }

    /**
     * 删除心晴信箱
     */
    @PreAuthorize("@ss.hasPermi('mind:inbox:remove')")
    @Log(title = "心晴信箱", businessType = BusinessType.DELETE)
	@DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable Long[] messageIds)
    {
        return toAjax(mindMessageInboxService.deleteMindMessageInboxByMessageIds(messageIds));
    }
}
