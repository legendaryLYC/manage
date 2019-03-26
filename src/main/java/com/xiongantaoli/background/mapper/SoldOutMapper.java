package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.entity.SoldOut;

public interface SoldOutMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SoldOut record);

    int insertSelective(SoldOut record);

    SoldOut selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SoldOut record);

    int updateByPrimaryKey(SoldOut record);
    
    
    /**
     * 查询所有已经销售的产品
     * @param id
     * state 筛选条件 0 代表所有的
     * @return
     */
    List<SoldOut> selectAll(@Param(value="beginTime")String beginTime, @Param(value="endTime")String endTime);

	boolean deleteByPurchaseId(Long key);
	
	/**
	    * 查询当前表中某人的userid
	 * @param orderId
	 * 
	 * @return
	 */
	Long selectUserId(Long orderId);
    
}