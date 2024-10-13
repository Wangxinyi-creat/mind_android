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
import com.pj.mind.domain.MindActivityRecord;
import com.pj.mind.service.IMindActivityRecordService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 活动预约记录Controller
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/mind/activityRecord")
public class MindActivityRecordController extends BaseController
{
    @Autowired
    private IMindActivityRecordService mindActivityRecordService;

    /**
     * 查询活动预约记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindActivityRecord mindActivityRecord)
    {
        startPage();
        List<MindActivityRecord> list = mindActivityRecordService.selectMindActivityRecordList(mindActivityRecord);
        return getDataTable(list);
    }

    /**
     * 获取活动预约记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mind:activityRecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(mindActivityRecordService.selectMindActivityRecordByRecordId(recordId));
    }

    /**
     * 新增活动预约记录
     */
    @Log(title = "活动预约记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindActivityRecord mindActivityRecord)
    {
        return toAjax(mindActivityRecordService.insertMindActivityRecord(mindActivityRecord));
    }


}
