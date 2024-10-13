package com.pj.mind.service.impl;

import java.util.List;
import com.pj.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindCategoryMapper;
import com.pj.mind.domain.MindCategory;
import com.pj.mind.service.IMindCategoryService;

/**
 * 分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-20
 */
@Service
public class MindCategoryServiceImpl implements IMindCategoryService 
{
    @Autowired
    private MindCategoryMapper mindCategoryMapper;

    /**
     * 查询分类
     * 
     * @param id 分类主键
     * @return 分类
     */
    @Override
    public MindCategory selectMindCategoryById(Long id)
    {
        return mindCategoryMapper.selectMindCategoryById(id);
    }

    /**
     * 查询分类列表
     * 
     * @param mindCategory 分类
     * @return 分类
     */
    @Override
    public List<MindCategory> selectMindCategoryList(MindCategory mindCategory)
    {
        return mindCategoryMapper.selectMindCategoryList(mindCategory);
    }

    /**
     * 新增分类
     * 
     * @param mindCategory 分类
     * @return 结果
     */
    @Override
    public int insertMindCategory(MindCategory mindCategory)
    {
        mindCategory.setCreateTime(DateUtils.getNowDate());
        return mindCategoryMapper.insertMindCategory(mindCategory);
    }

    /**
     * 修改分类
     * 
     * @param mindCategory 分类
     * @return 结果
     */
    @Override
    public int updateMindCategory(MindCategory mindCategory)
    {
        mindCategory.setUpdateTime(DateUtils.getNowDate());
        return mindCategoryMapper.updateMindCategory(mindCategory);
    }

    /**
     * 批量删除分类
     * 
     * @param ids 需要删除的分类主键
     * @return 结果
     */
    @Override
    public int deleteMindCategoryByIds(Long[] ids)
    {
        return mindCategoryMapper.deleteMindCategoryByIds(ids);
    }

    /**
     * 删除分类信息
     * 
     * @param id 分类主键
     * @return 结果
     */
    @Override
    public int deleteMindCategoryById(Long id)
    {
        return mindCategoryMapper.deleteMindCategoryById(id);
    }
}
