package com.xiongantaoli.background.service;

import java.math.BigDecimal;

import com.xiongantaoli.background.entity.DrawbackList;
import com.xiongantaoli.background.entity.Order;

/**
 * 销售用服务
 * @author 孟晓冬
 *
 */
public interface SalesSerivce {

	/**
	 * 退货部分金额
	 * @author 孟晓冬
	 */
	Boolean refundPartly(Long id, BigDecimal money);
	
	/**
	 * 退货全部金额
	 * @param id
	 * @return
	 * @author 孟晓冬
	 */
	Boolean refundAll(Long id);

	Order selectByPrimaryKey(Long id);

	boolean updatePriceByid(Order sale);

	boolean insertDrawback(DrawbackList drawback);
}
