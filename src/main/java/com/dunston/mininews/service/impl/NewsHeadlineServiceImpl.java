package com.dunston.mininews.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dunston.mininews.domain.NewsHeadline;
import com.dunston.mininews.domain.NewsType;
import com.dunston.mininews.domain.request.NewsHeadlineRequest;
import com.dunston.mininews.domain.request.NewsPageData;
import com.dunston.mininews.domain.request.NewsPageRequest;
import com.dunston.mininews.mapper.NewsTypeMapper;
import com.dunston.mininews.service.NewsHeadlineService;
import com.dunston.mininews.mapper.NewsHeadlineMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 13180
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2023-08-31 16:07:22
 */
@Service
public class NewsHeadlineServiceImpl extends ServiceImpl<NewsHeadlineMapper, NewsHeadline>
        implements NewsHeadlineService {
    @Resource
    NewsHeadlineMapper headlineMapper;
    @Resource
    NewsTypeMapper typeMapper;

    /**
     * 根据条件获取新闻
     *
     * @param pageRequest
     * @return pageDatas
     */
    @Override
    public List<NewsPageData> findNewsPage(NewsPageRequest pageRequest) {
        //1.将获取到的页面容量转为int
        int pageSize = Integer.parseInt(pageRequest.getPageSize());
        //2.获取页面关键词
        String keyWords = pageRequest.getKeyWords();
        //3.获取特定新闻类型
        Integer type = pageRequest.getType();
        //4.分页查询
        Page<NewsHeadline> page = new Page<>(pageRequest.getPageNum(), pageSize);
        //5.根据定义条件查询
        QueryWrapper<NewsHeadline> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", keyWords);
        queryWrapper.eq("type", type);
        headlineMapper.selectPage(page, queryWrapper);
        //6.数据转存
        List<NewsHeadline> records = page.getRecords();
        List<NewsPageData> pageDatas = new ArrayList<>();
        for (NewsHeadline news : records) {
            NewsPageData pageData = getModelNews(news);
            pageDatas.add(pageData);
        }
        return pageDatas;
    }

    /**
     * 新闻脱敏
     *
     * @param newsHeadline
     * @return pageData
     */
    @Override
    public NewsPageData getModelNews(NewsHeadline newsHeadline) {
        //脱敏
        NewsPageData pageData = new NewsPageData();
        pageData.setHid(newsHeadline.getHid().intValue() + "");
        pageData.setTitle(newsHeadline.getTitle());
        pageData.setType(newsHeadline.getType().intValue() + "");
        pageData.setPageViews(newsHeadline.getPage_views().intValue() + "");
        //时间格式化
        Date date1 = new Date();
        Date date2 = newsHeadline.getCreate_time();
        long getTime = date1.getTime() - date2.getTime();
        long num = getTime / (1000 * 60 * 60);
        pageData.setPastHours(num + "");
        pageData.setPublisher(newsHeadline.getPublisher() + "");
        return pageData;
    }

    /**
     * 显示头条新闻
     *
     * @param hid
     * @return
     */
    @Override
    public NewsHeadlineRequest showHeadlineDetail(Integer hid) {
        //定义查询条件
        QueryWrapper<NewsHeadline> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hid", hid);
        NewsHeadline newsHeadline = headlineMapper.selectOne(queryWrapper);
        NewsHeadlineRequest newsHeadlineRequest = getModelShowNews(newsHeadline);
        return newsHeadlineRequest;
    }

    /**
     * 新闻头条脱敏
     *
     * @param newsHeadline
     * @return
     */
    @Override
    public NewsHeadlineRequest getModelShowNews(NewsHeadline newsHeadline) {
        NewsHeadlineRequest newsHeadlineRequest = new NewsHeadlineRequest();
        newsHeadlineRequest.setHid(newsHeadline.getHid());
        newsHeadlineRequest.setTitle(newsHeadline.getTitle());
        newsHeadlineRequest.setArticle(newsHeadline.getArticle());
        newsHeadlineRequest.setType(newsHeadline.getType());
        QueryWrapper<NewsType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid", newsHeadline.getType());
        NewsType newsType = typeMapper.selectOne(queryWrapper);
        newsHeadlineRequest.setTypeName(newsType.getTname());
        newsHeadlineRequest.setPageViews(newsHeadline.getPage_views().intValue() + "");
        //时间处理
        Date date1 = new Date();
        Date date2 = newsHeadline.getCreate_time();
        long getTime = date1.getTime() - date2.getTime();
        long num = getTime / (1000 * 60 * 60);
        newsHeadlineRequest.setPastHours(num + "");
        newsHeadlineRequest.setPublisher(newsHeadline.getPublisher().intValue() + "");
        newsHeadlineRequest.setAuthor(newsHeadlineRequest.getAuthor());
        return newsHeadlineRequest;
    }
}




