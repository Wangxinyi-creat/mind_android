package com.pj.mind.service;

import java.util.List;
import com.pj.mind.domain.MindCounselorRecord;

/**
 * 咨询师预约记录Service接口
 * 
 * @author ruoyi
 * @date 2024-04-20
 */
public interface IMindCounselorRecordService 
{
    /**
     * 查询咨询师预约记录
     * 
     * @param recordId 咨询师预约记录主键
     * @return 咨询师预约记录
     */
    public MindCounselorRecord selectMindCounselorRecordByRecordId(Long recordId);

    /**
     * 查询咨询师预约记录列表
     * 
     * @param mindCounselorRecord 咨询师预约记录
     * @return 咨询师预约记录集合
     */
    public List<MindCounselorRecord> selectMindCounselorRecordList(MindCounselorRecord mindCounselorRecord);

    /**
     * 新增咨询师预约记录
     * 
     * @param mindCounselorRecord 咨询师预约记录
     * @return 结果
     */
    public int insertMindCounselorRecord(MindCounselorRecord mindCounselorRecord);

    /**
     * 修改咨询师预约记录
     * 
     * @param mindCounselorRecord 咨询师预约记录
     * @return 结果
     */
    public int updateMindCounselorRecord(MindCounselorRecord mindCounselorRecord);

    /**
     * 批量删除咨询师预约记录
     * 
     * @param recordIds 需要删除的咨询师预约记录主键集合
     * @return 结果
     */
    public int deleteMindCounselorRecordByRecordIds(Long[] recordIds);

    /**
     * 删除咨询师预约记录信息
     * 
     * @param recordId 咨询师预约记录主键
     * @return 结果
     */
    public int deleteMindCounselorRecordByRecordId(Long recordId);
}
