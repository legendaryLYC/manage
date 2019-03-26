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
import com.xiongantaoli.background.entity.ProductModel;
import com.xiongantaoli.background.entity.SysUser;
import com.xiongantaoli.background.security.controller.IndexController;
import com.xiongantaoli.background.service.OperateModelService;

/**
 * 型号维护类
 * 
 * @author 李永成
 *
 */

@Controller
@RequestMapping("/model") 	
public class ModelMaintenanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(ModelMaintenanceController.class);
	
	@Autowired
	private OperateModelService operatemodel; //操作型号的Service类
	
	//添加型号的信息
	@RequestMapping(value = "/addmodel")
	public String addRoleBefore(Model model) {
		return "maintenance-operate/add-model";
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertmodel")
	public String insertmodel(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "name", required = false, defaultValue="") String name){
		int code1 = Integer.parseInt(code);
		try {
			ProductModel model1=operatemodel.selectmodelbyname(name,code1);
			if(model1!=null) {
				return "2"; // 代表数据库中已有该型号
			}
			else {
				boolean flag=operatemodel.insertmodel(code1,name);
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
	
	//通过型号名查询型号
	@RequestMapping("/selectmodel")
	public String selectmodel(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue="") String name) {
		List<ProductModel> modellist=null;
		if (name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
			name=name.trim();
			modellist = operatemodel.selectmodel(name);
		}
		else {
			modellist = operatemodel.selectmodel();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<ProductModel> pageInfo = new PageInfo<ProductModel>(modellist);
		model.addAttribute("pageInfo", pageInfo);
		return "maintenance-operate/show-model";
	}
	
	//根据型号名删除字典中对应的型号
	@RequestMapping("/deletemodel/{id}")
	@ResponseBody
	public String deletemodel(@ModelAttribute ProductModel model){
		logger.debug("调用model/deletemodel");
		String result="0";
		boolean flag=false;
		try {
			
			flag=operatemodel.deletebyid(model.getId().intValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(flag=true) {
			result="1";
		}
		return result;
	}
	
	//删除批量选中的型号
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				operatemodel.deletebyid(Integer.parseInt(String.valueOf(id)));
			}
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	//根据型号名更改型号的名称和编号信息
	@RequestMapping(value = "/updatebyid/{id}")
	public String updatebyidBefore(Model model, @ModelAttribute(value = "model1") ProductModel model1,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		model1 = operatemodel.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("model1", model1);
		return "maintenance-operate/editor-model";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updatebyid(@ModelAttribute(value = "model1") ProductModel model1,HttpServletRequest request) {
		logger.debug("调用model/updatebyid");
		int id=model1.getId().intValue();
		ProductModel result=new ProductModel();
		operatemodel.deletebyid(id);
		result.setCode(Integer.parseInt(request.getParameter("code1")));
		result.setId(model1.getId());
		result.setName(request.getParameter("name1"));
		try {
			ProductModel model2=operatemodel.selectmodelbyname(model1.getName(),model1.getCode());
			if(model2==null) {
				operatemodel.updatebyid(model1);
				System.err.println("更新成功");
				return "1";
			}
			else {
				operatemodel.updatebyid(result);
				System.err.println("更新失败");
				return "2";
			}
		}
		catch (Exception e) {
			System.err.println("!!!!!!!!!!!!!!!!出现异常");
			operatemodel.updatebyid(result);
			return "2";
		}
	}
}
