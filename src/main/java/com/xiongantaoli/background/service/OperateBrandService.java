package com.xiongantaoli.background.service;

import java.util.List;

import com.xiongantaoli.background.entity.ProductBrand;

	public interface OperateBrandService {
	
	List<ProductBrand> selectbrand();
	
	List<ProductBrand> selectbrand(String name);

	boolean insertbrand(int code, String name);

	boolean deletebyid(int id);

	boolean updatebyid(ProductBrand brand);

	ProductBrand selectByPrimaryKey(Long id);

	ProductBrand selectbrandbyname(String name,int code);
	
	
}
