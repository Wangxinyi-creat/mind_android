package com.pj.mind.mapper;

import java.util.List;

import com.pj.mind.domain.MindMessage;
import org.apache.ibatis.annotations.Param;

/**
 * 消息Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-21
 */
public interface MindMessageMapper {
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
     * 删除消息
     *
     * @param messageId 消息主键
     * @return 结果
     */
    public int deleteMindMessageByMessageId(Long messageId);

    /**
     * 批量删除消息
     *
     * @param messageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMindMessageByMessageIds(Long[] messageIds);

    List<MindMessage> selectMindMessageByMessageIdNext(@Param("messageId") Long messageId, @Param("recordId") Long recordId);
}
