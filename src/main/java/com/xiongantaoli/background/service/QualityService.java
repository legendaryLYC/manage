package com.xiongantaoli.background.service;

import com.xiongantaoli.background.entity.QualityList;

/**
 * 质检用service
 * @author 孟晓冬
 */
public interface QualityService {
	
	/**
	 * 质检员操作时记录质检记录
	 * @param quality
	 * @return
	 */
	Boolean insertQualityRecord(QualityList quality);
	
	/**
	 * 判断质检记录是否存在，存在不插入，存在插入
	 * @param quality
	 * @return
	 */
	int selectByOrderId(Long orderId);
}
