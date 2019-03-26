package com.xiongantaoli.background.service;


import com.xiongantaoli.background.entity.OrderNew;
import com.xiongantaoli.background.form.NewOrderForm;
import com.xiongantaoli.background.form.ProductInfoForm;

import java.io.File;
import java.util.List;

/**
 * 新订单页面逻辑的service接口
 * 时间:2019/1/10
 * @author 周天
 * 最后修改时间:2019/1/10
 * 注意事项:无
 */
public interface OrderNewService {

    public List<OrderNew> selectOrder(NewOrderForm newOrderForm);

    public int updateOrder(OrderNew record);

    public int deleteNewOrder(Long id);

    public int addNewOrder(OrderNew record);

    public File downloadPartExcel( Long[] ids);
}
