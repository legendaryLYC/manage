package com.xiongantaoli.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.ProductApperance;
import com.xiongantaoli.background.mapper.ProductApperanceMapper;
import com.xiongantaoli.background.service.OperateApperanceService;


@Service("operateapperance")
public class OperateApperanceServiceImpl implements OperateApperanceService{

	@Autowired 
	ProductApperanceMapper sysapperancemapper;
	
	@Override
	public List<ProductApperance> selectapperance() {
		List<ProductApperance> apperancelist=null;
		try {
			apperancelist=sysapperancemapper.selectallapperance();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return apperancelist;
	}

	@Override
	public List<ProductApperance> selectapperance(String name) {
		List<ProductApperance> apperancelist=null;
		try {
			apperancelist=sysapperancemapper.selectapperance(name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return apperancelist;
	}

	@Override
	public boolean deletebyid(int id) {
		boolean flag=false;
		try {
		flag=sysapperancemapper.deletebyid(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertapperance(int code, String name) {
		boolean flag=false;
		try {
		flag=sysapperancemapper.insertapperance(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updatebyid(ProductApperance apperance) {
		boolean flag=false;
		try {
		flag=sysapperancemapper.updatebyid(apperance);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ProductApperance selectByPrimaryKey(Long id) {
		ProductApperance apperance=sysapperancemapper.selectByPrimaryKey(id);
		return apperance;
	}

	@Override
	public ProductApperance selectapperancebyname(String name,int code) {
		ProductApperance apperance=sysapperancemapper.selectapperancebyname(name,code);
		return apperance;
	}
}
