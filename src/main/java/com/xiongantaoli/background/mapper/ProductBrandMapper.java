package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.xiongantaoli.background.entity.ProductBrand;


public interface ProductBrandMapper {
	public List<ProductBrand> selectallbrand();
	
    /**
          * 在精准报价页面查找出表中所有的品牌
	* @param List<ProductModel>
	* @return ProductInfo
	* @author 李永成
	*/
    public List<ProductBrand> selectAll();
    
    int insert(ProductBrand record);

	public List<ProductBrand> selectbrand(@Param("name")String name);

	public boolean deletebyid(int id);

	public boolean insertbrand(@Param("code")int code, @Param("name")String name);

	public boolean updatebyid(ProductBrand brand); // 此处由于业务需求已经把update变成插入函数

	public ProductBrand selectByPrimaryKey(Long id);

	public ProductBrand selectbrandbyname(String name, int code);
}
