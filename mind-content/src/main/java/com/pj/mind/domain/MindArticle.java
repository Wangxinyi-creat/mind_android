package com.pj.mind.domain;

import com.pj.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * 文章对象 mind_article
 *
 * @author ruoyi
 * @date 2024-04-20
 */
public class MindArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章ID */
    private Long id;

    /** 分类ID */
    @Excel(name = "分类ID")
    private Long categoryId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;
    @Transient
    private SysUser user;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String title;

    @Transient
    private String categoryTitle;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String content;

    /** 是否视频 */
    @Excel(name = "是否视频")
    private Integer isVideo;

    /** 视频链接 */
    @Excel(name = "视频链接")
    private String videoUrl;

    /** 观看次数 */
    @Excel(name = "观看次数")
    private Long views;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likes;

    /** 评论数 */
    @Excel(name = "评论数")
    private Long comments;

    /** 是否匿名 */
    @Excel(name = "是否匿名")
    private Integer anonymous;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setIsVideo(Integer isVideo)
    {
        this.isVideo = isVideo;
    }

    public Integer getIsVideo()
    {
        return isVideo;
    }
    public void setVideoUrl(String videoUrl)
    {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl()
    {
        return videoUrl;
    }
    public void setViews(Long views)
    {
        this.views = views;
    }

    public Long getViews()
    {
        return views;
    }
    public void setLikes(Long likes)
    {
        this.likes = likes;
    }

    public Long getLikes()
    {
        return likes;
    }
    public void setComments(Long comments)
    {
        this.comments = comments;
    }

    public Long getComments()
    {
        return comments;
    }
    public void setAnonymous(Integer anonymous)
    {
        this.anonymous = anonymous;
    }

    public Integer getAnonymous()
    {
        return anonymous;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryId", getCategoryId())
            .append("userId", getUserId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("isVideo", getIsVideo())
            .append("videoUrl", getVideoUrl())
            .append("views", getViews())
            .append("likes", getLikes())
            .append("comments", getComments())
            .append("anonymous", getAnonymous())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
