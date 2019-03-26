package com.xiongantaoli.background.mapper;

import com.xiongantaoli.background.entity.SoldOut;

public interface RoyaltyMapper {
	
	/**
	 * 通过id查询销售金额
	 * @param id
	 * @return SoldOut
	 */
	SoldOut selectByPrimaryKey(Long id);
}
