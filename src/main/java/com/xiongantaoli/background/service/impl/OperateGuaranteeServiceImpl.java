package com.xiongantaoli.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.ProductGuarantee;
import com.xiongantaoli.background.mapper.ProductGuaranteeMapper;
import com.xiongantaoli.background.service.OperateGuaranteeService;


@Service("operateguarantee")
public class OperateGuaranteeServiceImpl implements OperateGuaranteeService{

	@Autowired 
	ProductGuaranteeMapper sysguaranteemapper;
	
	@Override
	public List<ProductGuarantee> selectguarantee() {
		List<ProductGuarantee> guaranteelist=null;
		try {
			guaranteelist=sysguaranteemapper.selectallguarantee();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return guaranteelist;
	}

	@Override
	public List<ProductGuarantee> selectguarantee(String name) {
		List<ProductGuarantee> guaranteelist=null;
		try {
			guaranteelist=sysguaranteemapper.selectguarantee(name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return guaranteelist;
	}

	@Override
	public boolean deletebyid(int id) {
		boolean flag=false;
		try {
		flag=sysguaranteemapper.deletebyid(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertguarantee(int code, String name) {
		boolean flag=false;
		try {
		flag=sysguaranteemapper.insertguarantee(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updatebyid(ProductGuarantee guarantee) {
		boolean flag=false;
		try {
		flag=sysguaranteemapper.updatebyid(guarantee);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ProductGuarantee selectByPrimaryKey(Long id) {
		ProductGuarantee guarantee=sysguaranteemapper.selectByPrimaryKey(id);
		return guarantee;
	}

	@Override
	public ProductGuarantee selectguaranteebyname(String name,int code) {
		ProductGuarantee guarantee=sysguaranteemapper.selectguaranteebyname(name,code);
		return guarantee;
	}
}
