package com.xiongantaoli.background.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiongantaoli.background.entity.ProductInfo;
import com.xiongantaoli.background.form.NewProductDto;
import com.xiongantaoli.background.form.ProductInfoForm;
import com.xiongantaoli.background.form.ThreeConditonDto;
import com.xiongantaoli.background.mapper.ProductInfoMapper;
import com.xiongantaoli.background.service.ProductsService;


/**
 * 商品信息页面逻辑的service实现
 *  时间:2019/1/10
 * 最后修改时间:2019/1/10
 * 注意事项:无
* @author 周天
* 
*
*/
@Service
public class ProductsServiceImpl implements ProductsService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);
	
	@Autowired
	private ProductInfoMapper productsInfoMapper;
	
	public int  addPhone(NewProductDto product) {
		int flag = productsInfoMapper.insertNew(product); // 定义flag来返回插入成功的值，插入成功返回值大于0		 
		return flag;
	}

	
	@Override
	public int deletePhone(Long id) {
		int	flag = productsInfoMapper.deleteByPrimaryKey(id); // 通过商品id删除对应的商品
		return flag;
	}

	@Override
	public int updatePhone(NewProductDto record) {
		int flag = productsInfoMapper.updateByDtoKey(record); // 通过商品id更新对应的商品
		return flag;
	}	

	@Override
	public List<ProductInfoForm> selectByConditions(Integer brand,Integer model,Integer standard) {
		List<ProductInfoForm> productsAllList = null;
			if(brand == null && model == null && standard == null) {
				 productsAllList = productsInfoMapper.selectProductInfo(); // 默认找到所有的商品
			}else {
				productsAllList = productsInfoMapper.selectByAllCondition(brand,model,standard); //或者根据条件进行筛选查找
				if(productsAllList == null) {
					logger.error("传入的brand,model,standard参数有问题，或者传入的brand,model,standard参数并没有找到相应的数据");
				}
			 }
			return productsAllList;
	}
	
	@Override
	public ProductInfoForm selectById(Long id) {
		ProductInfoForm editProduct = productsInfoMapper.selectByFormPrimaryKey(id); // 根据id进行查找需要编辑的商品
		return editProduct;
	}


	@Override
	public Long checkPhone(ThreeConditonDto threeConditionDTO) {
		// 检查库中是否有重复的商品
		Long id = productsInfoMapper.selectBms(threeConditionDTO.getBrand(), threeConditionDTO.getModel(), threeConditionDTO.getStandard());
		return id;
	}
}
