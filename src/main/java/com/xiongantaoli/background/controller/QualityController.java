package com.xiongantaoli.background.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiongantaoli.background.entity.ProductDicMethod;
import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.entity.ProductApperance;
import com.xiongantaoli.background.entity.ProductAttachment;
import com.xiongantaoli.background.entity.ProductBrand;
import com.xiongantaoli.background.entity.ProductColor;
import com.xiongantaoli.background.entity.ProductGuarantee;
import com.xiongantaoli.background.entity.ProductModel;
import com.xiongantaoli.background.entity.ProductState;
import com.xiongantaoli.background.entity.QualityList;
import com.xiongantaoli.background.entity.SysUser;
import com.xiongantaoli.background.form.LongsAndIntegerDto;
import com.xiongantaoli.background.form.OrderInfoForm;
import com.xiongantaoli.background.mapper.SysUserMapper;
import com.xiongantaoli.background.model.ResultData;
import com.xiongantaoli.background.service.GetDictionaryListService;
import com.xiongantaoli.background.service.PurchaseService;
import com.xiongantaoli.background.service.QualityService;
import com.xiongantaoli.background.util.Utility;

/**
 * 质检相关功能控制类
 * @author 孟晓冬
 */
@Controller
@RequestMapping("/quality")
public class QualityController {
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private GetDictionaryListService getDictionaryList;
	@Autowired
	private QualityService qualityService;
	@Autowired
	private SysUserMapper userMapper;
	
	
	private static final Logger logger = LoggerFactory.getLogger(QualityController.class);
	
	/**
	 * 质检列表页面
	 * 包括筛选, 分页
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public String index(Model model,Order order,
		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
		@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
		) {
			//分页
			PageHelper.startPage(pageNo, pageSize);
			List<OrderInfoForm> result = purchaseService.selectOrdersSelective(order,1,6);
			PageInfo<OrderInfoForm> pageInfo = new PageInfo<OrderInfoForm>(result);
			if (pageNo > pageInfo.getPages()) {
				pageNo = pageInfo.getPages();
				PageHelper.startPage(pageNo, pageSize);
				result = purchaseService.selectOrdersSelective(order,1);
				pageInfo = new PageInfo<OrderInfoForm>(result);
			}
			
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("order", order);
			return "quality/list";
	}
	
	/**
	 * 质检页面的审核控制(修改order状态)并记录时间和质检员id
	 * @param state
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeState",consumes = "application/json",produces = "application/json;charset=utf-8")
	public ResultData changeState(@RequestBody LongsAndIntegerDto longsAndIntegerDto) {
		
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：/quality/list，传过来的参数为longsAndIntegerDto=" + longsAndIntegerDto );
		ResultData resultToPage = new ResultData();
		Boolean resultRecord=false;
		
		Integer state = longsAndIntegerDto.getState();
		for(Long id : longsAndIntegerDto.getIds()) {
			try {
				purchaseService.updateState(state, id);
			}catch (Exception e) {
				logger.error("根据id修改状态出错!"+e.getMessage());
				resultToPage.setCode("999999");
				resultToPage.setMessage("修改状态失败！");
				return resultToPage;
			}
			//创建一条质检记录
			QualityList qualityList = new QualityList();
			//获取用户id, 用户id存到对象的usrId,时间自动生成
			String loginUserName = Utility.getCurrentUsername();
			SysUser loginUser = userMapper.selectByUsername(loginUserName);
			qualityList.setOrderId(id);
			qualityList.setQualityId(loginUser.getId());
			//把质检记录插入到质检表
			if(qualityService.selectByOrderId(id) == 0) { // 如果这个orderid记录还没在质检表中就插入它，再返回
			resultRecord = qualityService.insertQualityRecord(qualityList);
			//检查插入结果是否正确
			if(resultRecord == false) {
				resultToPage.setCode("999999");
				resultToPage.setMessage("插入质检记录出错！");
				return resultToPage;
				}
			} // 如果有这条记录，则跳过插入
		}
		resultToPage.setCode("000000");
		resultToPage.setMessage("修改状态成功！");
		return resultToPage;
	}
	
	/**
	 * 进入质检修改页面
	 */
	@RequestMapping(value = "/updateOrderPage")
	public String updateOrderPage(Model model, 
			@RequestParam(value = "id", required = false)Long id) {
		//查询该id号的采购记录项
		OrderInfoForm orderInfoForm = purchaseService.selectOrderInfoFormByPrimaryKey(id);
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
		return "quality/update";
	}
	
}
