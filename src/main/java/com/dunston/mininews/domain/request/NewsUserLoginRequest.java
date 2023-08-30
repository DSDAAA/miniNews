package com.dunston.mininews.domain.request;

import lombok.Data;

@Data
public class NewsUserLoginRequest {
    private String username;
    private String userPwd;

    public NewsUserLoginRequest(String username, String userPwd) {
        this.username = username;
        this.userPwd = userPwd;
    }
}
