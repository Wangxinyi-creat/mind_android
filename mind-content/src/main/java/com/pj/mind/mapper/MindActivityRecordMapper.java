package com.pj.mind.mapper;

import java.util.List;
import com.pj.mind.domain.MindActivityRecord;

/**
 * 活动预约记录Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-20
 */
public interface MindActivityRecordMapper
{
    /**
     * 查询活动预约记录
     *
     * @param recordId 活动预约记录主键
     * @return 活动预约记录
     */
    public MindActivityRecord selectMindActivityRecordByRecordId(Long recordId);

    /**
     * 查询活动预约记录列表
     *
     * @param mindActivityRecord 活动预约记录
     * @return 活动预约记录集合
     */
    public List<MindActivityRecord> selectMindActivityRecordList(MindActivityRecord mindActivityRecord);

    /**
     * 新增活动预约记录
     *
     * @param mindActivityRecord 活动预约记录
     * @return 结果
     */
    public int insertMindActivityRecord(MindActivityRecord mindActivityRecord);

    /**
     * 修改活动预约记录
     *
     * @param mindActivityRecord 活动预约记录
     * @return 结果
     */
    public int updateMindActivityRecord(MindActivityRecord mindActivityRecord);

    /**
     * 删除活动预约记录
     *
     * @param recordId 活动预约记录主键
     * @return 结果
     */
    public int deleteMindActivityRecordByRecordId(Long recordId);

    /**
     * 批量删除活动预约记录
     *
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMindActivityRecordByRecordIds(Long[] recordIds);
    public int deleteMindActivityRecordByActivityIds(Long[] activityIds);
}
