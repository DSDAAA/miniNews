package com.dunston.mininews.service;

import com.dunston.mininews.domain.NewsType;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author dunston
 * @description 针对表【news_type】的数据库操作Service
 * @createDate 2023-08-31 15:00:18
 */
public interface NewsTypeService extends IService<NewsType> {
    /**
     * 查询所有type
     *
     * @param request
     * @return
     */
    List<NewsType> findAllTypes(HttpServletRequest request);
}
