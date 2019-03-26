package com.xiongantaoli.background.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.entity.ProductApperance;
import com.xiongantaoli.background.entity.ProductAttachment;
import com.xiongantaoli.background.entity.ProductBrand;
import com.xiongantaoli.background.entity.ProductColor;
import com.xiongantaoli.background.entity.ProductDicMethod;
import com.xiongantaoli.background.entity.ProductGuarantee;
import com.xiongantaoli.background.entity.ProductInfo;
import com.xiongantaoli.background.entity.ProductModel;
import com.xiongantaoli.background.entity.ProductState;
import com.xiongantaoli.background.entity.RefundList;
import com.xiongantaoli.background.entity.RepairList;
import com.xiongantaoli.background.form.OrderInfoForm;
import com.xiongantaoli.background.form.OrderPeopleDto;
import com.xiongantaoli.background.mapper.DrawbackListMapper;
import com.xiongantaoli.background.mapper.ProductInfoMapper;
import com.xiongantaoli.background.mapper.ProductRepairMapper;
import com.xiongantaoli.background.mapper.QualityListMapper;
import com.xiongantaoli.background.mapper.QualityRoyaltyMapper;
import com.xiongantaoli.background.mapper.RepairListMapper;
import com.xiongantaoli.background.mapper.SoldOutMapper;
import com.xiongantaoli.background.mapper.SysUserMapper;
import com.xiongantaoli.background.service.GetDictionaryListService;
import com.xiongantaoli.background.service.PurchaseService;
import com.xiongantaoli.background.util.StringToDate;

/**
 * 订单管理类，管理员能对每笔订单进行操作
 * @author 周天
 *
 */
@Controller
@RequestMapping("/order")
public class OrderManageController {
	
	@Autowired
	private PurchaseService purchaseServices;
	@Autowired
	private GetDictionaryListService getDictionaryList;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private QualityListMapper qualityListMapper;
	@Autowired
	private RepairListMapper repairListMapper;
	@Autowired
	private SoldOutMapper soldOutMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;
	@Autowired
	private QualityRoyaltyMapper qualityRoyaltyMapper;
	@Autowired
	private ProductRepairMapper productRepairMapper;
	
	/**
	 * 进入订单记录列表页面,
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
		System.out.println(order.getOrderState());
		//分页
		PageHelper.startPage(pageNo, pageSize);
		List<OrderInfoForm> result = null;
		if(order.getOrderState() == null) {
			 result = purchaseServices.getOrdersByStates(order, 1, 2, 3,4,5,6,7,8,9,10);	
		}else {
			 result = purchaseServices.getOrdersByStates(order,order.getOrderState());	
		}
		
		PageInfo<OrderInfoForm> pageInfo = new PageInfo<OrderInfoForm>(result);
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			if(order.getOrderState() == null) { // 查找库中所有的订单并显示
				 result = purchaseServices.getOrdersByStates(order, 1, 2, 3,4,5,6,7,8,9,10);	
			}else {
				 result = purchaseServices.getOrdersByStates(order,order.getOrderState());	
			} 
			pageInfo = new PageInfo<OrderInfoForm>(result);
		}
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("ProductStates", getDictionaryList.getProductStates());
		model.addAttribute("order", order);
		
		return "order/list";
	}
	
	/**
	 * 进入订单记录编辑页面,
	 * 包括回显，更改信息
	 * @return
	 */
	@RequestMapping(value = "/updateOrderPage")
	public String updateOrderPage(Model model, 
			@RequestParam(value = "id", required = false)Long id) {
		// 查询该id号的采购记录项
		OrderInfoForm orderInfoForm = purchaseServices.selectOrderInfoFormByPrimaryKey(id);
		
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
		//加载该id号的采购记录项
		model.addAttribute("orderInfoForm", orderInfoForm);
		return "order/update";
	}
	/**
	 * 进入该订单各管理人员查看页面,
	 * 包括回显，更改信息
	 * @return
	 */
	@RequestMapping(value = "/reviewPage")
	public String reviewPage(Model model, 
			@RequestParam(value = "id", required = false)Long id) {
		// 查询该id号的采购记录项
		OrderInfoForm orderInfoForm = purchaseServices.selectOrderInfoFormByPrimaryKey(id);
		// 定义一个传递参数的对象，将参数传递给前端页面
		OrderPeopleDto orderPeople = new OrderPeopleDto();
		orderPeople.setOrderId(id); // 首先获取到该订单的id，通过该id查找下面的数据
		// 查询采购的采购人员操作的userid和username和提成
		orderPeople.setPurchaseId(orderInfoForm.getUserId()); // 首先从上面查找的采购记录项获取到采购操作人员的userid
		orderPeople.setPurchaseName(userMapper.selectByPrimaryKey(orderPeople.getPurchaseId()).getUsername()); // 再从user表中查找到该用户id对应的用户名 
		// 获得该商品给采购人员的提成
		if(orderInfoForm.getFinalPurchase() != null) {
			BigDecimal purchasePrice = orderInfoForm.getPurchasePrice().subtract(orderInfoForm.getFinalPurchase());
			ProductInfo product = productInfoMapper.selectByPrimaryKey(orderInfoForm.getProductId());
			purchasePrice = purchasePrice.multiply(product.getSaleRoyalty());
			purchasePrice = purchasePrice.add(product.getBaseRoyalty()); 
			orderPeople.setPurchasePrice(purchasePrice);
		}
		// 查询质检的质检人员操作的userid和username
		
		if(qualityListMapper.selectUserId(id) != null) {
		List<Long> userId = qualityListMapper.selectUserId(id); // 首先在qualitymapper表中查询到对应操作的质检id
		String userName = new String();
		for(Long user : userId)
		{
			String temp = userMapper.selectByPrimaryKey(user).getUsername();
			userName += temp;
			userName += "  ";
		}
		orderPeople.setQualityName(userName); // 再从user表中查询到该用户id对应的用户名
		}
		// 获得该商品给质检人员的提成
		if(orderInfoForm.getQualityPrice() != null) {
			List<Long> userId = qualityListMapper.selectUserId(id); 
			BigDecimal qualityPrice = qualityRoyaltyMapper.selectQualityPrice();	// 质检的基础提成
			orderPeople.setQualityPrice(qualityPrice.multiply(new BigDecimal(userId.size())));
		}
		// 查询维修的维修人员操作的userid和username
		if(repairListMapper.selectUserId(id) != null) {
		orderPeople.setRepairId(repairListMapper.selectUserId(id));
		orderPeople.setRepairName(userMapper.selectByPrimaryKey(orderPeople.getRepairId()).getUsername());
		}
		// 获得该商品给维修人员的提成
		if( repairListMapper.selectByOrderId(id) != null) {
			BigDecimal repairPrice = new BigDecimal(0);
			RepairList repairList = repairListMapper.selectByOrderId(id);
			String[] arr = repairList.getRepairStyle().split(",");
			for(String str : arr) {
				BigDecimal tempPrice = productRepairMapper.selectCountByCode(Integer.parseInt(str));
				repairPrice = repairPrice.add(tempPrice);
				System.out.println(repairPrice);
			}
			orderPeople.setRepairPrice(repairPrice);
		}
		// 查询销售人员的userid和username
		if(soldOutMapper.selectUserId(id) != null && (orderInfoForm.getOrderState() == 7 || orderInfoForm.getOrderState() == 4)) {
			orderPeople.setSalerId(soldOutMapper.selectUserId(id));
			orderPeople.setSalerName(userMapper.selectByPrimaryKey(orderPeople.getSalerId()).getUsername());
		}
		// 获得该商品给销售人员的提成
		if(orderInfoForm.getFinalPurchase() != null && (orderInfoForm.getOrderState() == 7 || orderInfoForm.getOrderState() == 4)) {
			BigDecimal salerPrice = orderInfoForm.getPurchasePrice().subtract(orderInfoForm.getFinalPurchase());
			ProductInfo product = productInfoMapper.selectByPrimaryKey(orderInfoForm.getProductId());
			salerPrice = salerPrice.multiply(product.getSaleRoyalty());
			salerPrice = salerPrice.add(product.getBaseRoyalty()); 
			orderPeople.setSalerPrice(salerPrice);
		}
		model.addAttribute("orderPeople", orderPeople);
		return "order/Review";
	}

}
