package com.dunston.mininews.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dunston.mininews.domain.NewsType;
import com.dunston.mininews.service.NewsTypeService;
import com.dunston.mininews.mapper.NewsTypeMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author duston
 * @description 针对表【news_type】的数据库操作Service实现
 * @createDate 2023-08-31 15:00:18
 */
@Service
public class NewsTypeServiceImpl extends ServiceImpl<NewsTypeMapper, NewsType>
        implements NewsTypeService {
    @Resource
    NewsTypeMapper typeMapper;

    /**
     * 查询所有type
     *
     * @param request
     * @return
     */
    @Override
    public List<NewsType> findAllTypes(HttpServletRequest request) {
        List<NewsType> newsTypes = typeMapper.selectList(null);
        return newsTypes;
    }
}




