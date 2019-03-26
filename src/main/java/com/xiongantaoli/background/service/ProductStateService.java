package com.xiongantaoli.background.service;

import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.ProductState;

/**
 * 订单状态字典类
 * @author 卜勇峰
 */

public interface ProductStateService {
	
	/**
	 * 按名字查询订单状态编号
	 * @param 
	 * @return
	 */
	int selectCode(String name);
}
