package com.xiongantaoli.background.service;

import java.util.List;

import com.xiongantaoli.background.entity.*;

/**
 * 该service用于获取字典表list
 * @author 孟晓冬
 */
public interface GetDictionaryListService {
	
	/**
	 * 返回状态字典
	 * @return List<ProductState>
	 */
	List<ProductState> getProductStates();
	/**
	 * 返回品牌字典
	 * @return List<ProductBrand>
	 */
	List<ProductBrand> getProductBrands(); 
	
	/**
	 * 返回采购方式字典
	 * @return
	 */
	List<ProductDicMethod> getDicMethods();
	
	/**
	 * 返回型号字典
	 * @return
	 */
	List<ProductModel> getProductModels();
	
	/**
	 * 返回颜色字典
	 * @return
	 */
	List<ProductColor> getProductColors();
	
	/**
	 * 返回保修字典
	 * @return
	 */
	List<ProductGuarantee> getProductGuarantees();
	
	/**
	 * 返回外观字典
	 * @return
	 */
	List<ProductApperance> getProductApperances();
	
	/**
	 * 返回随机附件字典
	 * @return
	 */
	List<ProductAttachment> getProductAttachments();
	
	/**
	 * 返回维修类型字典
	 * @return 
	 * @author 卜勇峰
	 */
	List<ProductRepair> getProductRepair();
	
	/**
	 * 返回物料选择类型字典
	 * @return 
	 * @author 卜勇峰
	 */
	List<Materiel> getMateriels();
}
