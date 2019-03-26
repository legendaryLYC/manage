package com.xiongantaoli.background.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderForm {

	private Long id;

    private Long userId;

    private Long productId;

    private String orderNum;

    private Integer purchaseMethod;

    private Integer brand;

    private Integer model;

    private Integer color;

    private Integer orderState;

    private String operator;

    private Date purchaseTime;

    private Date finalTime;

    private String customerName;

    private String serialNum;

    private Integer guarantee;

    private Integer apperance;

    private Integer randomAttachment;

    private BigDecimal purchasePrice;

    private BigDecimal finalPurchase;

    private String remarks;
    
    private String description;
    
    private String brandName;
    
    private String colorName;
    
    private String modelName;

    public OrderForm(Long id, Long userId, Long productId, String orderNum, Integer purchaseMethod, Integer brand,
    		Integer model, Integer color, Integer orderState, String operator, Date purchaseTime, Date finalTime,
    		String customerName, String serialNum, Integer guarantee, Integer apperance, Integer randomAttachment,
    		BigDecimal purchasePrice, BigDecimal finalPurchase, String remarks,String description,String brandName,
    		String colorName,String modelName) {
    	super();
    	this.id = id;
    	this.userId = userId;
    	this.productId = productId;
    	this.orderNum = orderNum;
    	this.purchaseMethod = purchaseMethod;
    	this.brand = brand;
    	this.model = model;
    	this.color = color;
    	this.orderState = orderState;
    	this.operator = operator;
    	this.purchaseTime = purchaseTime;
    	this.finalTime = finalTime;
    	this.customerName = customerName;
    	this.serialNum = serialNum;
    	this.guarantee = guarantee;
    	this.apperance = apperance;
    	this.randomAttachment = randomAttachment;
    	this.purchasePrice = purchasePrice;
    	this.finalPurchase = finalPurchase;
    	this.remarks = remarks;
    	this.description = description;
    	this.brandName = brandName;
    	this.colorName = colorName;
    	this.modelName = modelName;
    }
    
    public OrderForm() {super();};
    
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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getPurchaseMethod() {
        return purchaseMethod;
    }

    public void setPurchaseMethod(Integer purchaseMethod) {
        this.purchaseMethod = purchaseMethod;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
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

    public Integer getApperance() {
        return apperance;
    }

    public void setApperance(Integer apperance) {
        this.apperance = apperance;
    }

    public Integer getRandomAttachment() {
        return randomAttachment;
    }

    public void setRandomAttachment(Integer randomAttachment) {
        this.randomAttachment = randomAttachment;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
    
}