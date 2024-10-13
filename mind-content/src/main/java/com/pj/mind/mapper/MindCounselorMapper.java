package com.pj.mind.mapper;

import java.util.List;
import com.pj.mind.domain.MindCounselor;

/**
 * 咨询师Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-20
 */
public interface MindCounselorMapper
{
    /**
     * 查询咨询师
     *
     * @param counselorId 咨询师主键
     * @return 咨询师
     */
    public MindCounselor selectMindCounselorByCounselorId(Long counselorId);

    /**
     * 查询咨询师列表
     *
     * @param mindCounselor 咨询师
     * @return 咨询师集合
     */
    public List<MindCounselor> selectMindCounselorList(MindCounselor mindCounselor);

    /**
     * 新增咨询师
     *
     * @param mindCounselor 咨询师
     * @return 结果
     */
    public int insertMindCounselor(MindCounselor mindCounselor);

    /**
     * 修改咨询师
     *
     * @param mindCounselor 咨询师
     * @return 结果
     */
    public int updateMindCounselor(MindCounselor mindCounselor);

    /**
     * 删除咨询师
     *
     * @param counselorId 咨询师主键
     * @return 结果
     */
    public int deleteMindCounselorByCounselorId(Long counselorId);

    /**
     * 批量删除咨询师
     *
     * @param counselorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMindCounselorByCounselorIds(Long[] counselorIds);

    MindCounselor selectMindCounselorByUserId(Long userId);
}
