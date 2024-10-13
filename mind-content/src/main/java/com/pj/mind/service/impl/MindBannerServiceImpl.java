package com.pj.mind.service.impl;

import java.util.List;
import com.pj.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindBannerMapper;
import com.pj.mind.domain.MindBanner;
import com.pj.mind.service.IMindBannerService;

/**
 * 幻灯片Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@Service
public class MindBannerServiceImpl implements IMindBannerService
{
    @Autowired
    private MindBannerMapper mindBannerMapper;

    /**
     * 查询幻灯片
     *
     * @param id 幻灯片主键
     * @return 幻灯片
     */
    @Override
    public MindBanner selectMindBannerById(Long id)
    {
        return mindBannerMapper.selectMindBannerById(id);
    }

    /**
     * 查询幻灯片列表
     *
     * @param mindBanner 幻灯片
     * @return 幻灯片
     */
    @Override
    public List<MindBanner> selectMindBannerList(MindBanner mindBanner)
    {
        return mindBannerMapper.selectMindBannerList(mindBanner);
    }

    /**
     * 新增幻灯片
     *
     * @param mindBanner 幻灯片
     * @return 结果
     */
    @Override
    public int insertMindBanner(MindBanner mindBanner)
    {
        mindBanner.setCreateTime(DateUtils.getNowDate());
        mindBanner.setUpdateTime(DateUtils.getNowDate());
        return mindBannerMapper.insertMindBanner(mindBanner);
    }

    /**
     * 修改幻灯片
     *
     * @param mindBanner 幻灯片
     * @return 结果
     */
    @Override
    public int updateMindBanner(MindBanner mindBanner)
    {
        mindBanner.setUpdateTime(DateUtils.getNowDate());
        return mindBannerMapper.updateMindBanner(mindBanner);
    }

    /**
     * 批量删除幻灯片
     *
     * @param ids 需要删除的幻灯片主键
     * @return 结果
     */
    @Override
    public int deleteMindBannerByIds(Long[] ids)
    {
        return mindBannerMapper.deleteMindBannerByIds(ids);
    }

    /**
     * 删除幻灯片信息
     *
     * @param id 幻灯片主键
     * @return 结果
     */
    @Override
    public int deleteMindBannerById(Long id)
    {
        return mindBannerMapper.deleteMindBannerById(id);
    }
}
