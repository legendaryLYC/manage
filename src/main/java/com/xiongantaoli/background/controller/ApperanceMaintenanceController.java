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
import com.xiongantaoli.background.entity.ProductApperance;
import com.xiongantaoli.background.service.OperateApperanceService;

/**
 * 外观维护类
 * 
 * @author 李永成
 *
 */

@Controller
@RequestMapping("/apperance") 	
public class ApperanceMaintenanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApperanceMaintenanceController.class);
	
	@Autowired
	private OperateApperanceService operateapperance; //操作外观的Service类
	
	//添加外观的信息
	@RequestMapping(value = "/addapperance")
	public String addRoleBefore(Model model) {
		return "maintenance-operate/add-apperance";
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertapperance")
	public String insertapperance(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "name", required = false, defaultValue="") String name){
		int code1 = Integer.parseInt(code);
		try {
			ProductApperance apperance=operateapperance.selectapperancebyname(name,code1);
			if(apperance!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag=operateapperance.insertapperance(code1,name);
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
	
	//通过外观名查询外观
	@RequestMapping("/selectapperance")
	public String selectapperance(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue="") String name) {
		List<ProductApperance> apperancelist=null;
		if (name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
			name=name.trim();
			apperancelist = operateapperance.selectapperance(name);
		}
		else {
			apperancelist = operateapperance.selectapperance();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<ProductApperance> pageInfo = new PageInfo<ProductApperance>(apperancelist);
		model.addAttribute("pageInfo", pageInfo);
		return "maintenance-operate/show-apperance";
	}
	
	//根据外观名删除字典中对应的外观
	@RequestMapping("/deleteapperance/{id}")
	@ResponseBody
	public String deleteapperance(@ModelAttribute ProductApperance apperance){
		logger.debug("调用apperance/deleteapperance");
		String result="0";
		boolean flag=false;
		try {
			
			flag=operateapperance.deletebyid(apperance.getId().intValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(flag=true) {
			result="1";
		}
		return result;
	}
	
	//删除批量选中的外观
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				operateapperance.deletebyid(Integer.parseInt(String.valueOf(id)));
			}
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	//根据外观名更改外观的名称和编号信息
	@RequestMapping(value = "/updatebyid/{id}")
	public String updatebyidBefore(Model model, @ModelAttribute(value = "apperance") ProductApperance apperance,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		apperance = operateapperance.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("apperance", apperance);
		return "maintenance-operate/editor-apperance";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updatebyid(Model model, @ModelAttribute(value = "apperance") ProductApperance apperance,HttpServletRequest request) {
		logger.debug("调用apperance/updatebyid");
		int id=apperance.getId().intValue();
		ProductApperance result=new ProductApperance();
		try {
			operateapperance.deletebyid(id);
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(apperance.getId());
			result.setName(request.getParameter("name1"));
			ProductApperance apperance2=operateapperance.selectapperancebyname(apperance.getName(),apperance.getCode());
			if(apperance2==null) {
				operateapperance.updatebyid(apperance);
				return "1";
			}
			else {
				operateapperance.updatebyid(result); //此处由于业务需求执行的是insert操作
				return "2";
			}
		}
		catch (Exception e) {
			operateapperance.updatebyid(result);
			return "2";
		}
	}
}
