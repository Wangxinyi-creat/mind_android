package com.pj.mind.service.impl;

import java.util.List;
import com.pj.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindMessageInboxMapper;
import com.pj.mind.domain.MindMessageInbox;
import com.pj.mind.service.IMindMessageInboxService;

/**
 * 心晴信箱Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-21
 */
@Service
public class MindMessageInboxServiceImpl implements IMindMessageInboxService 
{
    @Autowired
    private MindMessageInboxMapper mindMessageInboxMapper;

    /**
     * 查询心晴信箱
     * 
     * @param messageId 心晴信箱主键
     * @return 心晴信箱
     */
    @Override
    public MindMessageInbox selectMindMessageInboxByMessageId(Long messageId)
    {
        return mindMessageInboxMapper.selectMindMessageInboxByMessageId(messageId);
    }

    /**
     * 查询心晴信箱列表
     * 
     * @param mindMessageInbox 心晴信箱
     * @return 心晴信箱
     */
    @Override
    public List<MindMessageInbox> selectMindMessageInboxList(MindMessageInbox mindMessageInbox)
    {
        return mindMessageInboxMapper.selectMindMessageInboxList(mindMessageInbox);
    }

    /**
     * 新增心晴信箱
     * 
     * @param mindMessageInbox 心晴信箱
     * @return 结果
     */
    @Override
    public int insertMindMessageInbox(MindMessageInbox mindMessageInbox)
    {
        mindMessageInbox.setCreateTime(DateUtils.getNowDate());
        return mindMessageInboxMapper.insertMindMessageInbox(mindMessageInbox);
    }

    /**
     * 修改心晴信箱
     * 
     * @param mindMessageInbox 心晴信箱
     * @return 结果
     */
    @Override
    public int updateMindMessageInbox(MindMessageInbox mindMessageInbox)
    {
        mindMessageInbox.setUpdateTime(DateUtils.getNowDate());
        return mindMessageInboxMapper.updateMindMessageInbox(mindMessageInbox);
    }

    /**
     * 批量删除心晴信箱
     * 
     * @param messageIds 需要删除的心晴信箱主键
     * @return 结果
     */
    @Override
    public int deleteMindMessageInboxByMessageIds(Long[] messageIds)
    {
        return mindMessageInboxMapper.deleteMindMessageInboxByMessageIds(messageIds);
    }

    /**
     * 删除心晴信箱信息
     * 
     * @param messageId 心晴信箱主键
     * @return 结果
     */
    @Override
    public int deleteMindMessageInboxByMessageId(Long messageId)
    {
        return mindMessageInboxMapper.deleteMindMessageInboxByMessageId(messageId);
    }
}
