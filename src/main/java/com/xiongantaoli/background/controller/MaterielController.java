package com.xiongantaoli.background.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiongantaoli.background.entity.Materiel;
import com.xiongantaoli.background.entity.SysResource;
import com.xiongantaoli.background.entity.SysRole;
import com.xiongantaoli.background.entity.SysUser;
import com.xiongantaoli.background.mapper.MaterielMapper;
import com.xiongantaoli.background.mapper.SysResourceMapper;
import com.xiongantaoli.background.model.ResultData;
import com.xiongantaoli.background.security.controller.ResourceController;
import com.xiongantaoli.background.service.MaterielService;

/**
 * 物料资源管理控制类
 * 
 * @author 卜勇峰
 *
 */

@Controller
@RequestMapping("/materiel")
public class MaterielController {


	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private MaterielController materielController; 
	
	@Autowired
	private MaterielService materielService; //调用materiel管理接口（具体内容注释在接口里）
	
	/**
	 * 查询物料信息
	 * @Param pageNo,pageSize,name
	 * @return
	 */
	@RequestMapping(value = "/find")
	public String find(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			HttpServletRequest request) {
		/*
		 * 打印入参日志
		 */
		logger.info("====== 进入find方法，调用者为xxx，" +  ",参数名pageNo : "+pageNo+"#pageSize : " + pageSize + "#name : " + name + "===========");
		
		//封装查询使用的materiel
		Materiel materiel=new Materiel();
		if (name != null && !"".equals(name.trim())) {
			materiel.setMaterielName(name.trim());
			model.addAttribute("name", name.trim());
		}
		name=name.trim();
		
		//分页，两者顺序不能调换
		PageHelper.startPage(pageNo, pageSize);
		List<Materiel> list = materielService.select(name);
		logger.info("============查询出materiels ： " + list.toString() + " ===========");
		
		try {	
			PageInfo<Materiel> pageInfo = new PageInfo<Materiel>(list);
			if (pageNo > pageInfo.getPages()) {
				pageNo = pageInfo.getPages();
				PageHelper.startPage(pageNo, pageSize);
				list = materielService.select(name);
				pageInfo = new PageInfo<Materiel>(list);
			}
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "materiels/showList";
	}
	
	/**
	 * 删除单一物料信息
	 * @Param model code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del")
	public ResultData delete(Model model, @RequestParam(value="code") Integer code
			) {
		logger.info("====== 进入delete方法，参数名code : "+code==null?null:code+" ===========");
		int key=0;
	 	key=materielService.deleteByCode(code);
	 	logger.info("========== 删除编号为"+code+"的物料结果 ： "+key+" ===============");
	 		
 		/*
		 * 打印出参日志
		 */
	 	ResultData result;
	 	result = ResultData.setCodeAndMessage(key, "success", "error");
	 	return result;
	}
	
	/**
	 * 批量删除物料信息
	 * @Param codes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delAll")
	public ResultData deleteAll(@RequestBody Integer[] codes
			) {
		/*
		 * 打印入参方法
		 */
		logger.info("====== 进入deleteAll方法，参数名codes : "+codes==null?null:codes.toString()+" ===========");
		int key = 0;
		
		ResultData result;
		try {
			for(int i = 0 ; i < codes.length ; i++) {
				  key = materielService.deleteByCode(codes[i]);
				  logger.info("========== 删除编号为"+codes[i]+"的物料结果  ："+key+" ===============");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = ResultData.setCodeAndMessage(0, "success", "error");
			return result;
		}	
		
	 	/*
		 * 打印出参日志
		 */	
	 	result = ResultData.setCodeAndMessage(key, "success", "error");
	 	return result;
	}
	
	/**
	 * 添加物料信息
	 * @Param model,materiel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add",produces = "application/json;charset=utf-8",method ={RequestMethod.GET})
	public ResultData add(Model model,Materiel materiel
			) {
		/*
		 * 打印入参方法
		 */
		logger.info("======进入add方法，参数名materiel : " + materiel.toString() + "===========");

		ResultData result;
		result = materielController.judgeExist(materiel.getMaterielName(), null);
		if(result.getCode() == "999999") {
			return result;
		}
		//设置code
		int count = materielService.selectLast()+1;
		materiel.setCode(count);
		//防止空格
		materiel.setMaterielName(materiel.getMaterielName().trim());
		materielService.insert(materiel);
	 	return result;

	}
	
	/**
	 * 更新物料信息
	 * @Param model,materiel,oldName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update",produces = "application/json;charset=utf-8",method ={RequestMethod.GET})
	public ResultData update(Model model,Materiel materiel,@RequestParam(value = "oldName") String oldName
			) {
		/*
		 * 打印入参方法
		 */
		logger.info("====== 进入update方法，materiel : "+ materiel.toString() +"===========");
		
		ResultData result;
		result = materielController.judgeExist(materiel.getMaterielName(), oldName);
		if(result.getCode() == "999999") {
			return result;
		}
		materiel.setMaterielName(materiel.getMaterielName().trim());
		materielService.updateByCode(materiel);
		return result;
		
	}
	
	/**
	 * 跳转到添加物料页面
	 * @Param model
	 * @return
	 */
	@RequestMapping(value="/addM")
	public String addMateriel(Model model) {
		return "materiels/add";
	}
	
	/**
	 * 跳转到修改物料页面
	 * @Param model,materielName
	 * @return
	 */
	@RequestMapping(value="/editM")
	public String editMateriel(Model model,
			@RequestParam(value = "code", required = false, defaultValue = "1") int code) {
		/*
		 * 打印入参
		 */
		logger.info("MaterielController下的editM方法，调用者xxx，code : " + code);
		List<Materiel> materiel = new ArrayList<>();
		
		//查询当前修改物料信息
		materiel.add(materielService.selectByCode(code));
		logger.info("======== 修改物料页面呈现信息 : " + materiel.toString() + " ==========");
		model.addAttribute("materiel", materiel);
		return "materiels/edit";
	}
	
	
	/**
	 * 如果是插入，判断是否存在该名字
	 * 如果是修改，判断是否存在，如果存在再判断和更改之前的名字相不相同
	 * @param newName,oldName
	 * @return
	 */
	public ResultData judgeNameExist(String newName,String oldName) {
		logger.info("进入MaterielController下的judgeNameExist方法，参数newName ： " + newName + " #oldName : " +oldName);
		List<Materiel> materielS = materielService.select(newName);
		int key = 0;
		ResultData result;
		if(materielS.size() == 0||materielS.get(0).getMaterielName().equals(oldName)) {
			key = 1;
		}
		/*
		 * 打印出参日志
		 */	
	 	result = ResultData.setCodeAndMessage(key, "success", "存在该名字！");
	 	return result;
	}
	public ResultData judgeExist(String newName,String oldName) {
		logger.info("进入MaterielController下的judgeNameExist方法，参数newName ： " + newName + " #oldName : " +oldName);
		List<Materiel> materielS = materielService.selectByName(newName);
		int key = 0;
		ResultData result;
		if(materielS.size() == 0||materielS.get(0).getMaterielName().equals(oldName)) {
			key = 1;
		}
		/*
		 * 打印出参日志
		 */	
	 	result = ResultData.setCodeAndMessage(key, "success", "存在该名字！");
	 	return result;
	}
	
	
}
