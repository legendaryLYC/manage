package com.xiongantaoli.background.service;

import java.util.List;
import com.xiongantaoli.background.entity.ProductStandard;

public interface OperateStandardService {
	
	public List<ProductStandard> selectstandard();
	
	public List<ProductStandard> selectstandard(String name);

	public boolean insertstandard(int code, String name);

	public boolean deletebyname(String name);

	public boolean updatebyid(int id, int code, String name);

	public ProductStandard selectstandardbyname(String name, int code);

	public boolean deletebyid(int intValue);

	public ProductStandard selectByPrimaryKey(Long id);

	public boolean updatebyid(ProductStandard standard);
	
	
}
