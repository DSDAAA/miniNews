package com.dunston.mininews.service;

import com.dunston.mininews.domain.NewsUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dunston.mininews.domain.request.NewsUserLoginRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author dunston
 * @description 针对表【news_user】的数据库操作Service
 * @createDate 2023-08-30 18:35:08
 */
public interface NewsUserService extends IService<NewsUser> {
    /**
     * 用户登录
     *
     * @param username
     * @param userpassword
     * @param request
     * @return token
     */
    String login(String username, String userpassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param orginUser
     * @return
     */
    public NewsUser getSaftyUser(NewsUser orginUser);
}
