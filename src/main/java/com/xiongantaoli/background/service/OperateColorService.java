package com.xiongantaoli.background.service;

import java.util.List;

import com.xiongantaoli.background.entity.ProductColor;


public interface OperateColorService {
	
	public List<ProductColor> selectcolor();
	
	public List<ProductColor> selectcolor(String name);

	public boolean insertcolor(int code, String name);

	public boolean deletebyid(int id);

	public boolean updatebyid(ProductColor color);

	public ProductColor selectByPrimaryKey(Long id);

	public ProductColor selectcolorbyname(String name,int code);
	
	
}
