package com.pj.mind.service.impl;

import java.util.List;

import com.pj.common.utils.DateUtils;
import com.pj.mind.domain.MindCounselorRecord;
import com.pj.mind.mapper.MindCounselorRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindMessageMapper;
import com.pj.mind.domain.MindMessage;
import com.pj.mind.service.IMindMessageService;

/**
 * 消息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-21
 */
@Service
public class MindMessageServiceImpl implements IMindMessageService {
    @Autowired
    private MindMessageMapper mindMessageMapper;
    @Autowired
    private MindCounselorRecordMapper mindCounselorRecordMapper;

    /**
     * 查询消息
     *
     * @param messageId 消息主键
     * @return 消息
     */
    @Override
    public MindMessage selectMindMessageByMessageId(Long messageId) {
        return mindMessageMapper.selectMindMessageByMessageId(messageId);
    }

    /**
     * 查询消息列表
     *
     * @param mindMessage 消息
     * @return 消息
     */
    @Override
    public List<MindMessage> selectMindMessageList(MindMessage mindMessage) {
        return mindMessageMapper.selectMindMessageList(mindMessage);
    }

    /**
     * 查询消息列表
     *
     * @param mindMessage 消息
     * @return 消息
     */
    @Override
    public List<MindMessage> selectMindMessageListAll(MindMessage mindMessage) {
        return mindMessageMapper.selectMindMessageListAll(mindMessage);
    }

    /**
     * 新增消息
     *
     * @param mindMessage 消息
     * @return 结果
     */
    @Override
    public int insertMindMessage(MindMessage mindMessage) {
        mindMessage.setCreateTime(DateUtils.getNowDate());
        int res = mindMessageMapper.insertMindMessage(mindMessage);
        MindCounselorRecord mindCounselorRecord = mindCounselorRecordMapper.selectMindCounselorRecordByRecordId(mindMessage.getRecordId());
        if (mindCounselorRecord.getAppointmentStatus()==0){
            mindCounselorRecord.setAppointmentStatus(1);
            mindCounselorRecordMapper.updateMindCounselorRecord(mindCounselorRecord);
        }
        return res;
    }

    /**
     * 修改消息
     *
     * @param mindMessage 消息
     * @return 结果
     */
    @Override
    public int updateMindMessage(MindMessage mindMessage) {
        mindMessage.setUpdateTime(DateUtils.getNowDate());
        return mindMessageMapper.updateMindMessage(mindMessage);
    }

    /**
     * 批量删除消息
     *
     * @param messageIds 需要删除的消息主键
     * @return 结果
     */
    @Override
    public int deleteMindMessageByMessageIds(Long[] messageIds) {
        return mindMessageMapper.deleteMindMessageByMessageIds(messageIds);
    }

    /**
     * 删除消息信息
     *
     * @param messageId 消息主键
     * @return 结果
     */
    @Override
    public int deleteMindMessageByMessageId(Long messageId) {
        return mindMessageMapper.deleteMindMessageByMessageId(messageId);
    }

    @Override
    public List<MindMessage> selectMindMessageByMessageIdNext(Long messageId, Long recordId) {
        return mindMessageMapper.selectMindMessageByMessageIdNext(messageId, recordId);
    }
}
