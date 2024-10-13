package com.pj.mind.mapper;

import java.util.List;
import com.pj.mind.domain.MindCategory;

/**
 * 分类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-20
 */
public interface MindCategoryMapper 
{
    /**
     * 查询分类
     * 
     * @param id 分类主键
     * @return 分类
     */
    public MindCategory selectMindCategoryById(Long id);

    /**
     * 查询分类列表
     * 
     * @param mindCategory 分类
     * @return 分类集合
     */
    public List<MindCategory> selectMindCategoryList(MindCategory mindCategory);

    /**
     * 新增分类
     * 
     * @param mindCategory 分类
     * @return 结果
     */
    public int insertMindCategory(MindCategory mindCategory);

    /**
     * 修改分类
     * 
     * @param mindCategory 分类
     * @return 结果
     */
    public int updateMindCategory(MindCategory mindCategory);

    /**
     * 删除分类
     * 
     * @param id 分类主键
     * @return 结果
     */
    public int deleteMindCategoryById(Long id);

    /**
     * 批量删除分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMindCategoryByIds(Long[] ids);
}
