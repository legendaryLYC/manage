package com.xiongantaoli.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiongantaoli.background.entity.ProductModel;
import com.xiongantaoli.background.mapper.ProductModelMapper;
import com.xiongantaoli.background.service.OperateModelService;

@Service("operatemodel")
public class OperateModelServiceImpl implements OperateModelService{

	@Autowired 
	ProductModelMapper sysmodelmapper;
	
	@Override
	public List<ProductModel> selectmodel() {
		List<ProductModel> modellist=null;
		try {
			modellist=sysmodelmapper.selectallmodel();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return modellist;
	}

	@Override
	public List<ProductModel> selectmodel(String name) {
		List<ProductModel> modellist=null;
		try {
			modellist=sysmodelmapper.selectmodel(name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return modellist;
	}

	@Override
	public boolean deletebyname(String name) {
		boolean flag=false;
		try {
		flag=sysmodelmapper.deletebyname();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertmodel(int code, String name) {
		boolean flag=false;
		try {
		flag=sysmodelmapper.insertmodel(code, name);
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
		flag=sysmodelmapper.updatebyid(id,code,name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}

	@Override
	public ProductModel selectmodelbyname(String name,int code){
		ProductModel model = null;
		model= sysmodelmapper.selectmodelbyname(name,code);
		return model;
	}

	@Override
	public boolean deletebyid(int id) {
		boolean flag=false;
		try {
		flag=sysmodelmapper.deletebyid(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ProductModel selectByPrimaryKey(Long id) {
		ProductModel model = null;
		try {
			model= sysmodelmapper.selectByPrimaryKey(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public boolean updatebyid(ProductModel model2) {
		boolean flag=false;
		try {
		flag=sysmodelmapper.updatebyid(model2);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


}
