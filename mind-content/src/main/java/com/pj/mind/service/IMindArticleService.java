package com.pj.mind.service;

import java.util.List;
import com.pj.mind.domain.MindArticle;

/**
 * 文章Service接口
 * 
 * @author ruoyi
 * @date 2024-04-20
 */
public interface IMindArticleService 
{
    /**
     * 查询文章
     * 
     * @param id 文章主键
     * @return 文章
     */
    public MindArticle selectMindArticleById(Long id);

    /**
     * 查询文章列表
     * 
     * @param mindArticle 文章
     * @return 文章集合
     */
    public List<MindArticle> selectMindArticleList(MindArticle mindArticle);

    /**
     * 新增文章
     * 
     * @param mindArticle 文章
     * @return 结果
     */
    public int insertMindArticle(MindArticle mindArticle);

    /**
     * 修改文章
     * 
     * @param mindArticle 文章
     * @return 结果
     */
    public int updateMindArticle(MindArticle mindArticle);

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的文章主键集合
     * @return 结果
     */
    public int deleteMindArticleByIds(Long[] ids);

    /**
     * 删除文章信息
     * 
     * @param id 文章主键
     * @return 结果
     */
    public int deleteMindArticleById(Long id);


    List<MindArticle> selectMindArticleListVideoByIds(Long[] ids);
}
