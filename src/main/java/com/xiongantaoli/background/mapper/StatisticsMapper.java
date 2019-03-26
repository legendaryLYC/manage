package com.xiongantaoli.background.mapper;

import com.xiongantaoli.background.entity.Statistics;

public interface StatisticsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Statistics record);

    int insertSelective(Statistics record);

    Statistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Statistics record);

    int updateByPrimaryKey(Statistics record);
}