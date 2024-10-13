package com.pj.mind.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;

/**
 * 幻灯片对象 mind_banner
 * 
 * @author ruoyi
 * @date 2024-04-20
 */
public class MindBanner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 幻灯片标题 */
    @Excel(name = "幻灯片标题")
    private String title;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String pictureUrl;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sorted;

    /** 是否展示 */
    @Excel(name = "是否展示")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPictureUrl(String pictureUrl) 
    {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() 
    {
        return pictureUrl;
    }
    public void setSorted(Integer sorted) 
    {
        this.sorted = sorted;
    }

    public Integer getSorted() 
    {
        return sorted;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("pictureUrl", getPictureUrl())
            .append("sorted", getSorted())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
