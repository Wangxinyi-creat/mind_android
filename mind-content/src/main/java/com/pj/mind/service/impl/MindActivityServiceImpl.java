package com.pj.mind.service.impl;

import java.util.List;
import com.pj.common.utils.DateUtils;
import com.pj.mind.domain.MindActivityRecord;
import com.pj.mind.mapper.MindActivityRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindActivityMapper;
import com.pj.mind.domain.MindActivity;
import com.pj.mind.service.IMindActivityService;

/**
 * 校园心理活动Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@Service
public class MindActivityServiceImpl implements IMindActivityService
{
    @Autowired
    private MindActivityMapper mindActivityMapper;
    @Autowired
    private MindActivityRecordMapper mindActivityRecordMapper;

    /**
     * 查询校园心理活动
     *
     * @param activityId 校园心理活动主键
     * @return 校园心理活动
     */
    @Override
    public MindActivity selectMindActivityByActivityId(Long activityId)
    {
        return mindActivityMapper.selectMindActivityByActivityId(activityId);
    }

    /**
     * 查询校园心理活动列表
     *
     * @param mindActivity 校园心理活动
     * @return 校园心理活动
     */
    @Override
    public List<MindActivity> selectMindActivityList(MindActivity mindActivity)
    {
        return mindActivityMapper.selectMindActivityList(mindActivity);
    }

    /**
     * 新增校园心理活动
     *
     * @param mindActivity 校园心理活动
     * @return 结果
     */
    @Override
    public int insertMindActivity(MindActivity mindActivity)
    {
        mindActivity.setCreateTime(DateUtils.getNowDate());
        mindActivity.setUpdateTime(DateUtils.getNowDate());
        return mindActivityMapper.insertMindActivity(mindActivity);
    }

    /**
     * 修改校园心理活动
     *
     * @param mindActivity 校园心理活动
     * @return 结果
     */
    @Override
    public int updateMindActivity(MindActivity mindActivity)
    {
        mindActivity.setUpdateTime(DateUtils.getNowDate());
        return mindActivityMapper.updateMindActivity(mindActivity);
    }

    /**
     * 批量删除校园心理活动
     *
     * @param activityIds 需要删除的校园心理活动主键
     * @return 结果
     */
    @Override
    public int deleteMindActivityByActivityIds(Long[] activityIds)
    {
        mindActivityRecordMapper.deleteMindActivityRecordByActivityIds(activityIds);
        return mindActivityMapper.deleteMindActivityByActivityIds(activityIds);
    }

    /**
     * 删除校园心理活动信息
     *
     * @param activityId 校园心理活动主键
     * @return 结果
     */
    @Override
    public int deleteMindActivityByActivityId(Long activityId)
    {
        return mindActivityMapper.deleteMindActivityByActivityId(activityId);
    }
}
