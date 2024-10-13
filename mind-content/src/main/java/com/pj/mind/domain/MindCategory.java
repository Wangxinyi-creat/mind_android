package com.pj.mind.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.TreeEntity;

/**
 * 分类对象 mind_category
 * 
 * @author ruoyi
 * @date 2024-04-20
 */
public class MindCategory extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Long pid;

    /** 标签 */
    @Excel(name = "标签")
    private String tag;

    /** 图标链接 */
    @Excel(name = "图标链接")
    private String iconUrl;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sorted;

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
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public String getTag() 
    {
        return tag;
    }
    public void setIconUrl(String iconUrl) 
    {
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() 
    {
        return iconUrl;
    }
    public void setSorted(Integer sorted) 
    {
        this.sorted = sorted;
    }

    public Integer getSorted() 
    {
        return sorted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("pid", getPid())
            .append("tag", getTag())
            .append("iconUrl", getIconUrl())
            .append("sorted", getSorted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
