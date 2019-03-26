package com.xiongantaoli.background.service;

import java.util.List;

import com.xiongantaoli.background.entity.ProductGuarantee;


public interface OperateGuaranteeService {
	
	public List<ProductGuarantee> selectguarantee();
	
	public List<ProductGuarantee> selectguarantee(String name);

	public boolean insertguarantee(int code, String name);

	public boolean deletebyid(int id);

	public boolean updatebyid(ProductGuarantee guarantee);

	public ProductGuarantee selectByPrimaryKey(Long id);

	public ProductGuarantee selectguaranteebyname(String name,int code);
	
	
}
