package com.pj.mind.service.impl;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.pj.common.exception.ServiceException;
import com.pj.mind.domain.MindActivity;
import com.pj.mind.mapper.MindActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindActivityRecordMapper;
import com.pj.mind.domain.MindActivityRecord;
import com.pj.mind.service.IMindActivityRecordService;

/**
 * 活动预约记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@Service
public class MindActivityRecordServiceImpl implements IMindActivityRecordService {
    @Autowired
    private MindActivityRecordMapper mindActivityRecordMapper;
    @Autowired
    private MindActivityMapper mindActivityMapper;

    /**
     * 查询活动预约记录
     *
     * @param recordId 活动预约记录主键
     * @return 活动预约记录
     */
    @Override
    public MindActivityRecord selectMindActivityRecordByRecordId(Long recordId) {
        MindActivityRecord mindActivityRecord = mindActivityRecordMapper.selectMindActivityRecordByRecordId(recordId);
        MindActivity mindActivity = mindActivityMapper.selectMindActivityByActivityId(mindActivityRecord.getActivityId());
        mindActivityRecord.setMindActivity(mindActivity);
        return mindActivityRecord;
    }

    /**
     * 查询活动预约记录列表
     *
     * @param mindActivityRecord 活动预约记录
     * @return 活动预约记录
     */
    @Override
    public List<MindActivityRecord> selectMindActivityRecordList(MindActivityRecord mindActivityRecord) {
        List<MindActivityRecord> list = mindActivityRecordMapper.selectMindActivityRecordList(mindActivityRecord);
        list.forEach(mindActivityRecord1 -> {
            MindActivity mindActivity = mindActivityMapper.selectMindActivityByActivityId(mindActivityRecord1.getActivityId());
            mindActivityRecord1.setMindActivity(mindActivity);
        });
        if (mindActivityRecord.getAppointmentStatus() == null || mindActivityRecord.getAppointmentStatus() == -1) {
            return list;
        }
        return list.stream().filter(mindActivityRecord12 ->
                mindActivityRecord12.getMindActivity().getAppointmentStatus() == mindActivityRecord.getAppointmentStatus()
        ).collect(Collectors.toList());
    }

    /**
     * 新增活动预约记录
     *
     * @param mindActivityRecord 活动预约记录
     * @return 结果
     */
    @Override
    public int insertMindActivityRecord(MindActivityRecord mindActivityRecord) {
        MindActivity mindActivity = mindActivityMapper.selectMindActivityByActivityId(mindActivityRecord.getActivityId());
        if (mindActivity.getCapacityNow() >= mindActivity.getCapacity()) {
            throw new ServiceException("活动已超过人数限制,预约失败");
        }
        int res = mindActivityRecordMapper.insertMindActivityRecord(mindActivityRecord);
        if (res > 0) {
            mindActivity.setCapacityNow(mindActivity.getCapacityNow() + 1);
            mindActivityMapper.updateMindActivity(mindActivity);
        }
        return res;
    }

}
