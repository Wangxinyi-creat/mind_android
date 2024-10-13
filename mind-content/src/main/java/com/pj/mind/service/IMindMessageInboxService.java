package com.pj.mind.service;

import java.util.List;
import com.pj.mind.domain.MindMessageInbox;

/**
 * 心晴信箱Service接口
 * 
 * @author ruoyi
 * @date 2024-04-21
 */
public interface IMindMessageInboxService 
{
    /**
     * 查询心晴信箱
     * 
     * @param messageId 心晴信箱主键
     * @return 心晴信箱
     */
    public MindMessageInbox selectMindMessageInboxByMessageId(Long messageId);

    /**
     * 查询心晴信箱列表
     * 
     * @param mindMessageInbox 心晴信箱
     * @return 心晴信箱集合
     */
    public List<MindMessageInbox> selectMindMessageInboxList(MindMessageInbox mindMessageInbox);

    /**
     * 新增心晴信箱
     * 
     * @param mindMessageInbox 心晴信箱
     * @return 结果
     */
    public int insertMindMessageInbox(MindMessageInbox mindMessageInbox);

    /**
     * 修改心晴信箱
     * 
     * @param mindMessageInbox 心晴信箱
     * @return 结果
     */
    public int updateMindMessageInbox(MindMessageInbox mindMessageInbox);

    /**
     * 批量删除心晴信箱
     * 
     * @param messageIds 需要删除的心晴信箱主键集合
     * @return 结果
     */
    public int deleteMindMessageInboxByMessageIds(Long[] messageIds);

    /**
     * 删除心晴信箱信息
     * 
     * @param messageId 心晴信箱主键
     * @return 结果
     */
    public int deleteMindMessageInboxByMessageId(Long messageId);
}
