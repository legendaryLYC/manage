package com.xiongantaoli.background.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.xiongantaoli.background.entity.ProductGuarantee;
import com.xiongantaoli.background.service.OperateGuaranteeService;

/**
 * 保修期维护类
 * 
 * @author 李永成
 *
 */

@Controller
@RequestMapping("/guarantee") 	
public class GuaranteeMaintenanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(GuaranteeMaintenanceController.class);
	
	@Autowired
	private OperateGuaranteeService operateguarantee; //操作保修期的Service类
	
	//添加保修期的信息
	@RequestMapping(value = "/addguarantee")
	public String addRoleBefore(Model model) {
		return "maintenance-operate/add-guarantee";
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertguarantee")
	public String insertguarantee(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "name", required = false, defaultValue="") String name){
		int code1 = Integer.parseInt(code);
		try {
			ProductGuarantee guarantee=operateguarantee.selectguaranteebyname(name,code1);
			if(guarantee!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag=operateguarantee.insertguarantee(code1,name);
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
	
	//通过保修期名查询保修期
	@RequestMapping("/selectguarantee")
	public String selectguarantee(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue="") String name) {
		List<ProductGuarantee> guaranteelist=null;
		if (name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
			name=name.trim();
			guaranteelist = operateguarantee.selectguarantee(name);
		}
		else {
			guaranteelist = operateguarantee.selectguarantee();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<ProductGuarantee> pageInfo = new PageInfo<ProductGuarantee>(guaranteelist);
		model.addAttribute("pageInfo", pageInfo);
		return "maintenance-operate/show-guarantee";
	}
	
	//根据保修期名删除字典中对应的保修期
	@RequestMapping("/deleteguarantee/{id}")
	@ResponseBody
	public String deleteguarantee(@ModelAttribute ProductGuarantee guarantee){
		logger.debug("调用guarantee/deleteguarantee");
		String result="0";
		boolean flag=false;
		try {
			
			flag=operateguarantee.deletebyid(guarantee.getId().intValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(flag=true) {
			result="1";
		}
		return result;
	}
	
	//删除批量选中的保修期
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				operateguarantee.deletebyid(Integer.parseInt(String.valueOf(id)));
			}
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	//根据保修期名更改保修期的名称和编号信息
	@RequestMapping(value = "/updatebyid/{id}")
	public String updatebyidBefore(Model model, @ModelAttribute(value = "guarantee") ProductGuarantee guarantee,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		guarantee = operateguarantee.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("guarantee", guarantee);
		return "maintenance-operate/editor-guarantee";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updatebyid(Model model, @ModelAttribute(value = "guarantee") ProductGuarantee guarantee,HttpServletRequest request) {
		logger.debug("调用guarantee/updatebyid");
		int id=guarantee.getId().intValue();
		ProductGuarantee result=new ProductGuarantee();
		try {
			operateguarantee.deletebyid(id);
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(guarantee.getId());
			result.setName(request.getParameter("name1"));
			ProductGuarantee guarantee2=operateguarantee.selectguaranteebyname(guarantee.getName(),guarantee.getCode());
			if(guarantee2==null) {
				operateguarantee.updatebyid(guarantee);
				return "1";
			}
			else {
				operateguarantee.updatebyid(result); //此处由于业务需求执行的是insert操作
				return "2";
			}
		}
		catch (Exception e) {
			operateguarantee.updatebyid(result);
			return "2";
		}
	}
}
