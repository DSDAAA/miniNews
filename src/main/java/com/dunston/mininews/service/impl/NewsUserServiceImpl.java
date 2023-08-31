package com.dunston.mininews.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dunston.mininews.common.Result;
import com.dunston.mininews.common.ResultCodeEnum;
import com.dunston.mininews.domain.NewsUser;
import com.dunston.mininews.exception.BusinessException;
import com.dunston.mininews.service.NewsUserService;
import com.dunston.mininews.mapper.NewsUserMapper;
import com.dunston.mininews.utils.JwtHelper;
import com.dunston.mininews.utils.MD5Util;
import com.dunston.mininews.utils.WebUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
     * @return token
     */
    @Override
    public String login(String username, String userpassword, HttpServletRequest request) {
        //1.根据用户名判断用户是否存在
        //todo 用户名密码长度格式校验判断
        QueryWrapper<NewsUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        NewsUser newsUser = userMapper.selectOne(queryWrapper);
        if (newsUser == null) {
            throw new BusinessException(ResultCodeEnum.USERNAME_ERROR.getMessage(), ResultCodeEnum.USERNAME_ERROR.getCode(), "用户账号错误");
        }
        //2.密码加密
        String encryptPwd = MD5Util.encrypt(userpassword);
        //3.根据查询密码判断密码是否正确
        if (!newsUser.getUser_pwd().equals(encryptPwd)) {
            throw new BusinessException(ResultCodeEnum.PASSWORD_ERROR.getMessage(), ResultCodeEnum.PASSWORD_ERROR.getCode(), "用户密码错误");
        }
        //4.用户脱敏
        NewsUser saftUser = getSaftyUser(newsUser);
        //5.JWT对用户信息进行加密并获取token
        String token = JwtHelper.createToken(Long.valueOf(saftUser.getUid()));
        //6.返回用户登录态
        request.getSession().setAttribute(Result.ok(saftUser).getMessage(), saftUser);
        return token;
    }

    @Override
    public NewsUser getUserInfo(HttpServletRequest request) {
        //1.根据token获取用户id
        Long userId = JwtHelper.getUserId(request.getHeader("token"));
        QueryWrapper<NewsUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", userId);
        //2.根据用户id查询数据库并获取用户完整信息
        NewsUser newsUser = userMapper.selectOne(queryWrapper);
        if (newsUser == null) {
            throw new BusinessException(ResultCodeEnum.NOTLOGIN.getMessage(), ResultCodeEnum.NOTLOGIN.getCode(), "用户信息获取失败,请检查用户是否登录");
        }
        //3.用户脱敏
        NewsUser saftUser = getSaftyUser(newsUser);
        //返回用户信息
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
            throw new BusinessException(ResultCodeEnum.NOTLOGIN.getMessage(), ResultCodeEnum.NOTLOGIN.getCode(), "用户获取异常");
        }
        //2.用户脱敏
        NewsUser saftyUser = new NewsUser();
        saftyUser.setUsername(orginUser.getUsername());
        saftyUser.setUser_pwd(orginUser.getUser_pwd());
        saftyUser.setUid(orginUser.getUid());
        saftyUser.setNick_name(orginUser.getNick_name());
        return saftyUser;
    }

    /**
     * 校验用户名是否被使用
     *
     * @param username
     * @return
     */
    @Override
    public Boolean checkUserNameAllPath(String username, HttpServletRequest request) {
        //1.用户名截取处理
        String subUsername = username.substring(9);
        //2.判断用户名是否为空
        if (subUsername.equals("")) {
            throw new BusinessException(ResultCodeEnum.USERNAME_ERROR.getMessage(), ResultCodeEnum.USERNAME_ERROR.getCode(), "用户名为空");
        }
        //3.根据用户名查询数据库
        QueryWrapper<NewsUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", subUsername);
        NewsUser newsUser = userMapper.selectOne(queryWrapper);
        //4.用户名已被使用
        if (newsUser != null) {
            throw new BusinessException(ResultCodeEnum.USERNAME_USED.getMessage(), ResultCodeEnum.USERNAME_USED.getCode(), "用户名已被使用");
        }
        return false;
    }

    @Override
    public Boolean checkUserName(String username) {
        //1.判断用户名是否为空
        if (username.equals("")) {
            throw new BusinessException(ResultCodeEnum.USERNAME_ERROR.getMessage(), ResultCodeEnum.USERNAME_ERROR.getCode(), "用户名为空");
        }
        //2.根据用户名查询数据库
        QueryWrapper<NewsUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        NewsUser newsUser = userMapper.selectOne(queryWrapper);
        //3.用户名已被使用
        if (newsUser != null) {
            throw new BusinessException(ResultCodeEnum.USERNAME_USED.getMessage(), ResultCodeEnum.USERNAME_USED.getCode(), "用户名已被使用");
        }
        return false;
    }

    /**
     * 用户注册
     *
     * @param username
     * @param userPwd
     * @param nickName
     * @param request
     * @return
     */
    @Override
    public NewsUser regist(String username, String userPwd, String nickName, HttpServletRequest request) {
        //用户名是否注册
        if (checkUserName(username)) {
            throw new BusinessException(ResultCodeEnum.USERNAME_USED.getMessage(), ResultCodeEnum.USERNAME_USED.getCode(), "用户名已经被注册");
        }
        //1.判断用户名是否合法
        if (username.length() < 4) {
            throw new BusinessException(ResultCodeEnum.USERNAME_ERROR.getMessage(), ResultCodeEnum.USERNAME_ERROR.getCode(), "用户名不能小于4位");
        }
        //2.判断密码是否合法
        if (userPwd.length() < 6) {
            throw new BusinessException(ResultCodeEnum.PASSWORD_ERROR.getMessage(), ResultCodeEnum.PASSWORD_ERROR.getCode(), "密码不能小于6位");
        }
        //3.判断姓名是否合法
        if (nickName.length() < 2) {
            throw new BusinessException(ResultCodeEnum.USERNAME_ERROR.getMessage(), ResultCodeEnum.USERNAME_ERROR.getCode(), "姓名必须大于等于2位");
        }
        if (nickName.length() > 6) {
            throw new BusinessException(ResultCodeEnum.USERNAME_ERROR.getMessage(), ResultCodeEnum.USERNAME_ERROR.getCode(), "姓名必须小于6位");
        }
        if (nickName.equals("")) {
            throw new BusinessException(ResultCodeEnum.USERNAME_ERROR.getMessage(), ResultCodeEnum.USERNAME_ERROR.getCode(), "姓名不能为空");
        }
        //4.用户注册
        NewsUser newUser = new NewsUser();
        newUser.setUsername(username);
        //5.用户密码加密
        String encryptPwd = MD5Util.encrypt(userPwd);
        newUser.setUser_pwd(encryptPwd);
        newUser.setNick_name(nickName);
        //6.数据库插入新用户数据
        int insert = userMapper.insert(newUser);
        if (insert == 0) {
            throw new BusinessException(ResultCodeEnum.NOTLOGIN.getMessage(), ResultCodeEnum.NOTLOGIN.getCode(), "用户注册失败");
        }
        return newUser;
    }
}




