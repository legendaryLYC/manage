package com.xiongantaoli.background.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.QualityList;
import com.xiongantaoli.background.mapper.QualityListMapper;
import com.xiongantaoli.background.service.QualityService;

/**
 * 质检用service
 * @author 孟晓冬
 */
@Service
public class QualityServiceImpl implements QualityService {

	@Autowired
	private QualityListMapper qualityListMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(QualityServiceImpl.class);
	
	/**
	 * 质检员操作时记录质检记录
	 * @param quality
	 * @return
	 * @author 孟晓冬
	 */
	@Override
	public Boolean insertQualityRecord(QualityList quality) {
		//记录插入结果
		int result = 0;
		//记录操作时间
	    quality.setTime(new Date(System.currentTimeMillis()));
		//把记录插入到质检记录表
	    try {
	    	result = qualityListMapper.insertSelective(quality);
		} catch (Exception e) {
			logger.error("插入/删除质检记录出错!"+e.getMessage());
			return false;
		}
	    //检查插入是否成功
	    if(result != 1) {
	    	logger.error("插入质检记录失败!");
	    	return false;
	    }
		return true;
	}

	@Override
	public int selectByOrderId(Long orderId) {
		int flag = qualityListMapper.selectByOrderId(orderId);
		if(flag > 0){
			return 1;	
		}
		else {
			return 0;
		}
	}

}
