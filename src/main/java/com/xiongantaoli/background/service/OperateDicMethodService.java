package com.xiongantaoli.background.service;

import java.util.List;

import com.xiongantaoli.background.entity.ProductDicMethod;

public interface OperateDicMethodService {
	
	public List<ProductDicMethod> selectdicmethod();
	
	public List<ProductDicMethod> selectdicmethod(String name);

	public boolean insertdicmethod(int code, String name);

	public boolean deletebyid(int id);

	public boolean updatebyid(ProductDicMethod dicmethod);

	public ProductDicMethod selectByPrimaryKey(Long id);

	public ProductDicMethod selectdicmethodbyname(String name,int code);
	
	
}
