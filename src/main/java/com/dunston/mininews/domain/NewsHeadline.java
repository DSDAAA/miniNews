package com.dunston.mininews.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName news_headline
 */
@TableName(value ="news_headline")
@Data
public class NewsHeadline implements Serializable {
    /**
     * 头条id
     */
    @TableId(type = IdType.AUTO)
    private Integer hid;

    /**
     * 头条标题
     */
    private String title;

    /**
     * 头条新闻内容
     */
    private String article;

    /**
     * 头条类型id
     */
    private Integer type;

    /**
     * 头条发布用户id
     */
    private Integer publisher;

    /**
     * 头条浏览量
     */
    private Integer page_views;

    /**
     * 头条发布时间
     */
    private Date create_time;

    /**
     * 头条最后的修改时间
     */
    private Date update_time;

    /**
     * 头条是否被删除 1 删除  0 未删除
     */
    private Integer is_deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        NewsHeadline other = (NewsHeadline) that;
        return (this.getHid() == null ? other.getHid() == null : this.getHid().equals(other.getHid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getArticle() == null ? other.getArticle() == null : this.getArticle().equals(other.getArticle()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPublisher() == null ? other.getPublisher() == null : this.getPublisher().equals(other.getPublisher()))
            && (this.getPage_views() == null ? other.getPage_views() == null : this.getPage_views().equals(other.getPage_views()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
            && (this.getIs_deleted() == null ? other.getIs_deleted() == null : this.getIs_deleted().equals(other.getIs_deleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHid() == null) ? 0 : getHid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getArticle() == null) ? 0 : getArticle().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPublisher() == null) ? 0 : getPublisher().hashCode());
        result = prime * result + ((getPage_views() == null) ? 0 : getPage_views().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getIs_deleted() == null) ? 0 : getIs_deleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hid=").append(hid);
        sb.append(", title=").append(title);
        sb.append(", article=").append(article);
        sb.append(", type=").append(type);
        sb.append(", publisher=").append(publisher);
        sb.append(", page_views=").append(page_views);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
