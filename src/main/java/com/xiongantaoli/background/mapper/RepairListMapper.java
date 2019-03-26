package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.RepairList;

public interface RepairListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RepairList record);

    int insertSelective(RepairList record);

    RepairList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RepairList record);

    int updateByPrimaryKey(RepairList record);
    
    /**
     * 查询当前表所有信息
     * @param id
     * state 筛选条件 0 代表所有的
     * @return
     */
    List<RepairList> selectAll(@Param(value="beginTime")String beginTime, @Param(value="endTime")String endTime);

	boolean deleteByOrderId(Long key);
	
    
    /**
         * 查询当前表中某人的userid
     * @param orderId
     * 
     * @return
     */
	Long selectUserId(Long orderId);
	
    /**
	    * 查询当前表中维修过的订单的维修类型
	 * @param orderId
	 * 
	 * @return
	 */
	RepairList selectByOrderId(Long orderId);
    
}