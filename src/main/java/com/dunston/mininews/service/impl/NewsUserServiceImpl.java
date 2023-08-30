package com.dunston.mininews.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dunston.mininews.common.Result;
import com.dunston.mininews.domain.NewsUser;
import com.dunston.mininews.service.NewsUserService;
import com.dunston.mininews.mapper.NewsUserMapper;
import com.dunston.mininews.utils.MD5Util;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author dunston
 * @description 针对表【news_user】的数据库操作Service实现
 * @createDate 2023-08-30 18:35:08
 */
@Service
public class NewsUserServiceImpl extends ServiceImpl<NewsUserMapper, NewsUser>
        implements NewsUserService {

    @Resource
    NewsUserMapper userMapper;

    /**
     * 用户登录
     *
     * @param username
     * @param userpassword
     * @param request
     * @return
     */
    @Override
    public NewsUser login(String username, String userpassword, HttpServletRequest request) {
        //1.根据用户名判断用户是否存在
        //todo 用户名密码长度格式校验判断
        QueryWrapper<NewsUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        NewsUser newsUser = userMapper.selectOne(queryWrapper);
        if (newsUser == null) {
            return null;
        }
        //2.密码加密
        String encryptPwd = MD5Util.encrypt(userpassword);
        //3.根据查询密码判断密码是否正确
        if (!newsUser.getUser_pwd().equals(encryptPwd)) {
            return null;
        }
        //4.用户脱敏
        NewsUser saftUser = getSaftyUser(newsUser);
        //5.返回用户登录态
        request.getSession().setAttribute(Result.ok(saftUser).getMessage(), saftUser);
        return saftUser;
    }

    /**
     * 用户脱敏
     *
     * @param orginUser
     * @return
     */
    public NewsUser getSaftyUser(NewsUser orginUser) {
        //1.判断是否为空
        if (orginUser == null) {
            return null;
        }
        //2.用户脱敏
        NewsUser saftyUser = new NewsUser();
        saftyUser.setUsername(orginUser.getUsername());
        saftyUser.setUser_pwd(orginUser.getUser_pwd());
        saftyUser.setUid(orginUser.getUid());
        saftyUser.setNick_name(orginUser.getNick_name());
        return saftyUser;
    }
}




