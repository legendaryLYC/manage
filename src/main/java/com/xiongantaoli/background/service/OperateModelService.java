package com.xiongantaoli.background.service;

import java.util.List;

import com.xiongantaoli.background.entity.ProductModel;

public interface OperateModelService {
	
	public List<ProductModel> selectmodel();
	
	public List<ProductModel> selectmodel(String name);

	public boolean insertmodel(int code, String name);

	public boolean deletebyname(String name);

	public boolean updatebyid(int id, int code, String name);

	public ProductModel selectmodelbyname(String name, int code);

	public boolean deletebyid(int id);

	public ProductModel selectByPrimaryKey(Long id);

	public boolean updatebyid(ProductModel model2);
	
	
}
