package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.ProductColor;


public interface ProductColorMapper {
	public List<ProductColor> selectallcolor();
	
    /**
          * 在精准报价页面查找出表中所有的品牌
	* @param List<ProductModel>
	* @return ProductInfo
	* @author 李永成
	*/
    public List<ProductColor> selectAll();
    
    int insert(ProductColor record);

	public List<ProductColor> selectcolor(@Param("name")String name);

	public boolean deletebyid(int id);

	public boolean insertcolor(@Param("code")int code, @Param("name")String name);

	public boolean updatebyid(ProductColor color); // 此处由于业务需求已经把update变成插入函数

	public ProductColor selectByPrimaryKey(Long id);

	public ProductColor selectcolorbyname(String name, int code);
}
