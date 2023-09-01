package com.dunston.mininews.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 新闻展示类
 *
 * @author dunston
 */
@Data
public class NewsHeadlineRequest implements Serializable {
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
    private String typeName;
    private String pageViews;
    private String pastHours;
    private String publisher;
    private String author;
}
