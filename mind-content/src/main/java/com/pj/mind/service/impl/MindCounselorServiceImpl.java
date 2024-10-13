package com.pj.mind.service.impl;

import java.util.List;
import com.pj.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.mind.mapper.MindCounselorMapper;
import com.pj.mind.domain.MindCounselor;
import com.pj.mind.service.IMindCounselorService;

/**
 * 咨询师Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@Service
public class MindCounselorServiceImpl implements IMindCounselorService
{
    @Autowired
    private MindCounselorMapper mindCounselorMapper;

    /**
     * 查询咨询师
     *
     * @param counselorId 咨询师主键
     * @return 咨询师
     */
    @Override
    public MindCounselor selectMindCounselorByCounselorId(Long counselorId)
    {
        return mindCounselorMapper.selectMindCounselorByCounselorId(counselorId);
    }

    /**
     * 查询咨询师列表
     *
     * @param mindCounselor 咨询师
     * @return 咨询师
     */
    @Override
    public List<MindCounselor> selectMindCounselorList(MindCounselor mindCounselor)
    {
        return mindCounselorMapper.selectMindCounselorList(mindCounselor);
    }

    /**
     * 新增咨询师
     *
     * @param mindCounselor 咨询师
     * @return 结果
     */
    @Override
    public int insertMindCounselor(MindCounselor mindCounselor)
    {
        mindCounselor.setCreateTime(DateUtils.getNowDate());
        return mindCounselorMapper.insertMindCounselor(mindCounselor);
    }

    /**
     * 修改咨询师
     *
     * @param mindCounselor 咨询师
     * @return 结果
     */
    @Override
    public int updateMindCounselor(MindCounselor mindCounselor)
    {
        mindCounselor.setUpdateTime(DateUtils.getNowDate());
        return mindCounselorMapper.updateMindCounselor(mindCounselor);
    }

    /**
     * 批量删除咨询师
     *
     * @param counselorIds 需要删除的咨询师主键
     * @return 结果
     */
    @Override
    public int deleteMindCounselorByCounselorIds(Long[] counselorIds)
    {
        return mindCounselorMapper.deleteMindCounselorByCounselorIds(counselorIds);
    }

    /**
     * 删除咨询师信息
     *
     * @param counselorId 咨询师主键
     * @return 结果
     */
    @Override
    public int deleteMindCounselorByCounselorId(Long counselorId)
    {
        return mindCounselorMapper.deleteMindCounselorByCounselorId(counselorId);
    }

    @Override
    public MindCounselor selectMindCounselorByUserId(Long userId) {

        return mindCounselorMapper.selectMindCounselorByUserId(userId);
    }
}
