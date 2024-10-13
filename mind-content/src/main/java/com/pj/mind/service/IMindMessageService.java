package com.pj.mind.service;

import java.util.List;
import com.pj.mind.domain.MindMessage;

/**
 * 消息Service接口
 *
 * @author ruoyi
 * @date 2024-04-21
 */
public interface IMindMessageService
{
    /**
     * 查询消息
     *
     * @param messageId 消息主键
     * @return 消息
     */
    public MindMessage selectMindMessageByMessageId(Long messageId);

    /**
     * 查询消息列表
     *
     * @param mindMessage 消息
     * @return 消息集合
     */
    public List<MindMessage> selectMindMessageList(MindMessage mindMessage);
    /**
     * 查询消息列表
     *
     * @param mindMessage 消息
     * @return 消息集合
     */
    public List<MindMessage> selectMindMessageListAll(MindMessage mindMessage);

    /**
     * 新增消息
     *
     * @param mindMessage 消息
     * @return 结果
     */
    public int insertMindMessage(MindMessage mindMessage);

    /**
     * 修改消息
     *
     * @param mindMessage 消息
     * @return 结果
     */
    public int updateMindMessage(MindMessage mindMessage);

    /**
     * 批量删除消息
     *
     * @param messageIds 需要删除的消息主键集合
     * @return 结果
     */
    public int deleteMindMessageByMessageIds(Long[] messageIds);

    /**
     * 删除消息信息
     *
     * @param messageId 消息主键
     * @return 结果
     */
    public int deleteMindMessageByMessageId(Long messageId);

    List<MindMessage> selectMindMessageByMessageIdNext(Long messageId, Long recordId);
}
