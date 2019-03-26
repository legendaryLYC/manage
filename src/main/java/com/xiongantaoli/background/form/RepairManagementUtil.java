package com.xiongantaoli.background.form;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.Materiel;
import com.xiongantaoli.background.entity.RepairList;
import com.xiongantaoli.background.entity.RepairManagement;
import com.xiongantaoli.background.entity.SysUser;
import com.xiongantaoli.background.mapper.SysUserMapper;
import com.xiongantaoli.background.model.ResultData;
import com.xiongantaoli.background.security.controller.IndexController;
import com.xiongantaoli.background.security.controller.ResourceController;
import com.xiongantaoli.background.service.MaterielService;
import com.xiongantaoli.background.service.ProductStateService;
import com.xiongantaoli.background.service.PurchaseService;
import com.xiongantaoli.background.service.RepairListService;
import com.xiongantaoli.background.service.RepairManagementService;
import com.xiongantaoli.background.util.Utility;

/**
 * RepairManagementController工具类
 * 
 * @author 卜勇峰
 */

@Service
public class RepairManagementUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private RepairManagementService repairManageService; //调用RepairManagementService 管理接口（具体内容注释在接口里）	
	@Autowired
	private MaterielService materielService; //调用materiel管理接口（具体内容注释在接口里）
	@Autowired
	private ProductStateService stateService; //调用ProductStateService 管理接口（具体内容注释在接口里）
	@Autowired
	private PurchaseService purchaseServices; //调用purchaseService管理接口（具体内容注释在接口里）
	@Autowired
	private RepairListService repairListService; //调用RepairListService接口
	@Autowired
	private SysUserMapper userMapper;
	
	/**
	 * 判断该物料是否已被录入
	 * @Param repairM
	 * @return
	 * @throws OperatorException 
	 */
	public void judgeHaveMateriel(RepairManagement repairM,ResultData result) throws OperatorException {
		int key = 0;
		key = repairManageService.selectIsExist(repairM.getMaterielId(),repairM.getPurchaseId());
		if(key != 0) {
			result = ResultData.setCodeAndMessage(0, "该物料未被录入", "该物料已存在！");
			throw new OperatorException(result);
		}
		
	}
	
	/**
	 * 判断是否有库存，有就更新
	 * @Param repairM
	 * @return
	 * @throws OperatorException 
	 */
	public void updateMaterielNum(RepairManagement repairM,ResultData result) throws OperatorException {
		
		int key; //当前数据库该维修id单对应的维修物料的数量
		int num; //物料库存更新值
		
		Materiel materiel = materielService.selectByCode(repairM.getMaterielId());
		key = repairManageService.selectIsExist(repairM.getMaterielId(),repairM.getPurchaseId());
		//如果当前数据库的值大于要更新的值，则将物料库存增加，如果小于，则减少
		if(key > repairM.getMaterielNum()) {
			num = materiel.getMaterielNum() + key - repairM.getMaterielNum();
		}else {
			num = materiel.getMaterielNum() - repairM.getMaterielNum();
			if(num < 0) {
				result = ResultData.setCodeAndMessage(0, "有足够库存", "所需物料不够！");
				throw new OperatorException(result);
			}
		}

		//更新该物料库存数量
		materiel.setMaterielNum(num);
		key = materielService.updateByCode(materiel);
		if(key == 0) {
			result = ResultData.setCodeAndMessage(0, "更新成功", "更新失败！");
			throw new OperatorException(result);
		}
	}
	
	/**
	 * 在字典中查询对应名字对应的编号
	 * @param name
	 * @return
	 */
	public int getCodeByName(String name) {	
		int code = stateService.selectCode(name);
		logger.info(name + "状态对应的维修编号为 " + code + " ==============");
		return code;
	}
	
	/**
	 * 进入良品库
	 * @param id,repairStyle
	 * @return
	 */
	public int inGoodStore(Long id , String repairStyle , int nit) {
		int key=0,code=0;
		//userId
		Long user_id = getUserId();
		//良品名
		String goodState = "良品";
		//当前sql对象时间
		Date currentDate = currentDate = new Date(System.currentTimeMillis());	;
		//搜索订单良品状态对应的编号
		code=stateService.selectCode(goodState);
		
		//更新订单状态
		key=purchaseServices.updateState(code, id);
		logger.info("========= id : " + id + "的订单更新状态为 : " + key);
		
		//如果repairStyle = -1代表是在维修管理页面直接点击的进入良品库，此时未选择维修类别而是直接进入的良品库，所以并不需要插入维修记录
		if(nit != -1) {
			RepairList repairList=new RepairList(user_id,id,repairStyle,currentDate);
			logger.info("========= 插入最终维修记录 : " + repairList + "===========");
			key = repairListService.insert(repairList);
		}
		return key;
	}
	
	/**
	 * 删除商品
	 */
	public int delete(Long id) {
		int key;
		
		//搜索当前编号的物料信息
		RepairManagement repairM = repairManageService.selectByPrimaryKey(id);
		
		//当前物料库存增加
		Materiel materiel = materielService.selectByCode(repairM.getMaterielId());
		materiel.setMaterielNum(materiel.getMaterielNum() + repairM.getMaterielNum());
		key = materielService.updateByCode(materiel);
		if(key == 1){
			//删除当前编号维修管理订单
			key = repairManageService.deleteByPrimaryId(id);
		}
		logger.info("当前id" + id + "订单删除情况：" + key);
		return key;
	}
	
	/**
	 * 获取userId
	 */
	public Long getUserId() {
		String loginUserName = Utility.getCurrentUsername();
		SysUser loginUser = userMapper.selectByUsername(loginUserName);
		return loginUser.getId();
	}
}
