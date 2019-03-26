package com.xiongantaoli.background.service;

import java.util.List;

import com.xiongantaoli.background.entity.Materiel;
import com.xiongantaoli.background.entity.SysRole;

/**
 * 维修管理类
 * @author 卜勇峰
 *
 */


public interface MaterielService {
	
	/**
	 *查询物料具体信息
	 *@param name
	 * @return
	 */
	List<Materiel> select(String name);
	
	/**
	 * 根据物料编号更新物料信息
	 * @param materiel
	 * @return
	 */
	int updateByCode(Materiel materiel);
	
	/**
	 * 根据物料编号删除物料信息
	 * @param code
	 * @return
	 */
	int deleteByCode(int code);
	
	/**
	 * 增加物料
	 * @param record
	 * @return
	 */
	int insert(Materiel record);
	
	/**
	 * 查询物料数量
	 * @param name
	 * @return
	 */
	Long getCount();
	
	/**
	 * 查询最后一条数据
	 * @param name
	 * @return
	 */
	Integer selectLast();

	/**
	 * 根据编码查物料
	 * @param code
	 * @return
	 */
	Materiel selectByCode(int code);
	
	/**
	 *查询物料具体信息
	 *@param name
	 * @return
	 */
	List<Materiel> selectByName(String name);
	
}
