package com.xiongantaoli.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiongantaoli.background.entity.QualityList;

/**
 * 质检相关dao层
 * @author 孟晓冬
 */
public interface QualityListMapper {
    int insert(QualityList record);

    int insertSelective(QualityList record);
    
    /**
     * 查询当前表中数据的数量
     * @param id
     * state 筛选条件 0 代表所有的
     * @return
     */
    int selectCount(@Param(value="beginTime")String beginTime, @Param(value="endTime")String endTime);
    
    /**
         * 查询当前表中某人的userid
     * @param orderId
     * 
     * @return
     */
    List<Long> selectUserId(Long orderId);
    
    /**
     * 查询当前表中是否有该id
     * 
     * @param id
     * state 筛选条件 0 代表所有的
     * @return
     */
    int selectByOrderId(Long orderId);
    
    
//    /**
//     * 根据订单号删除质检记录
//     * @return
//     */
//    int deleteByOrderId(@Param(value="orderId")Long orderId);
}