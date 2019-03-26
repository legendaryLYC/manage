package com.xiongantaoli.background.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.RepairList;
import com.xiongantaoli.background.mapper.RepairListMapper;
import com.xiongantaoli.background.service.RepairListService;

@Service
public class RepairListServiceImpl implements RepairListService {

	@Autowired
	private RepairListMapper repairListMapper; //最终维修订单操作类
	
	@Override
	public int insert(RepairList repairList) {
		// TODO Auto-generated method stub
		return repairListMapper.insertSelective(repairList);
	}

}
