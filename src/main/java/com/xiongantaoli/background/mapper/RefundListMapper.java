package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.RefundList;

public interface RefundListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RefundList record);
    
    Boolean insertRefundList (RefundList record);
    

    int insertSelective(RefundList record);

    RefundList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RefundList record);

    int updateByPrimaryKey(RefundList record);
    
    /**
	 * 查询所有
	 * @param state
	 * @return
	 */
	List<RefundList> selectAll(@Param(value="beginTime")String beginTime, @Param(value="endTime")String endTime);
}