package com.pj.mind.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.pj.common.core.domain.entity.SysUser;
import com.pj.common.utils.DateUtils;
import com.pj.mind.domain.MindCategory;
import com.pj.mind.mapper.MindCategoryMapper;
import com.pj.system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindArticleMapper;
import com.pj.mind.domain.MindArticle;
import com.pj.mind.service.IMindArticleService;

/**
 * 文章Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@Service
public class MindArticleServiceImpl implements IMindArticleService {
    @Autowired
    private MindArticleMapper mindArticleMapper;
    @Autowired
    private MindCategoryMapper mindCategoryMapper;
    @Autowired
    private SysUserServiceImpl sysUserService;

    /**
     * 查询文章
     *
     * @param id 文章主键
     * @return 文章
     */
    @Override
    public MindArticle selectMindArticleById(Long id) {
        MindArticle article = mindArticleMapper.selectMindArticleById(id);
        if (article != null) {
            if (article.getUserId() != null && article.getUserId() > 0) {
                SysUser sysUser = sysUserService.selectUserById(article.getUserId());
                article.setUser(sysUser);
            }
            MindCategory category = mindCategoryMapper.selectMindCategoryById(article.getCategoryId());
            article.setCategoryTitle(category.getTitle());
        }
        return article;
    }


    @Override
    public List<MindArticle> selectMindArticleListVideoByIds(Long[] ids) {

        return mindArticleMapper.selectMindArticleListVideoByIds(ids);
    }

    /**
     * 查询文章列表
     *
     * @param mindArticle 文章
     * @return 文章
     */
    @Override
    public List<MindArticle> selectMindArticleList(MindArticle mindArticle) {
        List<MindArticle> list = mindArticleMapper.selectMindArticleList(mindArticle);
        if (list.isEmpty()) {
            return list;
        }
        return list.stream().peek(article -> {
            if (article.getUserId() != null && article.getUserId() > 0) {
                SysUser sysUser = sysUserService.selectUserById(article.getUserId());
                article.setUser(sysUser);
            }
            MindCategory category = mindCategoryMapper.selectMindCategoryById(article.getCategoryId());
            article.setCategoryTitle(category.getTitle());
        }).collect(Collectors.toList());
    }

    /**
     * 新增文章
     *
     * @param mindArticle 文章
     * @return 结果
     */
    @Override
    public int insertMindArticle(MindArticle mindArticle) {
        mindArticle.setCreateTime(DateUtils.getNowDate());
        return mindArticleMapper.insertMindArticle(mindArticle);
    }

    /**
     * 修改文章
     *
     * @param mindArticle 文章
     * @return 结果
     */
    @Override
    public int updateMindArticle(MindArticle mindArticle) {
        mindArticle.setUpdateTime(DateUtils.getNowDate());
        return mindArticleMapper.updateMindArticle(mindArticle);
    }

    /**
     * 批量删除文章
     *
     * @param ids 需要删除的文章主键
     * @return 结果
     */
    @Override
    public int deleteMindArticleByIds(Long[] ids) {
        return mindArticleMapper.deleteMindArticleByIds(ids);
    }

    /**
     * 删除文章信息
     *
     * @param id 文章主键
     * @return 结果
     */
    @Override
    public int deleteMindArticleById(Long id) {
        return mindArticleMapper.deleteMindArticleById(id);
    }
}
