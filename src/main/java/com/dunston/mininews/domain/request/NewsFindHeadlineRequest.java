package com.dunston.mininews.domain.request;

import lombok.Data;

/**
 * 新闻查找中间类
 */
@Data
public class NewsFindHeadlineRequest {
    private String hid;
    private String title;
    private String article;
    private String type;
}
