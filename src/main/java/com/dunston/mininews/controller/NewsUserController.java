package com.dunston.mininews.controller;

import com.alibaba.fastjson.JSON;
import com.dunston.mininews.common.Result;
import com.dunston.mininews.common.ResultCodeEnum;
import com.dunston.mininews.domain.NewsUser;
import com.dunston.mininews.domain.request.NewsUserLoginRequest;
import com.dunston.mininews.domain.request.NewsUserRegisterRequest;
import com.dunston.mininews.service.NewsUserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户逻辑层
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
    public Result<Map> login(@RequestBody NewsUserLoginRequest userLoginRequest, HttpServletRequest request) {
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
        //3.获取token
        String token = newsUserService.login(username, password, request);
        //4.用map封装token数据
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("token", token);
        return Result.ok(userInfo);
    }

    /**
     * 获取用户详细信息
     *
     * @param request
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result<Map> getLoginUserInfo(@Autowired HttpServletRequest request) {
        //1.判断token是否为空
        String token = request.getHeader("token");
        if (token.equals("")) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        //2.根据token获取用户信息
        NewsUser loginUser = newsUserService.getUserInfo(request);
        //3.将用户信息封装为json
        Object json = JSON.toJSON(loginUser);
        //4.用map封装json
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("loginUser", json);
        return Result.ok(userInfo);
    }

    /**
     * 校验用户名是否重复
     *
     * @param username
     * @param request
     * @return
     */
    @PostMapping("/checkUserName")
    public Result checkUserName(@RequestBody String username, @Autowired HttpServletRequest request) {
        //1.判断用户名是否为空
        if (username.equals("")) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        //2.校验用户名是否重复
        Boolean checkUserName = newsUserService.checkUserNameAllPath(username, request);
        //3.双重校验用户名是否重复
        if (checkUserName) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);
    }

    @PostMapping("/regist")
    public Result regist(@RequestBody NewsUserRegisterRequest newsUserRegisterRequest, @Autowired HttpServletRequest request) {
        //1.获取用户信息
        String username = newsUserRegisterRequest.getUsername();
        String password = newsUserRegisterRequest.getUserPwd();
        String nickName = newsUserRegisterRequest.getNickName();
        //2.判断用户名是否合法
        if (username.length() < 4) {
            return Result.build(null, ResultCodeEnum.SYSTEM_ERROR.getCode(), "用户名不合法");
        }
        //3.判断密码是否合法
        if (password.length() < 6) {
            return Result.build(null, ResultCodeEnum.SYSTEM_ERROR.getCode(), "密码不合法");
        }
        //4.判断姓名是否合法
        if (nickName.length() < 2) {
            return Result.build(null, ResultCodeEnum.SYSTEM_ERROR.getCode(), "姓名不合法");
        }
        if (nickName.length() > 6) {
            return Result.build(null, ResultCodeEnum.SYSTEM_ERROR.getCode(), "用姓名不合法");
        }
        if (nickName.equals("")) {
            return Result.build(null, ResultCodeEnum.SYSTEM_ERROR.getCode(), "姓名不能为空");
        }
        //5.用户注册
        newsUserService.regist(username, password, nickName, request);
        return Result.ok(null);
    }
}
