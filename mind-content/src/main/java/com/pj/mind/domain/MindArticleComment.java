package com.pj.mind.domain;

import com.pj.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * 评论对象 mind_article_comment
 *
 * @author ruoyi
 * @date 2024-04-21
 */
public class MindArticleComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 文章外键 */
    @Excel(name = "文章外键")
    private Long articleId;

    /** 评论人外键 */
    @Excel(name = "评论人外键")
    private Long userId;
    @Transient
    private SysUser user;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setArticleId(Long articleId)
    {
        this.articleId = articleId;
    }

    public Long getArticleId()
    {
        return articleId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("articleId", getArticleId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
