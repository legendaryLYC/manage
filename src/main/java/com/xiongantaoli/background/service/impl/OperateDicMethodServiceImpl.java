package com.xiongantaoli.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.ProductDicMethod;
import com.xiongantaoli.background.mapper.ProductDicMethodMapper;
import com.xiongantaoli.background.service.OperateDicMethodService;

@Service("operatedicmethod")
public class OperateDicMethodServiceImpl implements OperateDicMethodService{

	@Autowired 
	ProductDicMethodMapper sysdicmethodmapper;
	
	@Override
	public List<ProductDicMethod> selectdicmethod() {
		List<ProductDicMethod> dicmethodlist=null;
		try {
			dicmethodlist=sysdicmethodmapper.selectalldicmethod();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return dicmethodlist;
	}

	@Override
	public List<ProductDicMethod> selectdicmethod(String name) {
		List<ProductDicMethod> dicmethodlist=null;
		try {
			dicmethodlist=sysdicmethodmapper.selectdicmethod(name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return dicmethodlist;
	}

	@Override
	public boolean deletebyid(int id) {
		boolean flag=false;
		try {
		flag=sysdicmethodmapper.deletebyid(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertdicmethod(int code, String name) {
		boolean flag=false;
		try {
		flag=sysdicmethodmapper.insertdicmethod(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updatebyid(ProductDicMethod dicmethod) {
		boolean flag=false;
		try {
		flag=sysdicmethodmapper.updatebyid(dicmethod);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ProductDicMethod selectByPrimaryKey(Long id) {
		ProductDicMethod dicmethod=sysdicmethodmapper.selectByPrimaryKey(id);
		return dicmethod;
	}

	@Override
	public ProductDicMethod selectdicmethodbyname(String name,int code) {
		ProductDicMethod dicmethod=sysdicmethodmapper.selectdicmethodbyname(name,code);
		return dicmethod;
	}
}
