package com.dunston.mininews.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class NewsUserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 31912417163120793L;
    private String username;
    private String userPwd;
    private String nickName;
}
