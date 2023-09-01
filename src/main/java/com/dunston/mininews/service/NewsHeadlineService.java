package com.dunston.mininews.service;

import com.dunston.mininews.domain.NewsHeadline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dunston.mininews.domain.NewsUser;
import com.dunston.mininews.domain.request.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author 13180
 * @description 针对表【news_headline】的数据库操作Service
 * @createDate 2023-08-31 16:07:22
 */
public interface NewsHeadlineService extends IService<NewsHeadline> {
    /**
     * 新闻信息分页获取
     *
     * @param newsPageRequest
     * @return
     */
    List<NewsPageData> findNewsPage(NewsPageRequest newsPageRequest);

    /**
     * 新闻脱敏化
     *
     * @param newsHeadline
     * @return
     */
    NewsPageData getModelNews(NewsHeadline newsHeadline);

    /**
     * 显示头条新闻
     *
     * @param hid
     * @return
     */
    NewsHeadlineRequest showHeadlineDetail(Integer hid);

    /**
     * 脱敏头条新闻
     *
     * @param newsHeadline
     * @return
     */
    NewsHeadlineRequest getModelShowNews(NewsHeadline newsHeadline);

    /**
     * 新闻发布
     *
     * @param newsPublishRequest
     * @param request
     * @return
     */
    Boolean publish(NewsPublishRequest newsPublishRequest, HttpServletRequest request);

    /**
     * 新闻完整信息查询
     *
     * @param hid
     * @param request
     * @return
     */
    NewsFindHeadlineRequest findHeadlineByHid(String hid, HttpServletRequest request);

    /**
     * @param newsFindHeadlineRequest
     * @return
     */
    Boolean update(NewsFindHeadlineRequest newsFindHeadlineRequest, HttpServletRequest request);

    /**
     * @param newsFindHeadlineRequest
     * @param newsUser
     * @return
     */
    NewsHeadline updateHeadline(NewsFindHeadlineRequest newsFindHeadlineRequest, NewsUser newsUser);

    /**
     *
     * @param hid
     * @param request
     * @return
     */
    Boolean delete(String hid, HttpServletRequest request);
}
