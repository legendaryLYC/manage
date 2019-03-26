package com.xiongantaoli.background.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.xiongantaoli.background.entity.PriceToMateriel;
import org.apache.ibatis.annotations.Param;
import com.xiongantaoli.background.entity.RepairManagement;
import com.xiongantaoli.background.form.RepairShow;

public interface RepairManagementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RepairManagement record);

    int insertSelective(RepairManagement record);

    RepairManagement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RepairManagement record);

    int updateByPrimaryKey(RepairManagement record);
    
    /**
     *	 通过订单id查询物料单价
     * @param id
     * @return
     */
    List<PriceToMateriel> selectByPurchaseId(Long id);
    

	List<RepairShow> selectPart(Long id);

	RepairManagement selectIsExist(@Param(value = "materielId")int materielId, @Param(value = "purchaseId") Long purchaseId);

	BigDecimal selectSinglePrice(int materialid); // 李永成添加方法，利用物料id查看物料的单价

	boolean deleteByPurchaseId(Long key);
    
}