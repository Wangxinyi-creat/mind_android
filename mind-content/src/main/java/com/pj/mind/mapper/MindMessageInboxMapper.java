package com.pj.mind.mapper;

import java.util.List;
import com.pj.mind.domain.MindMessageInbox;

/**
 * 心晴信箱Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-21
 */
public interface MindMessageInboxMapper 
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
     * 删除心晴信箱
     * 
     * @param messageId 心晴信箱主键
     * @return 结果
     */
    public int deleteMindMessageInboxByMessageId(Long messageId);

    /**
     * 批量删除心晴信箱
     * 
     * @param messageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMindMessageInboxByMessageIds(Long[] messageIds);
}
