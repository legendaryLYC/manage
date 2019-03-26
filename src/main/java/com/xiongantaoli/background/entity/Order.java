package com.xiongantaoli.background.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long id;

    private Long userId;

    private Long productId;

    private Integer color;

    private String orderNum;

    private Integer purchaseMethod;

    private Integer orderState;

    private String operator;

    private Date purchaseTime;

    private Date finalTime;

    private String customerName;

    private String serialNum;

    private Integer guarantee;

    private String apperance;

    private String randomAttachment;

    private BigDecimal purchasePrice;

    private BigDecimal finalPurchase;

    private BigDecimal qualityPrice;

    private BigDecimal partPrice;

    private String purRemarks;

    private String quRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Date getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public Integer getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Integer guarantee) {
        this.guarantee = guarantee;
    }

    public String getApperance() {
        return apperance;
    }

    public void setApperance(String apperance) {
        this.apperance = apperance == null ? null : apperance.trim();
    }

    public String getRandomAttachment() {
        return randomAttachment;
    }

    public void setRandomAttachment(String randomAttachment) {
        this.randomAttachment = randomAttachment== null ? null : randomAttachment.trim();
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getFinalPurchase() {
        return finalPurchase;
    }

    public void setFinalPurchase(BigDecimal finalPurchase) {
        this.finalPurchase = finalPurchase;
    }

    public BigDecimal getQualityPrice() {
        return qualityPrice;
    }

    public void setQualityPrice(BigDecimal qualityPrice) {
        this.qualityPrice = qualityPrice;
    }

    public BigDecimal getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(BigDecimal partPrice) {
        this.partPrice = partPrice;
    }

    public String getPurRemarks() {
        return purRemarks;
    }

    public void setPurRemarks(String purRemarks) {
        this.purRemarks = purRemarks == null ? null : purRemarks.trim();
    }

    public String getQuRemark() {
        return quRemark;
    }

    public void setQuRemark(String quRemark) {
        this.quRemark = quRemark == null ? null : quRemark.trim();
    }

	public Integer getPurchaseMethod() {
		return purchaseMethod;
	}

	public void setPurchaseMethod(Integer purchaseMethod) {
		this.purchaseMethod = purchaseMethod;
	}
}