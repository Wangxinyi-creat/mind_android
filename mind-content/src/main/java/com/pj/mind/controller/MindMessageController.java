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
import com.pj.mind.domain.MindMessage;
import com.pj.mind.service.IMindMessageService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 消息Controller
 *
 * @author ruoyi
 * @date 2024-04-21
 */
@RestController
@RequestMapping("/mind/message")
public class MindMessageController extends BaseController {
    @Autowired
    private IMindMessageService mindMessageService;

    /**
     * 查询消息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindMessage mindMessage) {
        startPage();
        List<MindMessage> list = mindMessageService.selectMindMessageList(mindMessage);
        return getDataTable(list);
    }

    /**
     * 查询消息列表
     */
    @GetMapping("/listAll")
    public AjaxResult listAll(MindMessage mindMessage) {
        List<MindMessage> list = mindMessageService.selectMindMessageListAll(mindMessage);
        return success(list == null ? new ArrayList<>() : list);
    }

    /**
     * 导出消息列表
     */
    @PreAuthorize("@ss.hasPermi('mind:message:export')")
    @Log(title = "消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindMessage mindMessage) {
        List<MindMessage> list = mindMessageService.selectMindMessageList(mindMessage);
        ExcelUtil<MindMessage> util = new ExcelUtil<MindMessage>(MindMessage.class);
        util.exportExcel(response, list, "消息数据");
    }

    /**
     * 获取消息详细信息
     */
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable("messageId") Long messageId) {
        return success(mindMessageService.selectMindMessageByMessageId(messageId));
    }

    /**
     * 获取消息详细信息
     */
    @GetMapping(value = "/next/{recordId}/{messageId}")
    public AjaxResult getInfoNext(@PathVariable("messageId") Long messageId, @PathVariable("recordId") Long recordId) {
        List<MindMessage> list = mindMessageService.selectMindMessageByMessageIdNext(messageId, recordId);

        return success(list != null ? list : new ArrayList<>());
    }

    /**
     * 新增消息
     */
    @Log(title = "消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindMessage mindMessage) {
        return toAjax(mindMessageService.insertMindMessage(mindMessage));
    }

    /**
     * 修改消息
     */
    @PreAuthorize("@ss.hasPermi('mind:message:edit')")
    @Log(title = "消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindMessage mindMessage) {
        return toAjax(mindMessageService.updateMindMessage(mindMessage));
    }

    /**
     * 删除消息
     */
    @PreAuthorize("@ss.hasPermi('mind:message:remove')")
    @Log(title = "消息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable Long[] messageIds) {
        return toAjax(mindMessageService.deleteMindMessageByMessageIds(messageIds));
    }
}
