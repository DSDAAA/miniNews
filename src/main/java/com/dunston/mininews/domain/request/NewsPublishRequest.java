package com.dunston.mininews.domain.request;

import lombok.Data;

/**
 * 新闻发布封装请求类
 *
 */
@Data
public class NewsPublishRequest {
    private String title;
    private String article;
    private String type;
}
