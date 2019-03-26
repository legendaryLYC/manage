package com.xiongantaoli.background.mapper;

import java.util.List;

import com.xiongantaoli.background.entity.ProductState;

public interface ProductStateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductState record);

    int insertSelective(ProductState record);

    ProductState selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductState record);

    int updateByPrimaryKey(ProductState record);
    
    /**
     * 获取状态表全部信息
     * @return List<ProductState>
     * @author 孟晓冬
     */
    List<ProductState> selectAll();

    /**
     * 根据名字获取对应订单状态名字
     * @param name
     * @return int
     * @author 卜勇峰
     */
	ProductState selectCode(String name);
}