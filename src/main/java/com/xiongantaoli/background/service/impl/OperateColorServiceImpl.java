package com.xiongantaoli.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.ProductColor;
import com.xiongantaoli.background.mapper.ProductColorMapper;
import com.xiongantaoli.background.service.OperateColorService;


@Service("operatecolor")
public class OperateColorServiceImpl implements OperateColorService{

	@Autowired 
	ProductColorMapper syscolormapper;
	
	@Override
	public List<ProductColor> selectcolor() {
		List<ProductColor> colorlist=null;
		try {
			colorlist=syscolormapper.selectallcolor();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return colorlist;
	}

	@Override
	public List<ProductColor> selectcolor(String name) {
		List<ProductColor> colorlist=null;
		try {
			colorlist=syscolormapper.selectcolor(name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return colorlist;
	}

	@Override
	public boolean deletebyid(int id) {
		boolean flag=false;
		try {
		flag=syscolormapper.deletebyid(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertcolor(int code, String name) {
		boolean flag=false;
		try {
		flag=syscolormapper.insertcolor(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updatebyid(ProductColor color) {
		boolean flag=false;
		try {
		flag=syscolormapper.updatebyid(color);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ProductColor selectByPrimaryKey(Long id) {
		ProductColor color=syscolormapper.selectByPrimaryKey(id);
		return color;
	}

	@Override
	public ProductColor selectcolorbyname(String name,int code) {
		ProductColor color=syscolormapper.selectcolorbyname(name,code);
		return color;
	}
}
