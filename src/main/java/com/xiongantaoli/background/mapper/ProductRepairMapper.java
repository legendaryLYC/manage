package com.xiongantaoli.background.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.xiongantaoli.background.entity.ProductRepair;

public interface ProductRepairMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductRepair record);

    int insertSelective(ProductRepair record);

    ProductRepair selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductRepair record);

    int updateByPrimaryKey(ProductRepair record);
    
    /**
     * 查询所有产品维修种类
     * @return
     */
    List<ProductRepair> selectAll();
    
    boolean updateByid(ProductRepair record);
    
    boolean deletebyid(Long id);
    
    /**
     * 根据code查询维修项目分类信息
     * @author 王喜壮
     * @return
     */
    ProductRepair selectByCode(Long code);
    
    /**
     * 根据name查询维修项目分类列表
     * @author 王喜壮
     * @return
     */
    List<ProductRepair> select(String name);
    
    /**
     * 插入维修项目
     * @author 王喜壮
     * @return
     */
	boolean insertproductRepair(ProductRepair productRepair);
	
	/**
	 * 通过code查询对应提成
	 * @param code
	 * @return
	 */
	BigDecimal selectCountByCode(Integer code);
}