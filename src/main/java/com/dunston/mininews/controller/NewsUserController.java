package com.dunston.mininews.controller;

import com.dunston.mininews.domain.NewsUser;
import com.dunston.mininews.service.NewsUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsUserController {
    @Resource
    NewsUserService newsUserService;

    @PostMapping("/login")
    public String login(@RequestBody NewsUser newUser) {
        return "";
    }
}
