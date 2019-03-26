package com.xiongantaoli.background.mapper;

import java.math.BigDecimal;

import com.xiongantaoli.background.entity.QualityRoyalty;

public interface QualityRoyaltyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QualityRoyalty record);

    int insertSelective(QualityRoyalty record);

    QualityRoyalty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QualityRoyalty record);

    int updateByPrimaryKey(QualityRoyalty record);
    /**
     * 查询质检基础提成
     * @return
     */
    BigDecimal selectQualityPrice();
}