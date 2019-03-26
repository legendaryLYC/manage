package com.xiongantaoli.background.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.mapper.ProductStateMapper;
import com.xiongantaoli.background.service.ProductStateService;

/**
 * 订单状态字典实现类
 * @author 卜勇峰
 */
@Service
public class ProductStateServiceImpl implements ProductStateService{
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);
	
	@Autowired
	private ProductStateMapper stateMapper;

	@Override
	public int selectCode(String name) {
		// TODO Auto-generated method stub
		int code = 0;
		code = stateMapper.selectCode(name).getCode();
		logger.info("==========订单状态 " + name + "对应的编号为 ：" + code + "============");
		return code;
	}

}
