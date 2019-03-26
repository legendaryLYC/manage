package com.xiongantaoli.background.mapper;

import com.xiongantaoli.background.entity.OrderNew;
import com.xiongantaoli.background.form.NewOrderForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderNewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderNew record);

    int insertSelective(OrderNew record);

    OrderNew selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderNew record);

    int updateByPrimaryKey(OrderNew record);

     public List<OrderNew> selectOrder(NewOrderForm newOrderForm);

     public List<OrderNew> selcetAllOrder();
}