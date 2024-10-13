package com.pj.mind.mapper;

import java.util.List;
import com.pj.mind.domain.MindActivity;

/**
 * 校园心理活动Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-20
 */
public interface MindActivityMapper 
{
    /**
     * 查询校园心理活动
     * 
     * @param activityId 校园心理活动主键
     * @return 校园心理活动
     */
    public MindActivity selectMindActivityByActivityId(Long activityId);

    /**
     * 查询校园心理活动列表
     * 
     * @param mindActivity 校园心理活动
     * @return 校园心理活动集合
     */
    public List<MindActivity> selectMindActivityList(MindActivity mindActivity);

    /**
     * 新增校园心理活动
     * 
     * @param mindActivity 校园心理活动
     * @return 结果
     */
    public int insertMindActivity(MindActivity mindActivity);

    /**
     * 修改校园心理活动
     * 
     * @param mindActivity 校园心理活动
     * @return 结果
     */
    public int updateMindActivity(MindActivity mindActivity);

    /**
     * 删除校园心理活动
     * 
     * @param activityId 校园心理活动主键
     * @return 结果
     */
    public int deleteMindActivityByActivityId(Long activityId);

    /**
     * 批量删除校园心理活动
     * 
     * @param activityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMindActivityByActivityIds(Long[] activityIds);
}
