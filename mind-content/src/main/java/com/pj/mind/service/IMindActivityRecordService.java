package com.pj.mind.service;

import java.util.List;
import com.pj.mind.domain.MindActivityRecord;

/**
 * 活动预约记录Service接口
 *
 * @author ruoyi
 * @date 2024-04-20
 */
public interface IMindActivityRecordService
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
}
