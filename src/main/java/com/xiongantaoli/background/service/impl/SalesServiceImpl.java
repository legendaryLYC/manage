package com.xiongantaoli.background.service.impl;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.DrawbackList;
import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.mapper.DrawbackListMapper;
import com.xiongantaoli.background.mapper.OrderMapper;
import com.xiongantaoli.background.service.SalesSerivce;

/**
 * 销售用服务
 * @author 孟晓冬
 */
@Service
public class SalesServiceImpl implements SalesSerivce{
	
	@Autowired 
	private OrderMapper orderMapper;
	@Autowired
	private DrawbackListMapper operateDrawback;
	
	private static final Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);
	
	/**
	 * 退货部分金额
	 * @author 孟晓冬
	 */
	@Override
	public Boolean refundPartly(Long id, BigDecimal money) {
		//先获得要退款的对象,获得质检价格
		Order record = orderMapper.selectByPrimaryKey(id);
		record.setId(id);
		//检查退款金额是否不大于质检价格
		if(money.compareTo(record.getQualityPrice()) == 1) {
			logger.info("申请退款的金额大于质检价格, 已拒绝");
			return false;
		}
		record.setPartPrice(money);
		//部分退款状态
		record.setOrderState(7);
		//记录更新操作结果
		int resultCount = 0;
		try {
			resultCount = orderMapper.updateByPrimaryKeySelective(record);
		}catch (Exception e) {
			logger.error("部分退款更新到数据库失败!"+e.getMessage());
			return false;
		}
		if(resultCount != 1) {
			return false;
		}
		return true;
	}

	/**
	 * 退货全部金额
	 * @param id
	 * @return
	 * @author 孟晓冬
	 */
	@Override
	public Boolean refundAll(Long id) {
		
		//存储该id对应的order对象
		Order order = null ;
		//记录更新操作结果
		int resultCount = 0;
		try {
			//先获得要退款的对象,获得退款金额
			order = orderMapper.selectByPrimaryKey(id);
			//全部退款状态
			order.setOrderState(6);
			//检查要退的钱数是否为空, 不为空时set到order对象
			if(order.getQualityPrice() != null) {
				order.setPartPrice(order.getQualityPrice());
			}else {
				return false;
			}
			resultCount = orderMapper.updateByPrimaryKeySelective(order);
		} catch (Exception e) {
			logger.error("全部退款获取数据库id对应对象失败!"+e.getMessage());
			return false;
		}
		//检查更新结果是否正确
		if(resultCount != 1) {
			return false;
		}
		return true;
	}

	@Override
	public Order selectByPrimaryKey(Long id) {
		Order order = orderMapper.selectByPrimaryKey(id);
		return order;
	}

	/**
	 * 更改良品的成交价格
	 * @author 李永成
	 */
	@Override
	public boolean updatePriceByid(Order sale) {
		boolean flag = false;
		int result = orderMapper.updateByPrimaryKeySelective(sale);
		if(result==1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean insertDrawback(DrawbackList drawback) {
		int flag = operateDrawback.insertSelective(drawback);
		if(flag==1) {
			return true;
		}
		else return false;
	}
}
