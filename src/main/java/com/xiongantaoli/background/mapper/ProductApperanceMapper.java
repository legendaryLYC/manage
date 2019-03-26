package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.ProductApperance;


public interface ProductApperanceMapper {
	public List<ProductApperance> selectallapperance();
	
    /**
          * 在精准报价页面查找出表中所有的品牌
	* @param List<ProductModel>
	* @return ProductInfo
	* @author 李永成
	*/
    public List<ProductApperance> selectAll();
    
    int insert(ProductApperance record);

	public List<ProductApperance> selectapperance(@Param("name")String name);

	public boolean deletebyid(int id);

	public boolean insertapperance(@Param("code")int code, @Param("name")String name);

	public boolean updatebyid(ProductApperance apperance); // 此处由于业务需求已经把update变成插入函数

	public ProductApperance selectByPrimaryKey(Long id);

	/**
	 * 永成写的,孟晓冬加了个注解,里边的name加了if判断
	 * @param name
	 * @param code
	 * @return
	 */
	public ProductApperance selectapperancebyname(@Param("name")String name, @Param("code")int code);
}
