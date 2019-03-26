package com.xiongantaoli.background.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiongantaoli.background.entity.Materiel;
import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.entity.ProductRepair;
import com.xiongantaoli.background.entity.RepairManagement;
import com.xiongantaoli.background.form.OrderInfoForm;
import com.xiongantaoli.background.form.RepairManagementUtil;
import com.xiongantaoli.background.form.RepairShow;
import com.xiongantaoli.background.form.OperatorException;
import com.xiongantaoli.background.model.ResultData;
import com.xiongantaoli.background.security.controller.ResourceController;
import com.xiongantaoli.background.service.GetDictionaryListService;
import com.xiongantaoli.background.service.PurchaseService;
import com.xiongantaoli.background.service.RepairManagementService;

/**
 * 物料资源管理控制类
 * 
 * @author 卜勇峰
 *
 */

@Controller
@RequestMapping("/repair")
public class RepairManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private PurchaseService purchaseServices; //调用purchaseService管理接口（具体内容注释在接口里）
	@Autowired
	private GetDictionaryListService getDictionaryList; //获取用到的字典
	
	@Autowired
	private RepairManagementService repairManageService; //调用RepairManagementService 管理接口（具体内容注释在接口里）
	@Autowired
	private RepairManagementUtil repairManagementUtil; //RepairManagementController工具类
	
	/**
	 * 查询订单信息
	 * @Param
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public String list(Model model,Order order,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
			) {
			/*
			 * 打印入参
			 */
			logger.info("============ 进入查询维修订单，order : " + order == null ? null:order.toString() + " ==============");

			//搜索订单待维修状态对应的编号
			int code = repairManagementUtil.getCodeByName("待维修");			
			//分页
			PageHelper.startPage(pageNo, pageSize);
			List<OrderInfoForm> result = purchaseServices.selectOrdersSelective(order,code);
			PageInfo<OrderInfoForm> pageInfo = new PageInfo<OrderInfoForm>(result);
			if (pageNo > pageInfo.getPages()) {
				pageNo = pageInfo.getPages();
				PageHelper.startPage(pageNo, pageSize);
				result = purchaseServices.selectOrdersSelective(order,code);
				pageInfo = new PageInfo<OrderInfoForm>(result);
			}
			
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("ProductStates", getDictionaryList.getProductStates());
			return "repair/list";
		}
	
	/**
	 * 批量进入良品库
	 * @Param ids，id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/inAll")
	public ResultData inAll(@RequestBody Long[] ids,
			@RequestParam(value = "repairStyle", required = false, defaultValue = "-1") String repairStyle
			) {
		/*
		 * 打印入参
		 */
		logger.info("====== 进入inAll方法，参数名ids : "+ids.toString()+" ===========");
		
		//准备参数
		int key=0,finalKey=1;
		
		
		
		//更新订单状态并insert维修记录
		for(int i=0;i<ids.length;i++) {
			//进入良品库
			key = repairManagementUtil.inGoodStore(ids[i], repairStyle,-1);
			if(key == 0) {
				finalKey = 0;
			}	
		}	
		
		ResultData result = ResultData.setCodeAndMessage(finalKey, "success", "存在该名字！");
		return result;
	}
	
	/**
	 * 单个进入良品库
	 * @Param ids，id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/inStore")
	public ResultData inStore(@RequestParam(value = "id") Long id,
			@RequestParam(value = "repairStyle", required = false, defaultValue = "100") String repairStyle
			) {
		/*
		 * 打印入参
		 */
		logger.info("====== 进入inStore方法，参数名id : "+ id +",repairStyle: " + repairStyle + "===========");
		
		//进入良品库
		int key = repairManagementUtil.inGoodStore(id, repairStyle,1);
		
		ResultData result = ResultData.setCodeAndMessage(key, "success", "存在该名字！");
		return result;
	}
	
	/**
	 * 进入维修材料显示页面
	 * @param model
	 * @return 
	 */
	@RequestMapping(value = "/addShow")
	public String addShow(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "id", required = false, defaultValue = "-1") Long id) {
		logger.info("====== 进入addShow方法，订单purchaseId : " + id + " ===========");
		//获取维修类别的字典
		List<ProductRepair> productRepairs = getDictionaryList.getProductRepair();
		logger.info("====== 维修类别字典 : " + productRepairs.toString() + " ===========");
		//加载下拉菜单的字典List和订单id
		model.addAttribute("productRepairs", productRepairs);
		//加载订单id
		model.addAttribute("id", id);
		
		//显示该订单已填物料获取
		PageHelper.startPage(pageNo, pageSize);
		List<RepairShow> list = repairManageService.selectPart(id);
		
		try {	
			PageInfo<RepairShow> pageInfo = new PageInfo<>(list);
			if (pageNo > pageInfo.getPages()) {
				pageNo = pageInfo.getPages();
				PageHelper.startPage(pageNo, pageSize);
				list = repairManageService.selectPart(id);
				pageInfo = new PageInfo<>(list);
			}
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "repair/addShow";
	}
	
	/**
	 * 进入维修材料添加页面
	 * @param model
	 * @return 
	 */
	@RequestMapping(value = "/addSignal")
	public String addShow(Model model,
			@RequestParam(value = "id", required = false, defaultValue = "-1") Long id) {
		/*
		 * 打印入参
		 */
		logger.info("====== 进入addSignal方法，订单purchaseId : " + id + " ===========");
		
		//获取物料选择的字典
		List<Materiel> materiels = getDictionaryList.getMateriels();
		logger.info("====== 物料选择字典 : " + materiels.toString() + " ===========");
		
		//加载下拉菜单的字典List
		model.addAttribute("materiels", materiels);
		//加载该订单id
		model.addAttribute("id", id);
		return "repair/add";
	}
	
	/**
	 * 添加维修材料
	 * @param model
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value="/add",produces = "application/json;charset=utf-8",method = {RequestMethod.GET})
	public ResultData add(Model model,
			RepairManagement repairM) {
		/*
		 * 打印入参
		 */
		logger.info(" ===========进入add方法，repairM : " + repairM.toString() + "==============");
		
		int key = 0;
		ResultData result = null;
		
		try {	
			//判断该物料是否已被录入
			repairManagementUtil.judgeHaveMateriel(repairM,result);
			//判断是否有库存，有就更新
			repairManagementUtil.updateMaterielNum(repairM,result);
			//插入该数据
			Long userId = repairManagementUtil.getUserId();
			repairM.setUserId(userId);
			key = repairManageService.insert(repairM);
			result = ResultData.setCodeAndMessage(key, "添加成功", "添加失败");
		} catch (OperatorException e) {
			// TODO Auto-generated catch block
			logger.info("====== 添加维修物料方法时因为名字或库存的原因报错 : " + e.getMessage() + " ============== ");
			return e.getResultData();
		}
		return result;
	}
	
	/**
	 * 编辑维修材料
	 * @param model
	 * @return 
	 */
	@ResponseBody
	@RequestMapping("/edit")
	public ResultData edit(Model model,
							RepairManagement repairM) {
		/*
		 * 打印入参
		 */
		logger.info(" ===========进入edit方法，repairM : " + repairM.toString() + "==============");
		
		int key = 0;
		ResultData result = null;
		
		try {	
			//判断是否有库存，有就更新
			repairManagementUtil.updateMaterielNum(repairM,result);
			//插入该数据
			Long userId = repairManagementUtil.getUserId();
			repairM.setUserId(userId);
			key = repairManageService.updateByPrimaryKey(repairM);
			result = ResultData.setCodeAndMessage(key, "更新成功", "更新失败");
		} catch (OperatorException e) {
			// TODO Auto-generated catch block
			logger.info("====== 编辑维修物料方法报错 : " + e.getMessage() + " ============== ");
			return e.getResultData();
		}
		return result;	
	}
	
	/**
	 * 删除维修材料
	 * @param model
	 * @return 
	 */
	@ResponseBody
	@RequestMapping("/del")
	public ResultData delete(Model model,
			@RequestParam(value = "id")Long id) {
		//判断更新是否失败
		int key = 0;
		logger.info(" ===========进入del方法，id : " + id + "==============");
		
		//delete
		key = repairManagementUtil.delete(id);
		
		ResultData result;
		result = ResultData.setCodeAndMessage(key, "删除成功", "删除失败");
		return result;
	}
	
	/**
	 * 批量删除维修材料
	 * @param model
	 * @return 
	 */
	@ResponseBody
	@RequestMapping("/delAll")
	public ResultData delAll(Model model,
			@RequestBody Long[] ids) {
		//判断更新是否失败
		int key = 0,finalKey = 1;
		logger.info(" ===========进入delAll方法，id : " + ids.toString() + "==============");
		
		try {
			for(int i = 0 ; i < ids.length ; i++) {
				//delete
				key = repairManagementUtil.delete(ids[i]);
				if(key == 0) {
					finalKey = 0;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		ResultData result = ResultData.setCodeAndMessage(finalKey, "删除成功", "部分删除失败");
		return result;	
	}
	
	/**
	 * 维修材料编辑回显
	 * @param model
	 * @return 
	 */
	@RequestMapping(value="/editShow",produces = "application/json;charset=utf-8",method = {RequestMethod.GET})
	public String editShow(Model model,
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		/*
		 * 打印入参
		 */
		logger.info("进入RepairManagementController下的editShow方法，参数 id :" + id);
		
		//搜索当前编号的物料信息
		RepairManagement repairM = repairManageService.selectByPrimaryKey(id);
		//获取物料选择的字典
		List<Materiel> materiels = getDictionaryList.getMateriels();

		logger.info("====== 物料选择字典 : " + materiels.toString() + " ===========");
		
		//加载下拉菜单的字典List
		model.addAttribute("materiels", materiels);
		//加载当前编号物料信息
		model.addAttribute("repairM",repairM);
		return "repair/edit";	
	}
}
