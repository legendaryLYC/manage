package com.xiongantaoli.background.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.Order;
import com.xiongantaoli.background.entity.ProductApperance;
import com.xiongantaoli.background.entity.ProductAttachment;
import com.xiongantaoli.background.form.OrderInfoForm;
import com.xiongantaoli.background.form.ProductInfoForm;
import com.xiongantaoli.background.form.RepairShow;
import com.xiongantaoli.background.mapper.OrderMapper;
import com.xiongantaoli.background.mapper.ProductApperanceMapper;
import com.xiongantaoli.background.mapper.ProductAttachmentMapper;
import com.xiongantaoli.background.mapper.ProductInfoMapper;
import com.xiongantaoli.background.mapper.RepairManagementMapper;
import com.xiongantaoli.background.service.PurchaseService;;

/**
 * @author 孟晓冬
 *采购相关方法
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

	@Autowired
	private OrderMapper orderMapper; // 订单类接口
	@Autowired
	private ProductInfoMapper productInfoMapper; 
	@Autowired
	private RepairManagementMapper repairManageMapper; //维修管理数据操作操作类  李永成调用计算出成本价格
	@Autowired
	private ProductAttachmentMapper productAttachmentMapper;
	@Autowired
	private ProductApperanceMapper productApperanceMapper;
	
	/**
	 * 按条件查询采购列表
	 * @param 
	 * @return
	 * @author 孟晓冬
	 */
	@Override
	public List<OrderInfoForm> selectOrdersSelective(Order order,Integer... states) {
		List<OrderInfoForm> result = null;
		//去除空串,防止""查库报错
		order.setOrderNum("".equals(order.getOrderNum()) ? null : order.getOrderNum());
		order.setOperator("".equals(order.getOperator()) ? null : order.getOperator());
		order.setSerialNum("".equals(order.getSerialNum()) ? null : order.getSerialNum());
		
		try {
			result=orderMapper.selectOrdersByStatesSelective(order,states); 
		} catch (Exception e) {
			logger.error("条件筛选采购记录失败!"+e.getMessage());
		}
		//填充配件字典
		result = fillAttachmentsAndApperances(result);
		return result;
	}
	
	/**
	 * 登记采购记录
	 * @param request
	 * @return Boolean
	 * @author 孟晓冬
	*/
	@Override
	public Boolean insertOrder(Order order) {
		//记录结果
		int result = 0;
		//插入数据库
		try {
			result = orderMapper.insert(order);
		} catch (Exception e) {
			logger.error("采购登记插入失败: "+e.getMessage());
			return false;
		}
		if(result <= 0) {
			return false;
		}
		else{
			return true;
		}
	}

	/**
	 * 修改采购记录(不更新为null的)
	 * @param request
	 * @return
	 * @author 孟晓冬
	 */
	@Override
	public Boolean updateOrder(Order order) {
		//记录结果
		int result = 0;
		//如果采购id为空则返回更新失败
		if(order.getId() == null) {
			logger.warn("要更新id为空的采购记录, 失败!");
			return false;
		}
		//更新数据库
		try {
			result = orderMapper.updateByPrimaryKeySelective(order);
		} catch (Exception e) {
			logger.warn("更新采购记录失败!"+e.getMessage());
		}
		if(result <= 0)
			return false;
		else
			return true;
	}

	/**
	 * 修改采购记录
	 * @param request
	 * @return
	 * @author 孟晓冬
	 */
	@Override
	public Boolean updateOrderAll(Order order) {
		//记录结果
		int result = 0;
		//如果采购id为空则返回更新失败
		if(order.getId() == null) {
			logger.warn("要更新id为空的采购记录, 失败!");
			return false;
		}
		//更新数据库
		try {
			result = orderMapper.updateByPrimaryKey(order);
		} catch (Exception e) {
			logger.warn("更新采购记录失败!"+e.getMessage());
		}
		if(result <= 0)
			return false;
		else
			return true;
	}
	
	/**
	 * 订单删除
	 * @param request
	 * @author 孟晓冬
	 */
	@Override
	public Boolean deleteOrder(Long id) {
		//记录结果
		int result = 0;
		//去数据库删除该id的采购记录
		try {
			result = orderMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.warn("删除采购记录失败"+e.getMessage());
			return false;
		}
		if(result <= 0)
			return false;
		else
			return true;
	}

	/**
	 * 订单删除
	 * @param request
	 * @author 孟晓冬
	 */
	@Override
	public OrderInfoForm selectOrderInfoFormByPrimaryKey(Long id) {
		OrderInfoForm result = null;
		if(id == null){
			return null;
		}
		try {
			result = orderMapper.selectOderInfoFormByPrimaryKey(id);
		}catch (Exception e) {
			logger.error("根据id查找OrderInfoForm失败! "+e.getMessage());
		}
		//装进list为了匹配参数, 填充字典后取出
		List<OrderInfoForm> temp1 = new ArrayList<OrderInfoForm>();
		temp1.add(result);
		//填充配件字典
		fillAttachmentsAndApperances(temp1);
		result = temp1.get(0);
		return result;
	}
	
	/**
	 * 更新订单状态
	 * @param
	 * @author 卜勇峰
	 */
	@Override
	public int updateState(int state,Long id) {
		// TODO Auto-generated method stub
		logger.info("=========== 进入updateState方法，state ： " + state + "================");
		return orderMapper.updateState(state,id);
	}
	
	/**
	 * 由字典选择查询字典记录类
	 * @param brand
	 * @param model
	 * @param standard
	 * @return
	 * @author 孟晓冬
	 */
	@Override
	public List<ProductInfoForm> getProductInfoForms(Integer brand, Integer model, Integer standard) {
		return productInfoMapper.selectProductInfoFormsSelective(brand, model, standard);
	}

	@SuppressWarnings({ "unused", "unused" })
	@Override
	public List<OrderInfoForm> selectsoldselective(Order order) {
		List<OrderInfoForm> result = null;
		//去除空串,防止""查库报错
		order.setOrderNum("".equals(order.getOrderNum()) ? null : order.getOrderNum());
		order.setOperator("".equals(order.getOperator()) ? null : order.getOperator());
		order.setSerialNum("".equals(order.getSerialNum()) ? null : order.getSerialNum());
		
		try {
			result=orderMapper.selectOrdersByStatesSelective(order, new Integer[] {3,4,7,8});
			for(int i = 0;i<result.size();i++) {
				if(result.get(i).getPartPrice()!=null) {
					// 如果交易记录是退款或者部分退款，那么这条记录的最终质检价格(出售价格)-=退款金额
					result.get(i).setQualityPrice(result.get(i).getQualityPrice().subtract(result.get(i).getPartPrice()));
				}
			}
			for(int i = 0;i<result.size();i++) {
				// 如果交易记录中有维修的，那么最终成本价格等于最终采购价格-维修价格 finally-purchase+=(materiel-num)*materiel-price
				List<RepairShow> list=repairManageMapper.selectPart(result.get(i).getId());
				if(list.size()!=0) {
					BigDecimal total = new BigDecimal(0);
					for(int j = 0;j<list.size();j++) {
						int materialid = list.get(j).getMaterielId(); // 得到该商品维修材料的id
						BigDecimal singleprice = repairManageMapper.selectSinglePrice(materialid); // 根据物料id得到物料单价					
						int materialnum = list.get(j).getMaterielNum(); // 得到该商品维修材料的数量
						BigDecimal price = singleprice.multiply(new BigDecimal(materialnum));
						total = total.add(price); // 得到该商品的维修总价
					}
					result.get(i).setFinalPurchase(result.get(i).getFinalPurchase().add(total)); // 把最终计算的成本价格set到最终收购价格里
				}
			}
			
		} catch (Exception e) {
			logger.error("条件筛选采购记录失败!"+e.getMessage());
		}
		return result;
	}

	
	/**
	 * 查找所有订单，并计算价格
	 * @param order
	 * @param states
	 * @return List<OrderInfoForm>
	 * @author 周天
	 */
	@Override
	public List<OrderInfoForm> getOrdersByStates(Order order,Integer... states) {	
		List<OrderInfoForm> result = null;
		try {
			result=orderMapper.selectOrdersByStatesSelective(order, states);
			for(int i = 0;i<result.size();i++) {
				if(result.get(i).getPartPrice()!=null) {
					// 如果交易记录是退款或者部分退款，那么这条记录的最终质检价格(出售价格)-=退款金额
					result.get(i).setQualityPrice(result.get(i).getQualityPrice().subtract(result.get(i).getPartPrice()));
				}
			} 
		} catch (Exception e) {
			logger.error("多条件筛选采购记录失败!"+e.getMessage());
		}
		//填充配件字典
		result = fillAttachmentsAndApperances(result);
		return result;
	}
	/**
	 * 多个状态or筛选订单和字典
	 * @param order
	 * @param states
	 * @return List<OrderInfoForm>
	 * @author 孟晓冬
	 */
	@Override
	public List<OrderInfoForm> getOrdersByStatesSelective(Order order, Integer... states) {
		
		List<OrderInfoForm> result = null;
		try {
			result=orderMapper.selectOrdersByStatesSelective(order, states);
		} catch (Exception e) {
			logger.error("多条件筛选采购记录失败!"+e.getMessage());
		}
		//填充配件字典
		result = fillAttachmentsAndApperances(result);
		return result;
	}

	
	@Override
	public List<OrderInfoForm> selectAllOrders(Order order) {
		List<OrderInfoForm> result = null;

		try {
			result=orderMapper.selectOrderAll(order);
		} catch (Exception e) {
			logger.error("条件筛选采购记录失败!"+e.getMessage());
		}
		//填充配件字典
		result = fillAttachmentsAndApperances(result);
		return result;
	}

	/**
	 * 循环填充配件字典和外观字典用
	 * 把传入的list循环每个的Attachment去字典查找后再放回List
	 * @param result
	 * @return List<OrderInfoForm>
	 * @author 孟晓冬
	 */
	@Override
	public List<OrderInfoForm> fillAttachmentsAndApperances(List<OrderInfoForm> result) {
		//存储配件code
		List<Integer> AttachmentCodeList = new ArrayList<>() ;
		//存储配件姓名
		List<String> AttachmentNameList = new ArrayList<>();
		//存储外观code
		List<Integer> ApperanceCodeList = new ArrayList<>();
		//存储外观姓名
		List<String> ApperanceNameList = new ArrayList<>();
		
		//把附件串拆分成数组分别查询配件字典表
		for(OrderInfoForm o : result) {
			//订单中配件项存储的是字符串, 每个配件之间用, 隔开
			String attachments = o.getRandomAttachment();
			String apperances = o.getApperance();
			String[] attachmentCodes = null;
			String[] apperanceCodes = null;
			//配件不为空
			if(attachments != null) {
				//把字符串转化为数组
				attachmentCodes = attachments.split(",");
				//遍历配件数组
				for(String sttaCode : attachmentCodes) {
					//防止遍历空串, 造成查询字典结果为null
					if("".equals(sttaCode)) {
						continue;
					}
					Integer code = null;
					try {
						code =Integer.valueOf(sttaCode);
					}catch (Exception e) {
						logger.error("String的code转integer出错!"+e.getMessage());
					}
					ProductAttachment atta = null;
					//根据code值去配件库查询配件信息
					try { 
						atta = productAttachmentMapper.selectattachmentbyname(null, code);
					}catch (Exception e) {
						logger.error("配件查询失败!"+e.getMessage());
					}
					//查出的配件信息放进List存储
					AttachmentCodeList.add(atta.getCode());
					AttachmentNameList.add(atta.getName());
				}
			}
			//把存储用数组放进对象
			o.setProductAttachmentCode(AttachmentCodeList.toArray(new Integer[AttachmentCodeList.size()]));
			o.setProductAttachmentName(AttachmentNameList.toArray(new String[AttachmentNameList.size()]));
			//外观部位空
			if(apperances != null) {
				//把字符串转化为数组
				apperanceCodes = apperances.split(",");
				//遍历外观数组
				for(String appeCode : apperanceCodes) {
					//防止遍历空串, 造成查询字典结果为null
					if("".equals(appeCode)) {
						continue;
					}
					Integer code = null;
					try {
						code =Integer.valueOf(appeCode);
					}catch (Exception e) {
						logger.error("String的code转integer出错!"+e.getMessage());
					}
					ProductApperance appe = null;
					//根据code值去配件库查询配件信息
					try { 
						appe = productApperanceMapper.selectapperancebyname(null, code);
					}catch (Exception e) {
						logger.error("配件查询失败!"+e.getMessage());
					}
					//查出的配件信息放进List存储
					ApperanceCodeList.add(appe.getCode());
					ApperanceNameList.add(appe.getName());
				}
			}
			//把存储用数组放进对象
			o.setProductApperanceCode(ApperanceCodeList.toArray(new Integer[ApperanceCodeList.size()]));
			o.setProductApperanceName(ApperanceNameList.toArray(new String[ApperanceNameList.size()]));
		}
		return result;
	}
}
