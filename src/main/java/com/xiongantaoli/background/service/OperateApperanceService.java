package com.xiongantaoli.background.service;

import java.util.List;

import com.xiongantaoli.background.entity.ProductApperance;


public interface OperateApperanceService {
	
	public List<ProductApperance> selectapperance();
	
	public List<ProductApperance> selectapperance(String name);

	public boolean insertapperance(int code, String name);

	public boolean deletebyid(int id);

	public boolean updatebyid(ProductApperance apperance);

	public ProductApperance selectByPrimaryKey(Long id);

	public ProductApperance selectapperancebyname(String name,int code);
	
	
}
