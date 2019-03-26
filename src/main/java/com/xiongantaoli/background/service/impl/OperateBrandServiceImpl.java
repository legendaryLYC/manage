package com.xiongantaoli.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.ProductBrand;
import com.xiongantaoli.background.mapper.ProductBrandMapper;
import com.xiongantaoli.background.service.OperateBrandService;

@Service("operatebrand")
public class OperateBrandServiceImpl implements OperateBrandService{

	@Autowired 
	ProductBrandMapper sysbrandmapper;
	
	@Override
	public List<ProductBrand> selectbrand() {
		List<ProductBrand> brandlist=null;
		try {
			brandlist=sysbrandmapper.selectallbrand();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return brandlist;
	}

	@Override
	public List<ProductBrand> selectbrand(String name) {
		List<ProductBrand> brandlist=null;
		try {
			brandlist=sysbrandmapper.selectbrand(name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return brandlist;
	}

	@Override
	public boolean deletebyid(int id) {
		boolean flag=false;
		try {
		flag=sysbrandmapper.deletebyid(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertbrand(int code, String name) {
		boolean flag=false;
		try {
		flag=sysbrandmapper.insertbrand(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updatebyid(ProductBrand brand) {
		boolean flag=false;
		try {
		flag=sysbrandmapper.updatebyid(brand);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ProductBrand selectByPrimaryKey(Long id) {
		ProductBrand brand=sysbrandmapper.selectByPrimaryKey(id);
		return brand;
	}

	@Override
	public ProductBrand selectbrandbyname(String name,int code) {
		ProductBrand brand=sysbrandmapper.selectbrandbyname(name,code);
		return brand;
	}
}
