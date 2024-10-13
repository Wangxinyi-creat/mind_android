package com.pj.mind.service;

import java.util.List;
import com.pj.mind.domain.MindArticleComment;

/**
 * 评论Service接口
 * 
 * @author ruoyi
 * @date 2024-04-21
 */
public interface IMindArticleCommentService 
{
    /**
     * 查询评论
     * 
     * @param id 评论主键
     * @return 评论
     */
    public MindArticleComment selectMindArticleCommentById(Long id);

    /**
     * 查询评论列表
     * 
     * @param mindArticleComment 评论
     * @return 评论集合
     */
    public List<MindArticleComment> selectMindArticleCommentList(MindArticleComment mindArticleComment);

    /**
     * 新增评论
     * 
     * @param mindArticleComment 评论
     * @return 结果
     */
    public int insertMindArticleComment(MindArticleComment mindArticleComment);

    /**
     * 修改评论
     * 
     * @param mindArticleComment 评论
     * @return 结果
     */
    public int updateMindArticleComment(MindArticleComment mindArticleComment);

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的评论主键集合
     * @return 结果
     */
    public int deleteMindArticleCommentByIds(Long[] ids);

    /**
     * 删除评论信息
     * 
     * @param id 评论主键
     * @return 结果
     */
    public int deleteMindArticleCommentById(Long id);
}
