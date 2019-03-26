package com.xiongantaoli.background.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.Materiel;
import com.xiongantaoli.background.mapper.MaterielMapper;
import com.xiongantaoli.background.security.controller.ResourceController;
import com.xiongantaoli.background.service.MaterielService;

/**
 * 物料管理实现类
 * @author 卜勇峰
 *
 */

@Service
public class MaterielImpl implements MaterielService{
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private MaterielMapper mMapper; //物料数据操作类

	/**
	 * 根据编码更新物料信息
	 * @param materiel
	 * @return
	 */
	@Override
	public int updateByCode(Materiel materiel) {
		// TODO Auto-generated method stub
		logger.info("======== 进入updateByCode方法,参数materiel : " + materiel.toString() + " =========");
		int key = mMapper.updateByCode(materiel);
		logger.info("========更新结果 ： " + key + " =========");
		return key;
	}

	/**
	 * 根据编码删除物料信息
	 * @param code
	 * @return
	 */
	@Override
	public int deleteByCode(int code) {
		// TODO Auto-generated method stub
		logger.info("======== 进入deleteByCode方法,参数code : " + code+ " =========");
		int key=mMapper.deleteByCode(code);
		logger.info("======== 删除结果 : " + key + " ==========");
		return key;
	}

	/**
	 *增加物料信息
	 * @param materiel
	 * @return
	 */
	@Override
	public int insert(Materiel materiel) {
		// TODO Auto-generated method stub
		logger.info("======== 进入insert方法,参数名materiel : " + materiel.toString() + " =========");
		int key = mMapper.insert(materiel);
		logger.info("========添加结果 : " + key + " ==========");
		return key;
	}

	/**
	 *得到物料数量
	 * @return
	 */
	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		Long num = mMapper.getCount();
		logger.info("======查询物料数量 : " + num + "===========");
		return num;
	}

	/**
	 *根据名字查询物料
	 *@param name
	 * @return
	 */
	@Override
	public List<Materiel> select(String name) {
		// TODO Auto-generated method stub
		logger.info("======== 进入select方法,参数名name : "+name+" =========");
		List<Materiel> materiels = new ArrayList<>();
		materiels=mMapper.select(name);
		try {
			logger.info("======== 查询到的物料信息 : " + materiels.toString() + " ==========");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return materiels;
	}
	@Override
	public List<Materiel> selectByName(String name) {
		// TODO Auto-generated method stub
		logger.info("======== 进入select方法,参数名name : "+name+" =========");
		List<Materiel> materiels = new ArrayList<>();
		materiels=mMapper.selectByName(name);
		try {
			logger.info("======== 查询到的物料信息 : " + materiels.toString() + " ==========");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return materiels;
	}

	/**
	 *查询最后一条物料信息的编号
	 *@param name
	 * @return
	 */
	@Override
	public Integer selectLast() {
		// TODO Auto-generated method stub
		Materiel materiel = mMapper.selectLast();
		logger.info("MaterielImpl下的selectLast方法查出的materiel : " + materiel);
		Integer code=0;
		code = materiel == null ? 1000:materiel.getCode(); 
		logger.info("返回编号为 : " +code);
		return code;
	}

	/**
	 *根据编码查询物料信息
	 *@param name
	 * @return
	 */
	@Override
	public Materiel selectByCode(int code) {
		// TODO Auto-generated method stub
		return mMapper.selectByPrimaryKey(code);
	}
		

}
