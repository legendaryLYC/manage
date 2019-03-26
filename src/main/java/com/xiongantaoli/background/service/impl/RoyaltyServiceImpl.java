package com.xiongantaoli.background.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.xiongantaoli.background.entity.DrawbackList;
import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.entity.PriceToMateriel;
import com.xiongantaoli.background.entity.ProductInfo;
import com.xiongantaoli.background.entity.ProductRepair;
import com.xiongantaoli.background.entity.RefundList;
import com.xiongantaoli.background.entity.RepairList;
import com.xiongantaoli.background.entity.RoyaltyModel;
import com.xiongantaoli.background.entity.SoldOut;
import com.xiongantaoli.background.mapper.DrawbackListMapper;
import com.xiongantaoli.background.mapper.OrderMapper;
import com.xiongantaoli.background.mapper.ProductInfoMapper;
import com.xiongantaoli.background.mapper.ProductRepairMapper;
import com.xiongantaoli.background.mapper.QualityListMapper;
import com.xiongantaoli.background.mapper.QualityRoyaltyMapper;
import com.xiongantaoli.background.mapper.RefundListMapper;
import com.xiongantaoli.background.mapper.RepairListMapper;
import com.xiongantaoli.background.mapper.RepairManagementMapper;
import com.xiongantaoli.background.mapper.SoldOutMapper;
import com.xiongantaoli.background.service.RoyaltyService;

/**
 * 统计分析计算service类
 * @author 黄润轩
 *
 */
@Service("royaltyService")
public class RoyaltyServiceImpl implements RoyaltyService{

	
	@Autowired 
	private SoldOutMapper soldOutMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private RepairManagementMapper repairManagementMapper;
	@Autowired
	private ProductRepairMapper productRepairMapper;
	@Autowired
	private RepairListMapper repairListMapper;
	@Autowired
	private QualityListMapper qualityListMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;
	@Autowired
	private QualityRoyaltyMapper qualityRoyaltyMapper;
	@Autowired
	private DrawbackListMapper drawbackListMapper;
	
	@Autowired
	private RefundListMapper refundListMapper;
	
	/**
	 * 参数state 代表：
	 * 1  全部
	 * 2 当前年
	 * 3 当前月
	 * 4 当前周
	 */
	@Override
	public RoyaltyModel caculate(String beginTime, String endTime) {
		
		RoyaltyModel royaltyModel = new RoyaltyModel();	// 用来存储结果集
		List<SoldOut> soldOutList = soldOutMapper.selectAll(beginTime,endTime);	// 查询所有已售出的交易
		List<RepairList> repairList = repairListMapper.selectAll(beginTime,endTime);	// 查询所有维修过的产品
		List<Order> orderList = orderMapper.selectAll(beginTime,endTime);	// 查询所有订单
		List<DrawbackList> drawbackList = drawbackListMapper.selectAll(beginTime,endTime);	// 所有退回物流费用
		List<RefundList> refundList = refundListMapper.selectAll(beginTime,endTime);	// 所有退回物流费用
		int count = qualityListMapper.selectCount(beginTime,endTime);	// 质检通过的手机的数量
		BigDecimal n2 =  new BigDecimal("0");	// 提成比率
		BigDecimal n3 = new BigDecimal("0");	// 基础提成
		
		//计算总成交金额
		for(SoldOut soldOut : soldOutList) {
			Order order = orderMapper.selectByPrimaryKey(soldOut.getPurchaseId());
			if(order.getOrderState() == 7) {
				royaltyModel.setTotal(order.getQualityPrice().subtract(order.getPartPrice()).
						add(royaltyModel.getTotal())); 
			}else if(order.getOrderState() == 4) {
				royaltyModel.setTotal(order.getQualityPrice().add(royaltyModel.getTotal())); 
			} 
			if(order.getOrderState() == 4 || order.getOrderState() == 7) {
				ProductInfo product = productInfoMapper.selectByPrimaryKey(order.getProductId());
				n2 = product.getSaleRoyalty();
				n3 = product.getBaseRoyalty();
				royaltyModel.setRoyalty(royaltyModel.getRoyalty().add(new BigDecimal(
						(order.getPurchasePrice().subtract(order.getFinalPurchase())).multiply(n2).add(n3).toString())));
			}
		//计算成本
			if(soldOut.getCarriagePrice() != null) {
				royaltyModel.setBase(royaltyModel.getBase().add(soldOut.getCarriagePrice()));
			}
		}
		
		//计算成本
		for(Order o : orderList) {
			if(o.getOrderState() != 10 && o.getOrderState() != 0) {
				if(o.getFinalPurchase() != null) {
					royaltyModel.setBase(royaltyModel.getBase().add(o.getFinalPurchase()));
				}
			}
		}
		for(DrawbackList d : drawbackList) {
			if(d.getCarriagePrice() != null) {
				royaltyModel.setBase(royaltyModel.getBase().add(d.getCarriagePrice()));
			}
		}
		for(RefundList r : refundList) {
			if(r.getCarriagePrice() != null) {
				royaltyModel.setBase(royaltyModel.getBase().add(r.getCarriagePrice()));
			}
		}
		
		for(RepairList r : repairList) {

			String[] arr = r.getRepairStyle().split(",");
			for(String str : arr)
				royaltyModel.setRoyalty(royaltyModel.getRoyalty().add(productRepairMapper.selectCountByCode(Integer.parseInt(str))));

			List<PriceToMateriel> priceToMaterielList= repairManagementMapper.selectByPurchaseId(r.getOrderId());
			for(PriceToMateriel priceToMateriel : priceToMaterielList) {
				royaltyModel.setBase(royaltyModel.getBase().add(priceToMateriel.getNum().multiply(priceToMateriel.getPrice())));
			}
		}
		BigDecimal qualityPrice = qualityRoyaltyMapper.selectQualityPrice();	// 质检的基础提成
		royaltyModel.setRoyalty(royaltyModel.getRoyalty().add(qualityPrice.multiply(
				new BigDecimal(count))));
		
		return royaltyModel;
	}

}
