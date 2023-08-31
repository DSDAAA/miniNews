package com.dunston.mininews.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.NewsType;
import com.dunston.mininews.service.NewsTypeService;
import com.dunston.mininews.mapper.NewsTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author 13180
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2023-08-31 15:00:18
*/
@Service
public class NewsTypeServiceImpl extends ServiceImpl<NewsTypeMapper, NewsType>
    implements NewsTypeService{

}




