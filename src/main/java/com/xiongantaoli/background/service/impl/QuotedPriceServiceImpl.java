package com.xiongantaoli.background.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.ProductBrand;
import com.xiongantaoli.background.entity.ProductInfo;
import com.xiongantaoli.background.entity.ProductModel;
import com.xiongantaoli.background.entity.ProductStandard;
import com.xiongantaoli.background.mapper.ProductBrandMapper;
import com.xiongantaoli.background.mapper.ProductInfoMapper;
import com.xiongantaoli.background.mapper.ProductModelMapper;
import com.xiongantaoli.background.mapper.ProductStandardMapper;
import com.xiongantaoli.background.service.QuotedPriceService;

/**
 *精准信息页面内容显示以及计算精准报价的service实现层
 *  时间:2019/1/10
 * 最后修改时间:2019/1/10
 * 注意事项:无
* @author 周天
*
*/
@Service
public class QuotedPriceServiceImpl implements QuotedPriceService{
	
	private static final Logger logger = LoggerFactory.getLogger(QuotedPriceServiceImpl.class);
	
	@Autowired
	private ProductBrandMapper productBrandMapper;
	@Autowired
	private ProductModelMapper productModelMapper;
	@Autowired
	private ProductStandardMapper productStandardMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Override
	public List<ProductBrand> showBrand() {		
		List<ProductBrand> brandlist = productBrandMapper.selectAll(); // 找出规格字典表所有的name值
		return brandlist;
	}


	@Override
	public List<ProductModel> showModel() {
		List<ProductModel>	modellist = productModelMapper.selectAll(); // 找出规格字典表所有的name值
		return modellist;		
	}


	@Override
	public List<ProductStandard> showStandard() {
		List<ProductStandard> standardlist = productStandardMapper.selectAll(); // 找出规格字典表所有的name值
		return standardlist;		
	}
	

	@Override
	public BigDecimal complutedPrice(int brand, int model, int standard, int scratch, int knock, int packing, int attachment,int scratchTwo,int knockTwo,int nationLock,int warranty) {
		ProductInfo productInfo = null;
		BigDecimal price = null;
		try {
			productInfo = findInfo(brand,model,standard); // 调用下面的FindInfo方法找到该商品的信息，并在下面计算估计价格
			price=productInfo.getBasePrice();
			if(scratch != -1) {
				price = price.subtract( productInfo.getScratchPrice()); // 通过下面的方法计算价格
			}
			if(knock != -1) {
				price = price.subtract( productInfo.getKnockPrice());
			}
			if(scratchTwo != -1) {
				price = price.subtract(productInfo.getScratchPriceTwo());
			}
			if(knockTwo != -1) {
				price = price.subtract(productInfo.getKnockPriceTwo());
			}
			if(nationLock != -1) {
				price = price.subtract(productInfo.getNationLock());
			}
			if(packing != -1) {
				price = price.add(productInfo.getPackingPrice());
			}
			if(attachment != -1) {
				price = price.add(productInfo.getAttachmentPrice());
			}
			if(warranty != -1) {
				price = price.add(productInfo.getWarranty());
			}
		} catch (Exception e) {
			logger.error("调用FindInfo方法出现错误，失败信息"+e.getMessage());
		}
		return price;
	}
	

	@Override
	public ProductInfo findInfo(int brand, int model, int standard) {
		ProductInfo	productinfo = null;
		try {
			productinfo = productInfoMapper.selectByThree(brand,model,standard); // 在三个字典表里面查找现在库里面的品牌、型号、规格	
		} catch (Exception e) {
			logger.error("调用selectByThree方法出现错误，失败信息"+e.getMessage());
		}
		return productinfo;
	}

}
