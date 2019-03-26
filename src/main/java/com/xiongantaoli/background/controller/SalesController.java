package com.xiongantaoli.background.controller;

import java.math.BigDecimal;
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
import com.xiongantaoli.background.entity.ProductDicMethod;
import com.xiongantaoli.background.entity.DrawbackList;
import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.entity.ProductApperance;
import com.xiongantaoli.background.entity.ProductAttachment;
import com.xiongantaoli.background.entity.ProductBrand;
import com.xiongantaoli.background.entity.ProductColor;
import com.xiongantaoli.background.entity.ProductGuarantee;
import com.xiongantaoli.background.entity.ProductModel;
import com.xiongantaoli.background.entity.ProductState;
import com.xiongantaoli.background.entity.RepairList;
import com.xiongantaoli.background.entity.RepairManagement;
import com.xiongantaoli.background.entity.SoldOut;
import com.xiongantaoli.background.entity.SysUser;
import com.xiongantaoli.background.form.LongsAndIntegerDto;
import com.xiongantaoli.background.form.OrderInfoForm;
import com.xiongantaoli.background.mapper.DrawbackListMapper;
import com.xiongantaoli.background.mapper.OrderMapper;
import com.xiongantaoli.background.mapper.RepairListMapper;
import com.xiongantaoli.background.mapper.RepairManagementMapper;
import com.xiongantaoli.background.mapper.SoldOutMapper;
import com.xiongantaoli.background.mapper.SysUserMapper;
import com.xiongantaoli.background.model.ResultData;
import com.xiongantaoli.background.service.GetDictionaryListService;
import com.xiongantaoli.background.service.PurchaseService;
import com.xiongantaoli.background.service.SalesSerivce;
import com.xiongantaoli.background.util.Utility;

/**
 * 销售相关功能控制类
 * 销售列表页面
 * 包括筛选, 分页
 * @author 李永成
 * @return List<OrderInfoForm>
 */

@Controller
@RequestMapping("/sales")
public class SalesController {
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private GetDictionaryListService getDictionaryList;
	@Autowired
	private PurchaseService purchaseServices;
	@Autowired
	private OrderMapper operate;
	@Autowired 
	private SoldOutMapper operatesoldoutmapper;
	@Autowired
	private SalesSerivce salesSerivce;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired 
	private DrawbackListMapper operateDrawBack;
	@Autowired
	private RepairListMapper operateRepairList;
	@Autowired 
	private RepairManagementMapper operateRepairManagement;
	
	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
	
	/**
	 * 销售列表页面显示页面(修改order状态)
	 * @param Integer pageNo,Integer pageSize
	 * @return html 列表页面
	 */
	@RequestMapping(value = { "", "/" })
	public String index(Model model,Order order,
		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
		@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
		) {
			PageHelper.startPage(pageNo, pageSize); 		// 分页
			List<OrderInfoForm> result = purchaseService.selectsoldselective(order); //调用select函数查看状态为已售出,部分退款,交易完成的订单
			PageInfo<OrderInfoForm> pageInfo = new PageInfo<OrderInfoForm>(result);
			if (pageNo > pageInfo.getPages()) {
				pageNo = pageInfo.getPages();
				PageHelper.startPage(pageNo, pageSize);
				result = purchaseService.selectsoldselective(order); //调用晓东的接口，查出良品，退款，部分退款给前端返回给前端
			}
			
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("order", order);
			return "sales/list";
	}
	/**
	 * 销售列表页面的状态修改(修改order状态)
	 * @param LongsAndIntegerDto
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/changeState",produces = "application/json;charset=utf-8")
	public String changeState(@RequestBody LongsAndIntegerDto longsAndIntegerDto) {
		
		Integer state = longsAndIntegerDto.getState();
		//只允许改成待维修(2),良品(3),退回(5)
//		if( state != 2 && state != 3 && state != 5 )
//			return "-1";
		for(Long id : longsAndIntegerDto.getIds()) {
			try {
				purchaseService.updateState(state, id);
			}catch (Exception e) {
				logger.error("根据id修改状态出错!"+e.getMessage());
				return "0";
			}
		}
		return "1";
	}
	
	/**
	 * 销售记录修改页面(修改order状态)
	 * @param LongsAndIntegerDto
	 * @return String
	 */
	@RequestMapping(value = "/updateOrderPage")
	public String updateOrderPage(Model model, 
			@RequestParam(value = "id", required = false)Long id) {
		// 查询该id号的销售记录项
		OrderInfoForm orderInfoForm = purchaseService.selectOrderInfoFormByPrimaryKey(id);
		
		// 获取下拉菜单的字典
		List<ProductDicMethod> dicMethods = getDictionaryList.getDicMethods();
		List<ProductBrand> productBrands = getDictionaryList.getProductBrands();
		List<ProductModel> productModels = getDictionaryList.getProductModels();
		List<ProductColor> productColors = getDictionaryList.getProductColors();
		List<ProductGuarantee> guarantees = getDictionaryList.getProductGuarantees();
		List<ProductApperance> productApperances = getDictionaryList.getProductApperances();
		List<ProductAttachment> productAttachments = getDictionaryList.getProductAttachments();
		List<ProductState> productStates = getDictionaryList.getProductStates();
		
		// 加载下拉菜单的字典List
		model.addAttribute("dicMethods", dicMethods);
		model.addAttribute("productBrands", productBrands);
		model.addAttribute("productModels", productModels);
		model.addAttribute("productColors", productColors);
		model.addAttribute("guarantees", guarantees);
		model.addAttribute("productApperances", productApperances);
		model.addAttribute("productAttachments", productAttachments);
		model.addAttribute("productStates", productStates);
		//加载该id号的销售记录项
		model.addAttribute("orderInfoForm", orderInfoForm);
		return "sales/update";
	}
	
	/**
	 * 销售页面根据物流单号和手机序列号筛选订单
	 * @param String orderNum,物流单号    String serialNum手机序列号
	 * @return html 列表页面
	 */
	@RequestMapping(value = "/selectsales")
	public String selectSales(Model model,Order order,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "orderNum", required = false, defaultValue = "") String orderNum,
			@RequestParam(value = "serialNum", required = false, defaultValue = "") String serialNum
			) {
			//获取ordernum并set到order对象
			order.setOrderNum(orderNum);
			order.setSerialNum(serialNum);
			//分页
			PageHelper.startPage(pageNo, pageSize);
			List<OrderInfoForm> result = purchaseServices.selectOrdersSelective(order,3); // 调用晓东接口筛选出符合要求的订单
			PageInfo<OrderInfoForm> pageInfo = new PageInfo<OrderInfoForm>(result);
			if (pageNo > pageInfo.getPages()) {
				pageNo = pageInfo.getPages();
				PageHelper.startPage(pageNo, pageSize);
				result = purchaseServices.selectOrdersSelective(order,3);
			}
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("ProductStates", getDictionaryList.getProductStates());
			model.addAttribute("order", order);
			return "sales/list";
		}
	
	/**
	 * 销售状态为良品的订单
	 * @param String orderNum,物流单号    String serialNum手机序列号
	 * @return html 销售页面
	 */
	@RequestMapping(value = "/addsales")
	public String addSales(Model model,@RequestParam(value = "id", required = false)String id ) {
		Long key=Long.parseLong(id);
		Order sale = operate.selectByPrimaryKey(key); // 根据订单id查出该订单，封装对象，传给前台
		model.addAttribute("sale",sale);
		return "sales/addsales";
	}
	
	/**
	 * 销售订单成功，更改状态，填写物流
	 * @param 订单对应的实体类
	 * @return html 销售列表页面
	 */
	@ResponseBody
	@RequestMapping(value = "/salesuccess", params = "save=true")
	public String saleSuccess(Model model, @ModelAttribute(value = "sale") SoldOut sale,HttpServletRequest request)
	{
		Long key=Long.parseLong(request.getParameter("id"));
		sale.setId(null);
		sale.setPurchaseId(key);
		String loginUserName = Utility.getCurrentUsername();
		SysUser loginUser = userMapper.selectByUsername(loginUserName);
		sale.setUserId(loginUser.getId());
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
				sale.setTime(currentDate);
		try{
			operate.updateState(8, key); // 更改订单状态为已售出
			operatesoldoutmapper.insertSelective(sale); // 把销售成功的订单信息放在售出的物流信息表里
			}
		catch (Exception e) {
			logger.debug("异常！！！！！！！！！！！"+e.getMessage());
			return "2";
		}
		return "1";
	}
	/**
	 * 已售出商品部分退款，跳到退款页面，填写退款金额
	 * @param 订单id
	 * @return html 订单部分退款页面
	 */
	@RequestMapping(value = "/refundMoney")
	public String refundMoney(Model model,@RequestParam(value = "id", required = false)String id ) {
		Long key=Long.parseLong(id);
		Order sale = operate.selectByPrimaryKey(key); // 根据id查出对应订单，封装对象，传给前台
		model.addAttribute("sale",sale);
		return "sales/refundMoney";
	}
	
	/**
	 * 已售出商品部分退款，更改退款金额，更改订单状态为部分退款。
	 * @param id，订单id
	 * @return ResultData 返回给前台处理结果
	 */
	@ResponseBody
	@RequestMapping(value = "/refundSuccess")
	public ResultData refundSuccess(Model model, @ModelAttribute(value = "sale") SoldOut sale,HttpServletRequest request)
	{
		/*
		 * 打印入参日志
		 */
		logger.info("SalesController下refundSuccess方法，调用者为：XXX，传过来的参数为id=" + request.getParameter("id") + "##who=" + request.getParameter("who"));
		ResultData result = new ResultData();
		Long key = null;
		BigDecimal price = null;
		int flag = 0;
		try {
			 key = Long.parseLong(request.getParameter("id"));
			 flag = Integer.parseInt(request.getParameter("who"));
			 if(flag == 1) {
				price = new BigDecimal(request.getParameter("refundMoney"));
			 }
		} catch (Exception e) {
			result.setCode("999999");
			result.setMessage("你传入的参数有问题，请按照要求传参！");
			return result;
		}

		if(flag == 1) {
			price = new BigDecimal(request.getParameter("refundMoney"));
			// 退还部分金额, 通过把上面变量的price赋值给根据id找到的part_pricce
			if(salesSerivce.refundPartly(key, price) != true) {
				result.setCode("999999");
				result.setMessage("部分退款失败！");
				return result;
			}
		}else {
			// 退货退款, 通过key(id)值将order表中的quality_price赋值给part_price
			if(salesSerivce.refundAll(key) != true) {
				result.setCode("999999");
				result.setMessage("全部退款失败！");
				return result;
			}
		}
		/*
		 * 打印出参日志
		 */
		result.setCode("000000");
		result.setMessage("修改成功！");
		return result;
	}
	
	/**
	 * 全部退款页面
	 * @param id，订单id，
	 * @return 全部退款页面，在前台填写物流信息
	 */
	@RequestMapping(value = "/refundall")
	public String refundAll(Model model,@RequestParam(value = "id", required = false)String id ) {
		Long key=Long.parseLong(id);
		Order sale = operate.selectByPrimaryKey(key);
		model.addAttribute("sale",sale);
		return "sales/refundAll";
	}
	
	/**
	 * 处理全部退款
	 * @param 订单的实体类
	 * @return 返回处理逻辑结果的状态吗，1代表成功，2代表失败
	 */
	@ResponseBody
	@RequestMapping(value = "/refundallsuccess", params = "save=true")
	public String refundAllSuccess(Model model, @ModelAttribute(value = "sale") DrawbackList sale,HttpServletRequest request) {
		Long key=Long.parseLong(request.getParameter("id"));
		Order order1 = salesSerivce.selectByPrimaryKey(key);
//		try {
//			SoldOut soldOut = operatesoldoutmapper.selectByPrimaryKey(key); // 删除售出表sold_out订单信息
//			if(soldOut!=null) {
//				operatesoldoutmapper.deleteByPurchaseId(key); 
//			}
//			DrawbackList drawBack = operateDrawBack.selectByPrimaryKey(key); // 删除退货退款物流信息的记录
//			if(drawBack!=null) {
//				operateDrawBack.deleteByPurchaseId(key);
//			}
//			RepairList repairlist = operateRepairList.selectByPrimaryKey(key); // 删除维修列表repair_list的记录
//			if(repairlist!=null) {
//				operateRepairList.deleteByOrderId(key);
//			}
//			RepairManagement repairmanagement = operateRepairManagement.selectByPrimaryKey(key);
//			if(repairmanagement!=null) {
//				operateRepairManagement.deleteByPurchaseId(key);
//			}
//		}
//		catch (Exception e) {
//			System.err.println("删除原订单的成本信息失败");
//			logger.error("删除原订单的成本信息失败");
//		}
		try {
			purchaseService.updateState(0, key); // 原来的订单状态置为无效
			order1.setId(null);
			order1.setPartPrice(null);
			order1.setOrderState(6);
			purchaseService.insertOrder(order1); // 插入新订单
			sale.setId(null);
			sale.setPurchaseId(order1.getId());
			String loginUserName = Utility.getCurrentUsername();
			SysUser loginUser = userMapper.selectByUsername(loginUserName);
			sale.setUserId(loginUser.getId());
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			sale.setTime(currentDate);
			operateDrawBack.insertSelective(sale); // 退款信息插入物流信息表sold_out成功
			return "1";
		}
		catch(Exception e) {
			logger.info("插入物流信息失败");
			return "2";
		}
//		try {
//			order1.setId(null);
//			order1.setOrderNum(null);
//			order1.setOperator(null);
//			order1.setCustomerName(null);
//			order1.setPartPrice(null);
//			order1.setQualityPrice(null);
//			order1.setOrderState(1);
//			purchaseService.insertOrder(order1);
//			return "1";
//		}
//		catch (Exception e)
//		{
//			logger.error(e.getMessage());
//			return "2";
//		}
	}
	
	/**
	 * 更改已售出的状态的商品的状态为交易完成
	 * @param 订单的实体类
	 * @return 返回处理逻辑结果的状态码，1代表成功，0代表失败
	 */
	@ResponseBody
	@RequestMapping(value = "/updatesales",produces = "application/json;charset=utf-8")
	public String updateSales(@RequestBody Long[] ids){
		String result = "0";
		try {
			for(Long iLong : ids) {
				System.err.println(iLong);
			}
			for (Long id : ids) {
				if(id != null) {
					purchaseService.updateState(4, id); // 调用晓东接口，把已完成，退款的状态修改为交易完成
			  }
			}
			result = "1";
		}
		//返回结果
		catch (Exception e) {
			result = "0";
		}
		return result;
	}
	
	/**
	 * 更改良品的成交价格
	 * @param sale 订单实体类
	 * @param id 订单id
	 * @return 编辑订单页面
	 */
	@RequestMapping(value = "/updatebyid/{id}")
	public String updateByidBefore(Model model, @ModelAttribute(value = "sale") Order sale,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		sale = salesSerivce.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("sale", sale);
		return "sales/editor-sales";
	}
	
	/**
	 * 更改良品的成交价格(修改order状态)
	 * @param sale 订单实体类
	 * @return 返回处理逻辑结果的状态码，1代表成功，0代表失败
	 */
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updateByid(Model model, @ModelAttribute(value = "sale") Order sale,HttpServletRequest request) {
		logger.debug("调用sales/updatebyid");
		try {
			boolean flag = false;
			flag=salesSerivce.updatePriceByid(sale);
			if(flag == true) {
				return "1";
			}
			else return "2";
			}
		catch (Exception e) {
			return "2";
		}
	}
}
