package com.dunston.mininews.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 新闻获取信息封装类
 *
 * @author dunston
 */
@Data
public class NewsPageRequest implements Serializable {
    private String keyWords;
    private Integer type;
    private Integer pageNum;
    private String pageSize;
}
