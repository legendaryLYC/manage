package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.ProductStandard;

public interface ProductStandardMapper {
	public List<ProductStandard> selectallstandard();

	public List<ProductStandard> selectstandard(String name);

	public boolean deletebyname();

	public boolean insertstandard(@Param("code")int code, @Param("name")String name);

	public boolean updatebyid(@Param("id")int id, @Param("code")int code,@Param("name") String name);
	
    int updateByPrimaryKey(ProductStandard record);

    /**
           * 在精准报价页面查找出表中所有的规格
	* @param List<ProductModel>
	* @return ProductInfo
	* @author 周天
	*/
	List<ProductStandard> selectAll();

	public ProductStandard selectstandardbyname(String name, int code);

	public boolean deletebyid(int id);

	public ProductStandard selectByPrimaryKey(Long id);

	public boolean updatebyid(ProductStandard standard2);
}
