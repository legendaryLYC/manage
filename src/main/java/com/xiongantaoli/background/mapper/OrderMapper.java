package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.form.OrderInfoForm;

/**
 * 订单相关mapper
 * @author 孟晓冬
 */
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);
    
    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    /**
     * 按条件筛选采购列表与字典信息
     * !!!!不建议使用, 请使用下边的selectOrdersByStatesSelective
     * @author 孟晓冬
     */
    List<OrderInfoForm> selectOderInfoFormsSelective(Order order);
    
    /**
     * 按主键查找订单项项与字典信息
     * @param id
     * @author 李永成
     * @return
     */
    OrderInfoForm selectOderInfoFormByPrimaryKey(Long id);
    
    /**
     * 修改订单状态
     * @param state
     * @return
     */
	int updateState(@Param("state")int state,@Param("id") Long id);
	
	
	/**
           * 查询所有收购的手机订单
     * @param id
     * state 筛选条件 0 代表所有的
     * @return	
     */
    List<Order> selectAll(@Param(value="beginTime")String beginTime, @Param(value="endTime")String endTime);
    
    /**
     *	 查询所有订单并显示
     * @author 周天
     * @return OrderInfoForm
     * 
     */
    List<OrderInfoForm> selectOrderAll(Order order);

    /**
     * 多状态筛选订单与字典类信息
     * @param states
     * @return
     */
    List<OrderInfoForm> selectOrdersByStatesSelective(
    		@Param(value="order")Order order,
    		@Param(value="states")Integer[] states);

}