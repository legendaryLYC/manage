package com.xiongantaoli.background.mapper;

import java.util.List;

import com.xiongantaoli.background.entity.ProductInfo;
import com.xiongantaoli.background.form.NewProductDto;
import com.xiongantaoli.background.form.ProductInfoForm;

/**
 *商品信息操作的mapper文件
 *  时间:2019/1/10
 * 最后修改时间:2019/1/13
 * 注意事项:无
* @author 周天
*
*/
public interface ProductInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo record);
    
    int insertNew(NewProductDto record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Long id);
    
    ProductInfoForm selectByFormPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductInfo record);
    
    int updateByPrimaryKey(ProductInfo record);
    
    /**
             * 根据record里面的id更改product_info表中存储的商品信息，为商品列表页面的编辑功能服务
     * @param record
     * @return int
     * @author 周天
     */
    int updateByDtoKey(NewProductDto record);        
	
    /**
	 * 根据品牌，型号，规格三个参数查找符合条件的商品
	* @param Integer,Integer,Integer
	* @return List<ProductInfo>
	* @author 周天
	*/
    List<ProductInfoForm> selectByAllCondition(Integer brand,Integer model,Integer standard);          

    /**
	 * 查找product_info表里的所有商品
	* @param 
	* @return List<ProductInfo>
	* @author 周天
	*/   
    List<ProductInfo> selectAll();                                    

    /**
	 * 多表联合查询，在字典里查找出对应的商品，以及商品的品牌，型号，规格，并用一个form类来存储
	* @param 
	* @return List<ProductInfoForm>
	* @author 周天
	*/ 
    List<ProductInfoForm> selectProductInfo();                          

    /**
	 * 在精准报价页面通过选择的品牌，型号，规格，查找出对应商品的报价信息
	* @param int，int，int
	* @return ProductInfo
	* @author 周天
	*/ 
	ProductInfo selectByThree(int brand, int model, int standard);
	
	/**
	 * 由品牌，型号，规格3个条件筛选对应商品的报价信息
	 * @param brand
	 * @param model
	 * @param standard
	 * @author 孟晓冬
	 * @return
	 */
	List<ProductInfoForm> selectProductInfoFormsSelective(Integer brand, Integer model, Integer standard);
	
    /**
	 * 找到是否有重复的商品，有返回id值
	* @param Integer,Integer,Integer
	* @return Long
	* @author 周天
	*/ 
	Long selectBms(Integer brand, Integer model, Integer standard);
	
	
}