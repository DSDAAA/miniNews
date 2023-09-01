package com.dunston.mininews.domain.request;

import lombok.Data;

/**
 * 用户信息封装类
 */
@Data
public class NewsUserInfoRequest {
    private Integer uid;
    private String username;
    private String userPwd;
    private String nickName;
}
