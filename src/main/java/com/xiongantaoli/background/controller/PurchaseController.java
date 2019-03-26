package com.xiongantaoli.background.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiongantaoli.background.entity.*;
import com.xiongantaoli.background.form.LongsAndIntegerDto;
import com.xiongantaoli.background.form.OrderInfoForm;
import com.xiongantaoli.background.form.ProductInfoForm;
import com.xiongantaoli.background.mapper.RefundListMapper;
import com.xiongantaoli.background.mapper.SysUserMapper;
import com.xiongantaoli.background.model.ResultData;
import com.xiongantaoli.background.service.GetDictionaryListService;
import com.xiongantaoli.background.service.PurchaseService;
import com.xiongantaoli.background.util.StringToDate;
import com.xiongantaoli.background.util.Utility;


/**
 * 采购记录相关功能控制类
 * @author 孟晓冬
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	@Autowired
	private PurchaseService purchaseServices;
	@Autowired
	private GetDictionaryListService getDictionaryList;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private RefundListMapper refundListMapper;
	
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);
	
	/**
	 * 进入采购记录列表页面,
	 * 包括筛选, 分页
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public String index(Model model,Order order,
		@RequestParam(value = "purchaseTimeString", required = false) String purchaseTimeString,
		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
		@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
		) {
		//日期字符转化为Date型并set到order对象
		java.util.Date date = StringToDate.toUtilDate(purchaseTimeString);
		order.setPurchaseTime(date);
		
		//分页
		PageHelper.startPage(pageNo, pageSize);
		List<OrderInfoForm> result = purchaseServices.getOrdersByStatesSelective(order, 1, 5, 9);
		PageInfo<OrderInfoForm> pageInfo = new PageInfo<OrderInfoForm>(result);
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			result = purchaseServices.getOrdersByStatesSelective(order, 1, 5, 9);
			pageInfo = new PageInfo<OrderInfoForm>(result);
		}
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("ProductStates", getDictionaryList.getProductStates());
		model.addAttribute("order", order);
		return "purchase/list";
	}
	
	/**
	 * 新增采购记录
	 */
	@ResponseBody
	@RequestMapping(value =  "/insertOrder", produces = "application/json;charset=utf-8",method = {RequestMethod.GET})
	public ResultData insertOrder(Order order,
			@RequestParam(value = "purchaseTimeString", required = false) String purchaseTimeString) {
		
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：purchase/check-in，传过来的参数为order=" + order + "##purchaseTimeString=" + purchaseTimeString);
		ResultData resultToPage = new ResultData();
		
		//String日期转换为Date
		Date date = StringToDate.toSqlDate(purchaseTimeString);
		order.setPurchaseTime(date);
		//最终采购时间设置为采购时间
		order.setFinalTime(date);
		//用户id存到对象的usrId
		String loginUserName = Utility.getCurrentUsername();
		SysUser loginUser = userMapper.selectByUsername(loginUserName);
		order.setUserId(loginUser.getId());
		
		//插入数据库
		Boolean result = purchaseServices.insertOrder(order);
		//返回结果
		if(result == true){
			resultToPage.setCode("000000");
			resultToPage.setMessage("插入成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("插入失败！");
		}
		return resultToPage;
	}
	
	/**
	 * 删除采购记录
	 */
	@ResponseBody
	@RequestMapping(value =  "/deleteOrder", produces = "application/json;charset=utf-8")
	public ResultData deleteOrder(@RequestParam(value = "id", required = false) Long id) {
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：purchase/list，传过来的参数为id=" + id );
		ResultData resultToPage = new ResultData();
		
		//删除该id的采购记录
		Boolean result = purchaseServices.deleteOrder(id);
		
		//返回结果
		if(result == true){
			resultToPage.setCode("000000");
			resultToPage.setMessage("删除成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("删除失败！");
		}
		return resultToPage;
	}
	
	/**
	 * 删除所有勾选采购记录
	 */
	@ResponseBody
	@RequestMapping(value =  "/deleteAllOrders", produces = "application/json;charset=utf-8")
	public ResultData deleteAllOrders(@RequestBody Long[] ids) {
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：purchase/list，传过来的参数为ids=" + ids );
		ResultData resultToPage = new ResultData();
		
		//记录删除结果
		Boolean result=false;
		for (long id : ids) {
			//删除该id的采购记录
			result = purchaseServices.deleteOrder(id);
		}
		//返回结果
		if(result == true){
			resultToPage.setCode("000000");
			resultToPage.setMessage("删除成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("删除失败！");
		}
		return resultToPage;
	}
	
	/**
	 * 修改采购记录
	 */
	@ResponseBody
	@RequestMapping(value =  "/updateOrder", produces = "application/json;charset=utf-8",method ={RequestMethod.GET})
	public ResultData updateOrder(Order order,
			@RequestParam(value = "isPurhase", required = false) Integer isPurhase,
			@RequestParam(value = "purchaseTimeString", required = false) String purchaseTimeString) {
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：purchase/update和qiality/update，传过来的参数为order=" + 
				order + "purchaseTimeString:" + purchaseTimeString);
		ResultData resultToPage = new ResultData();
		//日期转换为Date型
		Date date = StringToDate.toSqlDate(purchaseTimeString);
		order.setPurchaseTime(date);
		order.setFinalTime(date);
		
		Boolean result = false;
		//判断是否需要选择更新
		if(isPurhase != null && isPurhase ==1) {
			//用户id存到对象的usrId
			String loginUserName = Utility.getCurrentUsername();
			SysUser loginUser = userMapper.selectByUsername(loginUserName);
				order.setUserId(loginUser.getId());
			result = purchaseServices.updateOrderAll(order);
		}else{
			result = purchaseServices.updateOrder(order);
		}
		
		//返回结果
		if(result == true){
			resultToPage.setCode("000000");
			resultToPage.setMessage("修改成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("修改失败！");
		}
		return resultToPage;
	}

	/**
	 * 进入采购记录修改页面
	 */
	@RequestMapping(value = "/updateOrderPage")
	public String updateOrderPage(Model model, 
			@RequestParam(value = "id", required = false)Long id) {
		//查询该id号的采购记录项
		OrderInfoForm orderInfoForm = purchaseServices.selectOrderInfoFormByPrimaryKey(id);
		//获取下拉菜单的字典
		List<ProductDicMethod> dicMethods = getDictionaryList.getDicMethods();
		List<ProductBrand> productBrands = getDictionaryList.getProductBrands();
		List<ProductModel> productModels = getDictionaryList.getProductModels();
		List<ProductColor> productColors = getDictionaryList.getProductColors();
		List<ProductGuarantee> guarantees = getDictionaryList.getProductGuarantees();
		List<ProductApperance> productApperances = getDictionaryList.getProductApperances();
		List<ProductAttachment> productAttachments = getDictionaryList.getProductAttachments();
		List<ProductState> productStates = getDictionaryList.getProductStates();
		//加载下拉菜单的字典List
		model.addAttribute("dicMethods", dicMethods);
		model.addAttribute("productBrands", productBrands);
		model.addAttribute("productModels", productModels);
		model.addAttribute("productColors", productColors);
		model.addAttribute("guarantees", guarantees);
		model.addAttribute("productApperances", productApperances);
		model.addAttribute("productAttachments", productAttachments);
		model.addAttribute("productStates", productStates);
		//加载该id号的采购记录项
		model.addAttribute("orderInfoForm", orderInfoForm);
		return "purchase/update";
	}
	
	/**
	 * 进入采购登记页面
	 * @return
	 */
	@RequestMapping(value = "/purchaseCheckIn")
	public String purchaseCheckIn(Model model) {
		//获取下拉菜单的字典
		List<ProductDicMethod> dicMethods = getDictionaryList.getDicMethods();
		List<ProductBrand> productBrands = getDictionaryList.getProductBrands();
		List<ProductColor> productColors = getDictionaryList.getProductColors();
		List<ProductGuarantee> guarantees = getDictionaryList.getProductGuarantees();
		List<ProductApperance> productApperances = getDictionaryList.getProductApperances();
		List<ProductAttachment> productAttachments = getDictionaryList.getProductAttachments();
		List<ProductState> productStates = getDictionaryList.getProductStates();
		String loginUserName = Utility.getCurrentUsername();
		SysUser loginUser = userMapper.selectByUsername(loginUserName);
		model.addAttribute("user",loginUser);
		
		//加载下拉菜单的字典List
		model.addAttribute("dicMethods", dicMethods);
		model.addAttribute("productBrands", productBrands);
		model.addAttribute("productColors", productColors);
		model.addAttribute("guarantees", guarantees);
		model.addAttribute("productApperances", productApperances);
		model.addAttribute("productAttachments", productAttachments);
		model.addAttribute("productStates", productStates);
		return "purchase/check-in";
	}
	
	/**
	 * ajax返回ProductInfoMapper对应的字典
	 * 品牌机型规格
	 */
	@ResponseBody
	@RequestMapping(value =  "/getModel", produces = "application/json;charset=utf-8",method ={RequestMethod.POST})
	public Object[] getModel(
			@RequestParam(value = "who", required = false)String who,
			@RequestParam(value = "code1", required = false)Integer code1,
			@RequestParam(value = "code2", required = false)Integer code2) {
		List<ProductInfoForm> result = new ArrayList<>(); 
		List<ProductInfoForm> selectResult=null;
		//分类讨论是什么的code
		if("brand".equals(who)) {
			selectResult = purchaseServices.getProductInfoForms(code1, null, null);
			//重复的model项只允许出现一个, 用数组记录已存在的model
			List<Integer> alreadyModel=new ArrayList<>(10);
			for(ProductInfoForm infoForm : selectResult) {
				//如果alreadyModel不存在该model值
				if(!alreadyModel.contains(infoForm.getModel())) {
					alreadyModel.add(infoForm.getModel());
					result.add(infoForm);
				}
			}
		}else if("model".equals(who)) {
			result = purchaseServices.getProductInfoForms(code1, code2, null);
		}
		//防止传null到前台
		if(result==null) {
			return new Object[0];
		}
		return result.toArray();
	}
	@RequestMapping(value = "/return")
	public String refund(Model model,@RequestParam(value = "id", required = false)String id ) {
		Long orderId=Long.parseLong(id);

		model.addAttribute("orderId", orderId);
		return "purchase/add-tuikuan";
	}
	
	@ResponseBody
	@RequestMapping(value = "/returnSuccess")
	public ResultData refundMoney(
			@RequestParam(value = "id", required = false)String id,
			@RequestParam(value = "logisticsCompany", required = false)String logisticsCompany,
			@RequestParam(value = "logisticsNum", required = false)String logisticsNum ,
			@RequestParam(value = "carriagePrice", required = false)String carriagePrice ) {
		ResultData resultToPage = new ResultData();
			
		RefundList refundList = new RefundList();
		
		Long logisticsId = Long.valueOf(id);
		BigDecimal bd=new BigDecimal(carriagePrice);
		
		refundList.setorderId(logisticsId);
		refundList.setLogisticsCompany(logisticsCompany);
		refundList.setLogisticsNum(logisticsNum);
		refundList.setCarriagePrice(bd);
		java.util.Date date = new Date(System.currentTimeMillis());
		refundList.setTime(date);
		
		// 插入数据库
		int result =  refundListMapper.insertSelective(refundList);
		
		// 修改订单状态
		int result2 = purchaseServices.updateState(10, logisticsId);
		//返回结果
		if(result != 0 && result2 != 0){
			resultToPage.setCode("000000");
			resultToPage.setMessage("插入成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("插入失败！");
		}
		return resultToPage;
	}
	
	/**
	 * 采购页面的重质检申请处理
	 * @param state
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeState",produces = "application/json;charset=utf-8")
	public ResultData changeState(@RequestBody LongsAndIntegerDto longsAndIntegerDto) {
		
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：/purchase/list，传过来的参数为longsAndIntegerDto=" + longsAndIntegerDto );
		ResultData resultToPage = new ResultData();
		
		Integer state = longsAndIntegerDto.getState();
		for(Long id : longsAndIntegerDto.getIds()) {
			try {
				purchaseServices.updateState(state, id);
			}catch (Exception e) {
				logger.error("根据id修改状态出错!"+e.getMessage());
				resultToPage.setCode("999999");
				resultToPage.setMessage("修改状态失败！");
				return resultToPage;
			}
		}
		resultToPage.setCode("000000");
		resultToPage.setMessage("修改状态成功！");
		return resultToPage;
	}
}
