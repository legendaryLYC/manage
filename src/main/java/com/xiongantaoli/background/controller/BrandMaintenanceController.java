package com.xiongantaoli.background.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiongantaoli.background.entity.ProductBrand;
import com.xiongantaoli.background.service.OperateBrandService;

/**
 * 品牌维护类
 * 
 * @author 李永成
 *
 */

@Controller
@RequestMapping("/brand") 	
public class BrandMaintenanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(BrandMaintenanceController.class);
	
	@Autowired
	private OperateBrandService operatebrand; //操作品牌的Service类
	
	//添加品牌的信息
	@RequestMapping(value = "/addbrand")
	public String addRoleBefore(Model model) {
		return "maintenance-operate/add-brand";
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertbrand")
	public String insertbrand(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "name", required = false, defaultValue="") String name){
		int code1 = Integer.parseInt(code);
		try {
			ProductBrand brand=operatebrand.selectbrandbyname(name,code1);
			if(brand!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag=operatebrand.insertbrand(code1,name);
				if(flag==true) {
					return "1"; //插入成功
				}
			}
		}
			
		catch (Exception e) {
			return "0"; // 根据两个字段查出了两条数据。
		}
		return "0";
	}
	//通过品牌名查询品牌
	@RequestMapping("/selectbrand")
	public String selectbrand(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue="") String name) {
		List<ProductBrand> brandlist=null;
		if (name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
			name=name.trim();
			brandlist = operatebrand.selectbrand(name);
		}
		else {
			brandlist = operatebrand.selectbrand();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<ProductBrand> pageInfo = new PageInfo<ProductBrand>(brandlist);
		model.addAttribute("pageInfo", pageInfo);
		return "maintenance-operate/show-brand";
	}
	
	//根据品牌名删除字典中对应的品牌
	@RequestMapping("/deletebrand/{id}")
	@ResponseBody
	public String deletebrand(@ModelAttribute ProductBrand brand){
		logger.debug("调用brand/deletebrand");
		String result="0";
		boolean flag=false;
		try {
			
			flag=operatebrand.deletebyid(brand.getId().intValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(flag=true) {
			result="1";
		}
		return result;
	}
	
	//删除批量选中的品牌
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				operatebrand.deletebyid(Integer.parseInt(String.valueOf(id)));
			}
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	//根据品牌名更改品牌的名称和编号信息
	@RequestMapping(value = "/updatebyid/{id}")
	public String updatebyidBefore(Model model, @ModelAttribute(value = "brand") ProductBrand brand,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		brand = operatebrand.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("brand", brand);
		return "maintenance-operate/editor-brand";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updatebyid(Model model, @ModelAttribute(value = "brand") ProductBrand brand,HttpServletRequest request) {
		logger.debug("调用brand/updatebyid");
		int id=brand.getId().intValue();
		ProductBrand result=new ProductBrand();
		try {
			operatebrand.deletebyid(id);
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(brand.getId());
			result.setName(request.getParameter("name1"));
			ProductBrand brand2=operatebrand.selectbrandbyname(brand.getName(),brand.getCode());
			if(brand2==null) {
				operatebrand.updatebyid(brand); //brand代表更改过后的数据
				return "1";
			}
			else {
				operatebrand.updatebyid(result); //此处由于业务需求执行的是insert操作 result是改变之前的值
				return "2";
			}
		}
		catch (Exception e) {
			operatebrand.updatebyid(result);
			return "2";
		}
	}
}
