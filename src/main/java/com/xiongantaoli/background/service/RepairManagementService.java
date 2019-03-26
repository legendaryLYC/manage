package com.xiongantaoli.background.service;

import java.util.Date;
import java.util.List;

import com.xiongantaoli.background.entity.Materiel;
import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.entity.RepairManagement;
import com.xiongantaoli.background.form.OrderInfoForm;
import com.xiongantaoli.background.form.RepairShow;

/**
 * 维修管理接口类
 * @author 卜勇峰
 *
 */

public interface RepairManagementService {

	/**
	 * 检索对应订单id的维修管理信息
	 * @param id
	 * @return
	 */
	List<RepairShow> selectPart(Long id);
	
	/**
	 * 判断该订单当前是否录入该物料,如果有，返回该物料数量
	 * @param materielId
	 * @return
	 */
	int selectIsExist(int materielId,Long purchaseId);

	/**
	 * 插入当前订单所需物料
	 * @param materielId
	 * @return
	 */
	int insert(RepairManagement repairM);

	/**
	 * 根据主键更新
	 * @param repairM
	 * @return
	 */
	int updateByPrimaryKey(RepairManagement repairM);
	
	/**
	 * 根据主键搜索
	 */
	RepairManagement selectByPrimaryKey(Long id);
	
	
	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryId(Long id);
	
	
}
