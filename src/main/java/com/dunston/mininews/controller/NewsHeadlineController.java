package com.dunston.mininews.controller;

import com.dunston.mininews.common.Result;
import com.dunston.mininews.domain.request.NewsHeadlineRequest;
import com.dunston.mininews.domain.request.NewsPageData;
import com.dunston.mininews.domain.request.NewsPageRequest;
import com.dunston.mininews.service.NewsHeadlineService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新闻业务逻辑层
 *
 * @author
 */
@RestController
@RequestMapping("/portal")
public class NewsHeadlineController {
    @Resource
    NewsHeadlineService headlineService;

    /**
     * 根据新闻限定条件查询并返回新闻
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/findNewsPage")
    public Result findNewsPage(@RequestBody NewsPageRequest pageRequest) {
        //获取
        List<NewsPageData> newsData = headlineService.findNewsPage(pageRequest);
        //封装
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> pageinfo = new HashMap<>();
        pageinfo.put("pageData", newsData);
        pageinfo.put("pageNum", 1);
        pageinfo.put("pageSize", 10);
        pageinfo.put("totalPage", 20);
        pageinfo.put("totalSize", newsData.size());
        data.put("pageInfo", pageinfo);
        return Result.ok(data);
    }

    /**
     * @param hid
     * @return
     */
    @PostMapping("/showHeadlineDetail")
    public Result showHeadlineDetail(@RequestBody String hid) {
        //处理hid
        String subHid = hid.substring(4);
        //根据hid获取
        NewsHeadlineRequest newsHeadlineRequest = headlineService.showHeadlineDetail(Integer.parseInt(subHid));
        Map<String, NewsHeadlineRequest> resultNews = new HashMap<>();
        resultNews.put("headline", newsHeadlineRequest);
        return Result.ok(resultNews);
    }
}
