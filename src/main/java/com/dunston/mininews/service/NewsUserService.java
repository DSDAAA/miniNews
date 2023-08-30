package com.dunston.mininews.service;

import com.dunston.mininews.domain.NewsUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dunston.mininews.domain.request.NewsUserLoginRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author 13180
 * @description 针对表【news_user】的数据库操作Service
 * @createDate 2023-08-30 18:35:08
 */
public interface NewsUserService extends IService<NewsUser> {
    NewsUser login(NewsUserLoginRequest newsUserLoginRequest);
}
