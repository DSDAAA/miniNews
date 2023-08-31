package com.dunston.mininews.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dunston.mininews.domain.NewsHeadline;
import com.dunston.mininews.service.NewsHeadlineService;
import com.dunston.mininews.mapper.NewsHeadlineMapper;
import org.springframework.stereotype.Service;

/**
* @author 13180
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2023-08-31 16:07:22
*/
@Service
public class NewsHeadlineServiceImpl extends ServiceImpl<NewsHeadlineMapper, NewsHeadline>
    implements NewsHeadlineService{

}




