package com.xiongantaoli.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.ProductStandard;
import com.xiongantaoli.background.mapper.ProductStandardMapper;
import com.xiongantaoli.background.service.OperateStandardService;

@Service("operatestandard")
public class OperateStandardServiceImpl implements OperateStandardService{

	@Autowired 
	ProductStandardMapper sysstandardmapper;
	
	@Override
	public List<ProductStandard> selectstandard() {
		List<ProductStandard> standardlist=null;
		try {
			standardlist=sysstandardmapper.selectallstandard();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return standardlist;
	}

	@Override
	public List<ProductStandard> selectstandard(String name) {
		List<ProductStandard> standardlist=null;
		try {
			standardlist=sysstandardmapper.selectstandard(name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return standardlist;
	}

	@Override
	public boolean deletebyname(String name) {
		boolean flag=false;
		try {
		flag=sysstandardmapper.deletebyname();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertstandard(int code, String name) {
		boolean flag=false;
		try {
		flag=sysstandardmapper.insertstandard(code, name); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updatebyid(int id, int code, String name) {
		boolean flag=false;
		try {
		flag=sysstandardmapper.updatebyid(id,code,name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	@Override
	public ProductStandard selectstandardbyname(String name,int code) {
		ProductStandard standard = null;
		try {
			standard= sysstandardmapper.selectstandardbyname(name,code);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return standard;
	}

	@Override
	public boolean deletebyid(int id) {
		boolean flag=false;
		try {
		flag=sysstandardmapper.deletebyid(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ProductStandard selectByPrimaryKey(Long id) {
		ProductStandard standard = null;
		try {
			standard= sysstandardmapper.selectByPrimaryKey(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return standard;
	}

	@Override
	public boolean updatebyid(ProductStandard standard2) {
		boolean flag=false;
		try {
		flag=sysstandardmapper.updatebyid(standard2);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


}
