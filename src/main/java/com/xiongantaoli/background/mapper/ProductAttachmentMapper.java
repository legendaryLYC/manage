package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.ProductAttachment;


public interface ProductAttachmentMapper {
	public List<ProductAttachment> selectallattachment();
	
    /**
    * 在精准报价页面查找出表中所有的品牌
	* @param List<ProductModel>
	* @return ProductInfo
	* @author 李永成
	*/
    public List<ProductAttachment> selectAll();
    
    int insert(ProductAttachment record);

	public List<ProductAttachment> selectattachment(@Param("name")String name);

	public boolean deletebyid(int id);

	public boolean insertattachment(@Param("code")int code, @Param("name")String name);

	public boolean updatebyid(ProductAttachment attachment); // 此处由于业务需求已经把update变成插入函数

	public ProductAttachment selectByPrimaryKey(Long id);

	public ProductAttachment selectattachmentbyname(@Param("name")String name, @Param("code")int code);
}
