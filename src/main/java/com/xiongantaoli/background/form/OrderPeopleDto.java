package com.xiongantaoli.background.form;

import java.math.BigDecimal;

/**
 * @author 周天
 * @Date 2019/1/22 15:19
 *  用来存储该订单分别被那些人员操作过
 */

public class OrderPeopleDto {

	private Long orderId;
	
	private Long purchaseId;
	
	private Long qualityId;
	
	private Long repairId;
	
	private Long salerId;
	
	private String purchaseName;
	
	private String qualityName;
	
	private String repairName;
	
	private String salerName;
	
	private BigDecimal purchasePrice;
	
	private BigDecimal qualityPrice;
	
	private BigDecimal repairPrice;
	
	private BigDecimal salerPrice;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Long getQualityId() {
		return qualityId;
	}

	public void setQualityId(Long qualityId) {
		this.qualityId = qualityId;
	}

	public Long getRepairId() {
		return repairId;
	}

	public void setRepairId(Long repairId) {
		this.repairId = repairId;
	}

	public Long getSalerId() {
		return salerId;
	}

	public void setSalerId(Long salerId) {
		this.salerId = salerId;
	}


	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public String getQualityName() {
		return qualityName;
	}

	public void setQualityName(String qualityName) {
		this.qualityName = qualityName;
	}

	public String getRepairName() {
		return repairName;
	}

	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}

	public String getSalerName() {
		return salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getQualityPrice() {
		return qualityPrice;
	}

	public void setQualityPrice(BigDecimal qualityPrice) {
		this.qualityPrice = qualityPrice;
	}

	public BigDecimal getRepairPrice() {
		return repairPrice;
	}

	public void setRepairPrice(BigDecimal repairPrice) {
		this.repairPrice = repairPrice;
	}

	public BigDecimal getSalerPrice() {
		return salerPrice;
	}

	public void setSalerPrice(BigDecimal salerPrice) {
		this.salerPrice = salerPrice;
	}
	
	
}
