package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.ProductDicMethod;


public interface ProductDicMethodMapper {
	public List<ProductDicMethod> selectalldicmethod();
	
    /**
          * 在精准报价页面查找出表中所有的品牌
	* @param List<ProductModel>
	* @return ProductInfo
	* @author 李永成
	*/
    public List<ProductDicMethod> selectAll();
    
    int insert(ProductDicMethod record);

	public List<ProductDicMethod> selectdicmethod(@Param("name")String name);

	public boolean deletebyid(int id);

	public boolean insertdicmethod(@Param("code")int code, @Param("name")String name);

	public boolean updatebyid(ProductDicMethod dicmethod); // 此处由于业务需求已经把update变成插入函数

	public ProductDicMethod selectByPrimaryKey(Long id);

	public ProductDicMethod selectdicmethodbyname(String name, int code);
}
