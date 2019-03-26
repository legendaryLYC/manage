package com.xiongantaoli.background.service;

import java.util.List;

import com.xiongantaoli.background.entity.ProductInfo;
import com.xiongantaoli.background.form.NewProductDto;
import com.xiongantaoli.background.form.ProductInfoForm;
import com.xiongantaoli.background.form.ThreeConditonDto;

/**
   * 商品信息页面逻辑的service接口
   * 时间:2019/1/10
 * @author 周天
   * 最后修改时间:2019/1/10
   * 注意事项:无
 */
public interface ProductsService {
	
	/**
	 * 添加新手机类型的报价信息的service实现
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/13
	* @param product
	* @author 周天
	*
	*/
	int addPhone(NewProductDto product);  
	
	
	/**
	 *删除某个商品信息的service实现
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	 * @param id
	* @author 周天
	*
	*/
	int deletePhone(Long id);
	
	/**
	 *更新某个商品信息的service实现
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	* @param record
	* @author 周天
	*
	*/
	int updatePhone(NewProductDto record);
	
	/**
	 *根据Controller传入的参数进行筛选
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/13
	* @param brand,@param model,@param standard
	* @author 周天
	*
	*/
	List<ProductInfoForm> selectByConditions(Integer brand,Integer modelt,Integer standard);
	
	/**
	 *根据Controller传入的id进行查找要编辑的页面
	 *  时间:2019/1/12
	 * 最后修改时间:2019/1/13
	 *  @param id
	* @author 周天
	*
	*/
	ProductInfoForm selectById(Long id); 
	
	/**
	 *根据Controller传入的品牌、型号、规格进行查找是否有重复的商品
	 *  时间:2019/1/13
	 * 最后修改时间:2019/1/13
	* @param threeConditionDTO
	* @author 周天
	*
	*/
	public Long checkPhone(ThreeConditonDto threeConditionDTO);
}
