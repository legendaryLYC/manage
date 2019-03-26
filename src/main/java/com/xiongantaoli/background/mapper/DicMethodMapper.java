package com.xiongantaoli.background.mapper;

import java.util.List;

import com.xiongantaoli.background.entity.ProductDicMethod;

public interface DicMethodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductDicMethod record);

    int insertSelective(ProductDicMethod record);

    ProductDicMethod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductDicMethod record);

    int updateByPrimaryKey(ProductDicMethod record);
    
    /**
     * 获得该字典List
     * @author 孟晓冬
     * @return
     */
    List<ProductDicMethod> selectAll();
}