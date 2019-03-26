package com.xiongantaoli.background.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiongantaoli.background.entity.ProductBrand;
import com.xiongantaoli.background.entity.ProductModel;
import com.xiongantaoli.background.entity.ProductStandard;
import com.xiongantaoli.background.form.NewProductDto;
import com.xiongantaoli.background.form.ProductInfoForm;
import com.xiongantaoli.background.form.ThreeConditonDto;
import com.xiongantaoli.background.service.ProductsService;
import com.xiongantaoli.background.service.QuotedPriceService;

/**
 *商品信息页面的逻辑操作controller类
 *  时间:2019/1/10
 * 最后修改时间:2019/1/13
 * 注意事项:无
* @author 周天
*
*/
@Controller
@RequestMapping("/products")
public class ProductsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	private ProductsService productsService;
	@Autowired
	private QuotedPriceService quotedPriceService;
	
	/**
	 * 进入商品信息页面,默认显示所有商品
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	 * 注意事项:无
	* @author 周天
	*
	*/
	@RequestMapping(value = {"","/"})
	public String showALLPhone  (Model model ,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo ,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize ,
			ThreeConditonDto threeConditionDTO) {
		
		    PageHelper.startPage(pageNo, pageSize);			    
			List<ProductInfoForm> productsList = productsService.selectByConditions(threeConditionDTO.getBrand(),threeConditionDTO.getModel(),threeConditionDTO.getStandard());
			//进行分页显示的代码部分
			PageInfo<ProductInfoForm>  pageInfo = new PageInfo<>(productsList);
			if (pageNo > pageInfo.getPages()) {
				pageNo = pageInfo.getPages();
				PageHelper.startPage(pageNo, pageSize);
			    productsList = productsService.selectByConditions(threeConditionDTO.getBrand(),threeConditionDTO.getModel(),threeConditionDTO.getStandard());
				pageInfo = new PageInfo<ProductInfoForm>(productsList);
			}
			List<ProductBrand> brandList = quotedPriceService.showBrand(); // 用service拿出品牌，规格，型号等
			List<ProductModel> modelList = quotedPriceService.showModel();
			List<ProductStandard> standardList = quotedPriceService.showStandard();
			
			model.addAttribute("threeConditionDTO", threeConditionDTO);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("brandInfo", brandList);
			model.addAttribute("modelInfo", modelList);
			model.addAttribute("standardInfo", standardList);
			return "goods/info/product_info";
		
	}
	
	/**
	 * 进入手机添加页面,添加新的商品
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	 * 注意事项:无
	* @author 周天
	*
	*/
	@RequestMapping(value = "/addProductHtml")
	public String addProductHtml(Model model) {
		List<ProductBrand> brandList = quotedPriceService.showBrand(); // 用service拿出品牌，规格，型号等
		List<ProductModel> modelList = quotedPriceService.showModel();
		List<ProductStandard> standardList = quotedPriceService.showStandard();
		
		model.addAttribute("brandInfo", brandList);
		model.addAttribute("modelInfo", modelList);
		model.addAttribute("standardInfo", standardList);
		return "goods/info/product_add";
	}
	
	/**
	 * 在手机添加页面,添加新的商品
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = {"/addPhone"})
	public int addPhone(NewProductDto product) {		
		int flag =-1;
		Long id = -1L;
		if(product.getBasePrice().compareTo(product.getScratchPrice().add(product.getKnockPrice()).add(product.getKnockPriceTwo()).add(product.getScratchPriceTwo())) <=0 )
		{
			return -2; // 判断基础价格要剪去的那些划痕价格是否会小于0，小于0返回-2.前端提示
		}
		ThreeConditonDto threeConditionDTO = new ThreeConditonDto(product.getBrand(),product.getModel(),product.getStandard());
		id = productsService.checkPhone(threeConditionDTO); 
		if(id == null) {
			flag = productsService.addPhone(product);	
		}
		return flag;
	}
	
	/**
	 * 进入手机删除操作,删除单一或选定的一些的商品
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/13
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = { "/deletePhone"}, produces = "application/json", consumes = "application/json")
	public int deletePhone(@RequestBody Long[] id ) {
		int flag;	
		for(int i = 0;i < id.length; i++) { // 循环遍历传过来需要删除的id数组
			flag=productsService.deletePhone(id[i]);	
			if(flag <= 0)
			{
				logger.info("====调用deletePhone方法删除操作失败====");
				return 0;
			}
		}
		return 1;
	}
	
	/**
	 * 进入手机编辑页面,编辑商品信息
	 *  时间:2019/1/11
	 * 最后修改时间:2019/1/11
	 * 注意事项:无
	* @author 周天
	*
	*/
	@RequestMapping(value = "/editProductHtml/{id}")
	public String editProductHtml(Model model, @PathVariable("id") Long id) {	
		
		ProductInfoForm editProduct = productsService.selectById(id); // 通过分割得到的id进行查找
		
		List<ProductBrand> brandList = quotedPriceService.showBrand(); // 用service拿出品牌，规格，型号等
		List<ProductModel> modelList = quotedPriceService.showModel();
		List<ProductStandard> standardList = quotedPriceService.showStandard();
		
		model.addAttribute("editProduct", editProduct);
		model.addAttribute("brandInfo", brandList);
		model.addAttribute("modelInfo", modelList);
		model.addAttribute("standardInfo", standardList);	
		return "goods/info/product_edit";
	}
	
	/**
	 * 进入手机信息更新操作,更新单一商品
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = { "/updatePhone"} , produces = "application/json;charset=UTF-8",method ={RequestMethod.POST})
	public int updatePhone(NewProductDto product) {
		int flag =-1;
		Long id = -1L;
		if(product.getBasePrice().compareTo(product.getScratchPrice().add(product.getKnockPrice()).add(product.getKnockPriceTwo()).add(product.getScratchPriceTwo())) <=0 )
		{
			return -2; // 判断基础价格要剪去的那些划痕价格是否会小于0，小于0返回-2.前端提示
		}
		ThreeConditonDto threeConditionDTO = new ThreeConditonDto(product.getBrand(),product.getModel(),product.getStandard());
		id = productsService.checkPhone(threeConditionDTO); // 检查库中是否已有该id，不进行更新
		if(id == null || id == product.getId()) {
			flag = productsService.updatePhone(product);
		}
		return flag;
	}
	/**
	 * 进行手机信息品牌、规格、型号检测
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	 * 注意事项:无
	* @author 周天
	*@param threeConditionDTO
	*/
	@ResponseBody
	@RequestMapping(value = { "/checkRepeatPhone"} , produces = "application/json;charset=UTF-8",method ={RequestMethod.POST})
	public Long checkRepeatPhone(ThreeConditonDto threeConditionDto) {
		Long id = productsService.checkPhone(threeConditionDto); // 前端通过返回的flag值来判断更新是否成功 	
		if(id == null){
			return -1L;
		}
		return id;
	}
	
}
