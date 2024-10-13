package com.pj.mind.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.pj.common.core.domain.entity.SysUser;
import com.pj.common.utils.DateUtils;
import com.pj.mind.domain.MindArticle;
import com.pj.mind.mapper.MindArticleMapper;
import com.pj.system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindArticleCommentMapper;
import com.pj.mind.domain.MindArticleComment;
import com.pj.mind.service.IMindArticleCommentService;

/**
 * 评论Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-21
 */
@Service
public class MindArticleCommentServiceImpl implements IMindArticleCommentService {
    @Autowired
    private MindArticleCommentMapper mindArticleCommentMapper;
    @Autowired
    private MindArticleMapper mindArticleMapper;
    @Autowired
    private SysUserServiceImpl sysUserService;

    /**
     * 查询评论
     *
     * @param id 评论主键
     * @return 评论
     */
    @Override
    public MindArticleComment selectMindArticleCommentById(Long id) {
        MindArticleComment comment = mindArticleCommentMapper.selectMindArticleCommentById(id);
        SysUser sysUser = sysUserService.selectUserById(comment.getUserId());
        comment.setUser(sysUser);
        return comment;
    }

    /**
     * 查询评论列表
     *
     * @param mindArticleComment 评论
     * @return 评论
     */
    @Override
    public List<MindArticleComment> selectMindArticleCommentList(MindArticleComment mindArticleComment) {
        List<MindArticleComment> list = mindArticleCommentMapper.selectMindArticleCommentList(mindArticleComment);

        if (list.isEmpty()) {
            return list;
        }
        return list.stream().peek(comment -> {
            SysUser sysUser = sysUserService.selectUserById(comment.getUserId());
            comment.setUser(sysUser);
        }).collect(Collectors.toList());
    }

    /**
     * 新增评论
     *
     * @param mindArticleComment 评论
     * @return 结果
     */
    @Override
    public int insertMindArticleComment(MindArticleComment mindArticleComment) {
        mindArticleComment.setCreateTime(DateUtils.getNowDate());
        int res = mindArticleCommentMapper.insertMindArticleComment(mindArticleComment);
        if (res > 0) {
            MindArticle mindArticle = mindArticleMapper.selectMindArticleById(mindArticleComment.getArticleId());
            mindArticle.setComments(mindArticle.getComments() + 1);
            mindArticleMapper.updateMindArticle(mindArticle);
        }
        return res;
    }

    /**
     * 修改评论
     *
     * @param mindArticleComment 评论
     * @return 结果
     */
    @Override
    public int updateMindArticleComment(MindArticleComment mindArticleComment) {
        mindArticleComment.setUpdateTime(DateUtils.getNowDate());
        return mindArticleCommentMapper.updateMindArticleComment(mindArticleComment);
    }

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的评论主键
     * @return 结果
     */
    @Override
    public int deleteMindArticleCommentByIds(Long[] ids) {
        return mindArticleCommentMapper.deleteMindArticleCommentByIds(ids);
    }

    /**
     * 删除评论信息
     *
     * @param id 评论主键
     * @return 结果
     */
    @Override
    public int deleteMindArticleCommentById(Long id) {
        return mindArticleCommentMapper.deleteMindArticleCommentById(id);
    }
}
