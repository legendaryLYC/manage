package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.ProductModel;
 
public interface ProductModelMapper {

	public List<ProductModel> selectmodel(String name);

	public boolean deletebyname();

	public boolean insertmodel(@Param("code")int code, @Param("name")String name);

	public boolean updatebyid(@Param("id")int id, @Param("code")int code,@Param("name") String name);

    public int updateByPrimaryKey(ProductModel record);

    /**
           * 在精准报价页面查找出表中所有的型号
    * @param List<ProductModel>
    * @return ProductInfo
    * @author 周天
    */
	public List<ProductModel> selectAll();

	public ProductModel selectmodelbyname(String name, int code);

	public boolean deletebyid(int id);

	public ProductModel selectByPrimaryKey(Long id);

	public boolean updatebyid(ProductModel model2);

	public List<ProductModel> selectallmodel();
}
