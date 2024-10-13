package com.pj.mind.service;

import java.util.List;
import com.pj.mind.domain.MindBanner;

/**
 * 幻灯片Service接口
 * 
 * @author ruoyi
 * @date 2024-04-20
 */
public interface IMindBannerService 
{
    /**
     * 查询幻灯片
     * 
     * @param id 幻灯片主键
     * @return 幻灯片
     */
    public MindBanner selectMindBannerById(Long id);

    /**
     * 查询幻灯片列表
     * 
     * @param mindBanner 幻灯片
     * @return 幻灯片集合
     */
    public List<MindBanner> selectMindBannerList(MindBanner mindBanner);

    /**
     * 新增幻灯片
     * 
     * @param mindBanner 幻灯片
     * @return 结果
     */
    public int insertMindBanner(MindBanner mindBanner);

    /**
     * 修改幻灯片
     * 
     * @param mindBanner 幻灯片
     * @return 结果
     */
    public int updateMindBanner(MindBanner mindBanner);

    /**
     * 批量删除幻灯片
     * 
     * @param ids 需要删除的幻灯片主键集合
     * @return 结果
     */
    public int deleteMindBannerByIds(Long[] ids);

    /**
     * 删除幻灯片信息
     * 
     * @param id 幻灯片主键
     * @return 结果
     */
    public int deleteMindBannerById(Long id);
}
