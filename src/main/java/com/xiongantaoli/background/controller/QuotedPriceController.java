package com.xiongantaoli.background.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiongantaoli.background.entity.ProductBrand;
import com.xiongantaoli.background.entity.ProductModel;
import com.xiongantaoli.background.entity.ProductStandard;
import com.xiongantaoli.background.form.ProductInfoForm;
import com.xiongantaoli.background.service.PurchaseService;
import com.xiongantaoli.background.service.QuotedPriceService;
import com.xiongantaoli.background.service.impl.QuotedPriceServiceImpl;

/**
 * 进入精准报价页面,从数据库中拿出现有的手机种类，品牌，规格，再拿取该类型的手机的相关信息
 *  时间:2019/1/10
 * 最后修改时间:2019/1/10
 * 注意事项:无
* @author 周天
*
*/
@Controller
@RequestMapping("/quoted")
public class QuotedPriceController {
	
	private static final Logger logger = LoggerFactory.getLogger(QuotedPriceController.class);
	
	@Autowired
	private QuotedPriceService quotedPriceService;
	@Autowired
	private PurchaseService purchaseServices;
	
	/**
	 * 进入精准报价页面,从数据库中拿出现有的手机种类，品牌，规格
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	* @param model
	* @author 周天
	*
	*/
	@RequestMapping(value = { "" ,"/"})
	public String quotedHtml(Model model) {
		List<ProductBrand> brandList = quotedPriceService.showBrand(); // 显示库中所有的品牌
		model.addAttribute("brandInfo", brandList);
		
		return "goods/price/quoted_price";
	}
	
	/**
	 * 进入精准报价页面后，估计该产品的价格
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = { "/showFourPrice" })
	public BigDecimal showFourPrice  (int brand,int model,int standard,int scratch,int knock,int packing,int attachment,int scratchTwo,int knockTwo,int nationLock,int warranty) {
		BigDecimal price = null; 
		price = quotedPriceService.complutedPrice(brand,model,standard,scratch,knock,packing,attachment,scratchTwo,knockTwo,nationLock,warranty); // 通过前台传过来的参数给service，查询该手机的价格
		if(price != null && price.compareTo(new BigDecimal(0))<0){
			price = new BigDecimal(-1);
		}
		return price;
	}
	
	/**
	 * ajax返回ProductInfoMapper对应的字典
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/13
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value =  "/getThree", produces = "application/json;charset=utf-8",method ={RequestMethod.POST})
	public Object[] getThree(
			@RequestParam(value = "who", required = false)String who,
			@RequestParam(value = "code1", required = false)Integer code1,
			@RequestParam(value = "code2", required = false)Integer code2) {
		List<ProductInfoForm> result = new ArrayList<>(); 
		List<ProductInfoForm> selectResult=null;
		//分类讨论是什么code
		if("brand".equals(who)) {
			selectResult = purchaseServices.getProductInfoForms(code1, null, null); //通过品牌去product_info表中查询型号
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
			result = purchaseServices.getProductInfoForms(code1, code2, null); //通过品牌、型号去product_info表中查询规格
		}
		//防止传null到前台
		if(result==null) {
			return new Object[0];
		}
		return result.toArray();
	}
}
