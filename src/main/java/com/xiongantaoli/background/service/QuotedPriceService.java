package com.xiongantaoli.background.service;

import java.math.BigDecimal;
import java.util.List;

import com.xiongantaoli.background.entity.ProductBrand;
import com.xiongantaoli.background.entity.ProductInfo;
import com.xiongantaoli.background.entity.ProductModel;
import com.xiongantaoli.background.entity.ProductStandard;

/**
 *精准信息页面内容显示以及计算精准报价的service层
 *  时间:2019/1/10
 * 最后修改时间:2019/1/13
 * 注意事项:无
* @author 周天
*
*/
public interface QuotedPriceService {
	
	/**
	 *精准信息页面品牌种类内容查询
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/12
	 * 注意事项:无
	* @author 周天
	*
	*/
	public List<ProductBrand> showBrand();
	
	/**
	 *精准信息页面型号种类内容查询
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/13
	 * 注意事项:无
	* @author 周天
	*
	*/
	public List<ProductModel> showModel();
	
	/**
	 *精准信息页面规格种类内容查询
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/13
	 * 注意事项:无
	* @author 周天
	*
	*/
	public List<ProductStandard> showStandard();
	
	/**
	 *通过传入的品牌，型号，规格查找到磨损的估价
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/13
	 * 注意事项:无
	* @author 周天
	 * @param brand 
	 * @param modelt 
	 * @param standard 
	 * @param scratch 
	 * @param knock
	 * @param packing
	 * @param attachment
	*
	*/
	BigDecimal complutedPrice(int brand, int model, int standard, int scratch, int knock, int packing, int attachment,int scratchTwo, int knockTwo, int nationLock, int warranty);
	
	/**
	 *通过前面ComplutedPrice方法传入的参数进行查找对应商品的信息
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/13
	 * 注意事项:无
	* @author 周天
	 * @param brand 
	 * @param modelt
	 * @param standard 
	 *
	*
	*/
	ProductInfo findInfo(int brand, int model, int standard);

}
