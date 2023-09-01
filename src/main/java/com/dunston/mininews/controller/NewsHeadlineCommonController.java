package com.dunston.mininews.controller;

import com.dunston.mininews.common.Result;
import com.dunston.mininews.common.ResultCodeEnum;
import com.dunston.mininews.domain.request.NewsFindHeadlineRequest;
import com.dunston.mininews.domain.request.NewsPublishRequest;
import com.dunston.mininews.service.NewsHeadlineService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/headline")
public class NewsHeadlineCommonController {
    @Resource
    NewsHeadlineService headlineService;
    /**
     * 发布新闻
     *
     * @param newsPublishRequest
     * @param request
     * @return
     */
    @PostMapping("/publish")
    public Result publish(@RequestBody NewsPublishRequest newsPublishRequest, HttpServletRequest request) {
        Boolean publish = headlineService.publish(newsPublishRequest, request);
        if (!publish) {
            return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
        }
        return Result.ok(null);
    }

    /**
     * 通过新闻编号查找新闻
     *
     * @param hid
     * @param request
     * @return
     */
    @PostMapping("/findHeadlineByHid")
    public Result findHeadlineByHid(@RequestBody String hid, HttpServletRequest request) {
        NewsFindHeadlineRequest headlineByHid = headlineService.findHeadlineByHid(hid, request);
        return Result.ok(headlineByHid);
    }

    /**
     * 更新新闻
     *
     * @param findHeadlineRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody NewsFindHeadlineRequest findHeadlineRequest, HttpServletRequest request) {
        Boolean update = headlineService.update(findHeadlineRequest, request);
        if (!update) {
            return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
        }
        return Result.ok(null);
    }

    /**
     * 删除新闻
     *
     * @param hid
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody String hid, HttpServletRequest request) {
        Boolean delete = headlineService.delete(hid, request);
        if (!delete) {
            return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
        }
        return Result.ok(null);
    }
}
