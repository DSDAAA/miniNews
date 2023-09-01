package com.dunston.mininews.domain.request;

import lombok.Data;

/**
 * 新闻返回数据封装类
 *
 * @author dunston
 */
@Data
public class NewsPageData {
    private String hid;
    private String title;
    private String type;
    private String pageViews;
    private String pastHours;
    private String publisher;
}
