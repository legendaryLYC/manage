package com.xiongantaoli.background.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.entity.RepairManagement;
import com.xiongantaoli.background.form.OrderInfoForm;
import com.xiongantaoli.background.form.RepairShow;
import com.xiongantaoli.background.mapper.OrderMapper;
import com.xiongantaoli.background.mapper.RepairManagementMapper;
import com.xiongantaoli.background.security.controller.ResourceController;
import com.xiongantaoli.background.service.RepairManagementService;



/**
 * 维修管理实现类
 * @author 卜勇峰
 *
 */

@Service
public class RepairManagementServiceImpl implements RepairManagementService{
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private RepairManagementMapper repairManageMapper; //维修管理数据操作操作类

	@Override
	public List<RepairShow> selectPart(Long id) {
		// TODO Auto-generated method stub
		List<RepairShow> list=repairManageMapper.selectPart(id);
		logger.info("======= 查询出的维修管理数据 : " + list.toString() + " ============");
		return list;
	}

	@Override
	public int selectIsExist(int materielId, Long purchaseId) {
		// TODO Auto-generated method stub
		RepairManagement repairM= repairManageMapper.selectIsExist(materielId,purchaseId);
		int num = repairM == null ? 0 : repairM.getMaterielNum();
		logger.info("=========== 查询当前订单是否存在该物料 : " + num + " =============");
		return num;
	}

	@Override
	public int insert(RepairManagement repairM) {
		// TODO Auto-generated method stub
		return repairManageMapper.insert(repairM);
	}

	@Override
	public int updateByPrimaryKey(RepairManagement repairM) {
		// TODO Auto-generated method stub
		return repairManageMapper.updateByPrimaryKeySelective(repairM);
	}

	@Override
	public RepairManagement selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		RepairManagement repairM = repairManageMapper.selectByPrimaryKey(id);
		logger.info("=========== 根据主键" + id +"搜索出repairManagement : " + repairM.toString() + " =============");
		return repairM;
	}

	@Override
	public int deleteByPrimaryId(Long id) {
		// TODO Auto-generated method stub
		return repairManageMapper.deleteByPrimaryKey(id);
	}
	
	
	
	

}
