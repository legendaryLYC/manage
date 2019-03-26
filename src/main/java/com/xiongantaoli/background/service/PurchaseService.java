package com.xiongantaoli.background.service;

import java.util.List;
import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.form.OrderInfoForm;
import com.xiongantaoli.background.form.ProductInfoForm;

/**
 * 
 * @author 孟晓冬
 *采购相关方法
 */
public interface PurchaseService {
	
	/**
	 * 按条件查询所有订单列表，并计算价格，订单管理
	 * @author 周天
	 * @param 
	 * @return
	 */
	List<OrderInfoForm> getOrdersByStates(Order order , Integer... states);

	/**
	 * 按条件查询采购列表
	 * @param 
	 * @return
	 */
	List<OrderInfoForm> selectOrdersSelective(Order order,Integer... states);
	/**
	 * 登记采购记录
	 * @param request
	 * @return Boolean
	*/
	Boolean insertOrder(Order Order);
	 
	/** 
	 * @author 李永成
	*调用晓东的接口，查出良品，退款，
	*已售出，交易成功的商品返回给前端
	*/
	List<OrderInfoForm> selectsoldselective(Order order);
	
	/**
	 * 修改采购记录(不更新为null的)
	 * @param request
	 * @return
	 */
	Boolean updateOrder(Order order);
	
	/**
	 * 修改采购记录
	 * @param request
	 * @return
	 */
	Boolean updateOrderAll(Order order);
	
	/**
	 * 订单删除
	 * @param request
	 */
	Boolean deleteOrder(Long id);
	
	/**
	 * 订单删除
	 * @param request
	 */
	OrderInfoForm selectOrderInfoFormByPrimaryKey(Long id);
	
	/**
	 * 更新订单状态
	 * @param
	 * @author 卜勇峰
	 */
	int updateState(int state,Long id);

	/**
	 * 由字典选择查询字典记录类
	 * @param brand
	 * @param model
	 * @param standard
	 * @return
	 */
	List<ProductInfoForm> getProductInfoForms(Integer brand, Integer model, Integer standard);
	
	/**
	 * 查询所有订单
	 * 
	 * @param order
	 * @author 周天
	 */
	List<OrderInfoForm> selectAllOrders(Order order);
	
	/**
	 * 多个状态or筛选订单和字典
	 * @param order
	 * @param states
	 * @return List<OrderInfoForm>
	 */
	List<OrderInfoForm> getOrdersByStatesSelective(Order order , Integer... states);
	
	/**
	 * 循环填充配件字典和外观字典用
	 * 把传入的list循环每个的Attachment去字典查找后再放回List
	 * @param result
	 * @return List<OrderInfoForm>
	 */
	List<OrderInfoForm> fillAttachmentsAndApperances(List<OrderInfoForm> result);
}
