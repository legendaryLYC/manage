package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.xiongantaoli.background.entity.ProductGuarantee;


public interface ProductGuaranteeMapper {
	public List<ProductGuarantee> selectallguarantee();
	
    /**
          * 在精准报价页面查找出表中所有的品牌
	* @param List<ProductModel>
	* @return ProductInfo
	* @author 周天
	*/
    public List<ProductGuarantee> selectAll();
    
    int insert(ProductGuarantee record);

	public List<ProductGuarantee> selectguarantee(@Param("name")String name);

	public boolean deletebyid(int id);

	public boolean insertguarantee(@Param("code")int code, @Param("name")String name);

	public boolean updatebyid(ProductGuarantee guarantee); // 此处由于业务需求已经把update变成插入函数

	public ProductGuarantee selectByPrimaryKey(Long id);

	public ProductGuarantee selectguaranteebyname(String name, int code);
}
