package com.dunston.mininews.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dunston.mininews.domain.NewsUser;
import com.dunston.mininews.service.NewsUserService;
import com.dunston.mininews.mapper.NewsUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 13180
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-08-30 18:35:08
*/
@Service
public class NewsUserServiceImpl extends ServiceImpl<NewsUserMapper, NewsUser>
    implements NewsUserService{

}




