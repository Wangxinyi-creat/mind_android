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
import com.pj.mind.domain.MindBanner;
import com.pj.mind.service.IMindBannerService;
import com.pj.common.utils.poi.ExcelUtil;
import com.pj.common.core.page.TableDataInfo;

/**
 * 幻灯片Controller
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/mind/banner")
public class MindBannerController extends BaseController
{
    @Autowired
    private IMindBannerService mindBannerService;

    /**
     * 查询幻灯片列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MindBanner mindBanner)
    {
        startPage();
        List<MindBanner> list = mindBannerService.selectMindBannerList(mindBanner);
        return getDataTable(list);
    }

    /**
     * 导出幻灯片列表
     */
    @PreAuthorize("@ss.hasPermi('mind:banner:export')")
    @Log(title = "幻灯片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MindBanner mindBanner)
    {
        List<MindBanner> list = mindBannerService.selectMindBannerList(mindBanner);
        ExcelUtil<MindBanner> util = new ExcelUtil<MindBanner>(MindBanner.class);
        util.exportExcel(response, list, "幻灯片数据");
    }

    /**
     * 获取幻灯片详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mindBannerService.selectMindBannerById(id));
    }

    /**
     * 新增幻灯片
     */
    @PreAuthorize("@ss.hasPermi('mind:banner:add')")
    @Log(title = "幻灯片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MindBanner mindBanner)
    {
        return toAjax(mindBannerService.insertMindBanner(mindBanner));
    }

    /**
     * 修改幻灯片
     */
    @PreAuthorize("@ss.hasPermi('mind:banner:edit')")
    @Log(title = "幻灯片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MindBanner mindBanner)
    {
        return toAjax(mindBannerService.updateMindBanner(mindBanner));
    }

    /**
     * 删除幻灯片
     */
    @PreAuthorize("@ss.hasPermi('mind:banner:remove')")
    @Log(title = "幻灯片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mindBannerService.deleteMindBannerByIds(ids));
    }
}
