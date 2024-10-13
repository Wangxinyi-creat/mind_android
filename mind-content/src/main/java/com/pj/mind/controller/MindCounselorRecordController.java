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
import com.pj.mind.domain.MindCounselorRecord;
import com.pj.mind.service.IMindCounselorRecordService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 咨询师预约记录Controller
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/mind/counselorRecord")
public class MindCounselorRecordController extends BaseController
{
    @Autowired
    private IMindCounselorRecordService mindCounselorRecordService;

    /**
     * 查询咨询师预约记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindCounselorRecord mindCounselorRecord)
    {
        startPage();
        List<MindCounselorRecord> list = mindCounselorRecordService.selectMindCounselorRecordList(mindCounselorRecord);
        return getDataTable(list);
    }

    /**
     * 导出咨询师预约记录列表
     */
    @PreAuthorize("@ss.hasPermi('mind:counselorRecord:export')")
    @Log(title = "咨询师预约记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindCounselorRecord mindCounselorRecord)
    {
        List<MindCounselorRecord> list = mindCounselorRecordService.selectMindCounselorRecordList(mindCounselorRecord);
        ExcelUtil<MindCounselorRecord> util = new ExcelUtil<MindCounselorRecord>(MindCounselorRecord.class);
        util.exportExcel(response, list, "咨询师预约记录数据");
    }

    /**
     * 获取咨询师预约记录详细信息
     */
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(mindCounselorRecordService.selectMindCounselorRecordByRecordId(recordId));
    }

    /**
     * 新增咨询师预约记录
     */
    @Log(title = "咨询师预约记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindCounselorRecord mindCounselorRecord)
    {
        return toAjax(mindCounselorRecordService.insertMindCounselorRecord(mindCounselorRecord));
    }

    /**
     * 修改咨询师预约记录
     */
    @Log(title = "咨询师预约记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindCounselorRecord mindCounselorRecord)
    {
        return toAjax(mindCounselorRecordService.updateMindCounselorRecord(mindCounselorRecord));
    }

    /**
     * 删除咨询师预约记录
     */
    @PreAuthorize("@ss.hasPermi('mind:counselorRecord:remove')")
    @Log(title = "咨询师预约记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(mindCounselorRecordService.deleteMindCounselorRecordByRecordIds(recordIds));
    }
}
