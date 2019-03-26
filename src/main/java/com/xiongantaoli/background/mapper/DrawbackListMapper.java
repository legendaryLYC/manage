package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.DrawbackList;

public interface DrawbackListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DrawbackList record);

    int insertSelective(DrawbackList record);

    DrawbackList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DrawbackList record);

    int updateByPrimaryKey(DrawbackList record);

	boolean deleteByPurchaseId(Long key);
	
	/**
	 * 查询所有
	 * @param state
	 * @return
	 */
	List<DrawbackList> selectAll(@Param(value="beginTime")String beginTime, @Param(value="endTime")String endTime);
}