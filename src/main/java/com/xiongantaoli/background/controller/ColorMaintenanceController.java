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
import com.xiongantaoli.background.entity.ProductColor;
import com.xiongantaoli.background.service.OperateColorService;

/**
 * 品牌维护类
 * 
 * @author 李永成
 *
 */

@Controller
@RequestMapping("/color") 	
public class ColorMaintenanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(ColorMaintenanceController.class);
	
	@Autowired
	private OperateColorService operatecolor; //操作品牌的Service类
	
	//添加品牌的信息
	@RequestMapping(value = "/addcolor")
	public String addRoleBefore(Model model) {
		return "maintenance-operate/add-color";
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertcolor")
	public String insertcolor(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "name", required = false, defaultValue="") String name){
		int code1 = Integer.parseInt(code);
		try {
			ProductColor color=operatecolor.selectcolorbyname(name,code1);
			if(color!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag=operatecolor.insertcolor(code1,name);
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
	@RequestMapping("/selectcolor")
	public String selectcolor(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue="") String name) {
		List<ProductColor> colorlist=null;
		if (name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
			name=name.trim();
			colorlist = operatecolor.selectcolor(name);
		}
		else {
			colorlist = operatecolor.selectcolor();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<ProductColor> pageInfo = new PageInfo<ProductColor>(colorlist);
		model.addAttribute("pageInfo", pageInfo);
		return "maintenance-operate/show-color";
	}
	
	//根据品牌名删除字典中对应的品牌
	@RequestMapping("/deletecolor/{id}")
	@ResponseBody
	public String deletecolor(@ModelAttribute ProductColor color){
		logger.debug("调用color/deletecolor");
		String result="0";
		boolean flag=false;
		try {
			
			flag=operatecolor.deletebyid(color.getId().intValue());
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
				operatecolor.deletebyid(Integer.parseInt(String.valueOf(id)));
			}
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	//根据品牌名更改品牌的名称和编号信息
	@RequestMapping(value = "/updatebyid/{id}")
	public String updatebyidBefore(Model model, @ModelAttribute(value = "color") ProductColor color,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		color = operatecolor.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("color", color);
		return "maintenance-operate/editor-color";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updatebyid(Model model, @ModelAttribute(value = "color") ProductColor color,HttpServletRequest request) {
		logger.debug("调用color/updatebyid");
		int id=color.getId().intValue();
		ProductColor result=new ProductColor();
		try {
			operatecolor.deletebyid(id);
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(color.getId());
			result.setName(request.getParameter("name1"));
			ProductColor color2=operatecolor.selectcolorbyname(color.getName(),color.getCode());
			if(color2==null) {
				operatecolor.updatebyid(color);
				return "1";
			}
			else {
				operatecolor.updatebyid(result); //此处由于业务需求执行的是insert操作
				return "2";
			}
		}
		catch (Exception e) {
			operatecolor.updatebyid(result);
			return "2";
		}
	}
}
