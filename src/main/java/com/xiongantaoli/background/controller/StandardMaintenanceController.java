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
import com.xiongantaoli.background.entity.ProductStandard;
import com.xiongantaoli.background.service.OperateStandardService;

/**
 * 规格维护类
 * 
 * @author 李永成
 *
 */

@Controller
@RequestMapping("/standard") 	
public class StandardMaintenanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(StandardMaintenanceController.class);
	
	@Autowired
	private OperateStandardService operatestandard; //操作规格的Service类
	
	//添加规格的信息
	@RequestMapping(value = "/addstandard")
	public String addRoleBefore(Model model) {
		return "maintenance-operate/add-standard";
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertstandard")
	public String insertstandard(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "name", required = false, defaultValue="") String name){
		int code1 = Integer.parseInt(code);
		try {
			ProductStandard standard=operatestandard.selectstandardbyname(name,code1);
			if(standard!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag=operatestandard.insertstandard(code1,name);
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
	
	//通过规格名查询规格
	@RequestMapping("/selectstandard")
	public String selectstandard(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue="") String name) {
		List<ProductStandard> standardlist=null;
		if (name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
			name=name.trim();
			standardlist = operatestandard.selectstandard(name);
		}
		else {
			standardlist = operatestandard.selectstandard();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<ProductStandard> pageInfo = new PageInfo<ProductStandard>(standardlist);
		model.addAttribute("pageInfo", pageInfo);
		return "maintenance-operate/show-standard";
	}
	
	//根据规格名删除字典中对应的规格
	@RequestMapping("/deletestandard/{id}")
	@ResponseBody
	public String deletestandard(@ModelAttribute ProductStandard standard){
		logger.debug("调用standard/deletestandard");
		String result="0";
		boolean flag=false;
		try {
			
			flag=operatestandard.deletebyid(standard.getId().intValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(flag=true) {
			result="1";
		}
		return result;
	}
	
	//删除批量选中的规格
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				operatestandard.deletebyid(Integer.parseInt(String.valueOf(id)));
			}
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	//根据规格名更改规格的名称和编号信息
	@RequestMapping(value = "/updatebyid/{id}")
	public String updatebyidBefore(Model model, @ModelAttribute(value = "standard") ProductStandard standard,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		standard = operatestandard.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("standard", standard);
		return "maintenance-operate/editor-standard";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updatebyid(Model model, @ModelAttribute(value = "standard") ProductStandard standard,HttpServletRequest request) {
		logger.debug("调用standard/updatebyid");
		int id=standard.getId().intValue();
		ProductStandard result=new ProductStandard();
		try {
			operatestandard.deletebyid(id);
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(standard.getId());
			result.setName(request.getParameter("name1"));
			ProductStandard standard2=operatestandard.selectstandardbyname(standard.getName(),standard.getCode());
			if(standard2==null) {
				operatestandard.updatebyid(standard);
				return "1";
			}
			else {
				operatestandard.updatebyid(result); //此处由于业务需求执行的是insert操作
				return "2";
			}
		}
		catch (Exception e) {
			operatestandard.updatebyid(result);
			return "2";
		}
	}
}
