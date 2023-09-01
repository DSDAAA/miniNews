package com.dunston.mininews.controller;

import com.dunston.mininews.common.Result;
import com.dunston.mininews.domain.NewsType;
import com.dunston.mininews.service.NewsTypeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 新闻类型逻辑层
 *
 * @author dunston
 */
@RestController
@RequestMapping("/portal")
public class NewsTypeController {
    @Resource
    NewsTypeService typeService;

    /**
     * 查询所有新闻类型
     *
     * @param request
     * @return
     */
    @GetMapping("/findAllTypes")
    public Result findAllTypes(@Autowired HttpServletRequest request) {
        //获取所有类型
        List<NewsType> allTypes = typeService.findAllTypes(request);
        return Result.ok(allTypes);
    }
}
