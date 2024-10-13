package com.pj.mind.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.pj.common.utils.DateUtils;
import com.pj.mind.domain.MindActivity;
import com.pj.mind.domain.MindCounselor;
import com.pj.mind.mapper.MindCounselorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindCounselorRecordMapper;
import com.pj.mind.domain.MindCounselorRecord;
import com.pj.mind.service.IMindCounselorRecordService;

/**
 * 咨询师预约记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@Service
public class MindCounselorRecordServiceImpl implements IMindCounselorRecordService {
    @Autowired
    private MindCounselorRecordMapper mindCounselorRecordMapper;
    @Autowired
    private MindCounselorMapper mindCounselorMapper;

    /**
     * 查询咨询师预约记录
     *
     * @param recordId 咨询师预约记录主键
     * @return 咨询师预约记录
     */
    @Override
    public MindCounselorRecord selectMindCounselorRecordByRecordId(Long recordId) {
        MindCounselorRecord counselorRecord = mindCounselorRecordMapper.selectMindCounselorRecordByRecordId(recordId);
        MindCounselor mindCounselor = mindCounselorMapper.selectMindCounselorByCounselorId(counselorRecord.getCounselorId());
        counselorRecord.setMindCounselor(mindCounselor);
        return counselorRecord;
    }

    /**
     * 查询咨询师预约记录列表
     *
     * @param mindCounselorRecord 咨询师预约记录
     * @return 咨询师预约记录
     */
    @Override
    public List<MindCounselorRecord> selectMindCounselorRecordList(MindCounselorRecord mindCounselorRecord) {
        List<MindCounselorRecord> list = mindCounselorRecordMapper.selectMindCounselorRecordList(mindCounselorRecord);

        list.forEach(counselorRecord -> {
            MindCounselor mindCounselor = mindCounselorMapper.selectMindCounselorByCounselorId(counselorRecord.getCounselorId());
            counselorRecord.setMindCounselor(mindCounselor);
        });
        return list;
    }

    /**
     * 新增咨询师预约记录
     *
     * @param mindCounselorRecord 咨询师预约记录
     * @return 结果
     */
    @Override
    public int insertMindCounselorRecord(MindCounselorRecord mindCounselorRecord) {
        mindCounselorRecord.setCreateTime(DateUtils.getNowDate());
        return mindCounselorRecordMapper.insertMindCounselorRecord(mindCounselorRecord);
    }

    /**
     * 修改咨询师预约记录
     *
     * @param mindCounselorRecord 咨询师预约记录
     * @return 结果
     */
    @Override
    public int updateMindCounselorRecord(MindCounselorRecord mindCounselorRecord) {
        mindCounselorRecord.setUpdateTime(DateUtils.getNowDate());
        return mindCounselorRecordMapper.updateMindCounselorRecord(mindCounselorRecord);
    }

    /**
     * 批量删除咨询师预约记录
     *
     * @param recordIds 需要删除的咨询师预约记录主键
     * @return 结果
     */
    @Override
    public int deleteMindCounselorRecordByRecordIds(Long[] recordIds) {
        return mindCounselorRecordMapper.deleteMindCounselorRecordByRecordIds(recordIds);
    }

    /**
     * 删除咨询师预约记录信息
     *
     * @param recordId 咨询师预约记录主键
     * @return 结果
     */
    @Override
    public int deleteMindCounselorRecordByRecordId(Long recordId) {
        return mindCounselorRecordMapper.deleteMindCounselorRecordByRecordId(recordId);
    }
}
