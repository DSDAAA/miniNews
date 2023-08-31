package com.dunston.mininews.controller;

import com.dunston.mininews.common.Result;
import com.dunston.mininews.common.ResultCodeEnum;
import com.dunston.mininews.domain.NewsUser;
import com.dunston.mininews.domain.request.NewsUserLoginRequest;
import com.dunston.mininews.service.NewsUserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 *
 * @author dunston
 */
@RestController
@RequestMapping("/user")
public class NewsUserController {
    @Resource
    NewsUserService newsUserService;

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<NewsUser> login(@RequestBody NewsUserLoginRequest userLoginRequest, HttpServletRequest request) {
        //1.判断是否为空
        if (userLoginRequest == null) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN.getCode(), ResultCodeEnum.NOTLOGIN.getMessage());
        }
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getUserPwd();
        //2.用户名和密码任意判断为空
        if (username.equals("")) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if (password.equals("")) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }
        NewsUser result = newsUserService.login(username, password, request);
        return Result.ok(result);
    }
    @GetMapping("/getUserInfo")
    public Result getLoginUserInfo(){
        return null;
    }
}
