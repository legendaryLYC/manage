package com.xiongantaoli.background.form;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单所用到所有字典表
 * @author 孟晓冬
 */
public class OrderInfoForm {
	
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

    private Integer productInfoId;
    
    private Integer productInfoBrand;
    
    private Integer productInfoModel;
    
    private Integer productInfoStandrad;
    
    private Integer dicMethodCode;

    private String dicMethodName;
    
    private Integer productBrandCode;

    private String productBrandName;
    
    private Integer productModelCode;

    private String productModelName;
    
    private Integer productStandradCode;

    private String productStandradName;
    
    private Integer productColorCode;

    private String productColorName;
    
    private Integer productGuaranteeCode;

    private String productGuaranteeName;
    
    private Integer[] productApperanceCode;

    private String[] productApperanceName;
    
    private Integer[] productAttachmentCode;

    private String[] productAttachmentName;
    
    private Integer productStateCode;

    private String productStateName;
    
    //下边是退货退款的域
    
    private Long drawBackUserId;

    private Long drawBackPurchaseId;

    private String drawBackLogisticsNum;

    private String drawBackLogisticsCompany;

    private BigDecimal drawBackCarriagePrice;

    private Date drawBackTime;
    
    //以下是售出的信息(先弃用)
    
    private Long soldOutUserId;

    private Long soldOutPurchaseId;

    private String soldOutLogisicsNum;

    private String soldOutLogisicsNumCompany;

    private BigDecimal soldOutCarriagePrice;

    private String soldOutSalesPerson;

    private Date soldOutTime;

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
		this.orderNum = orderNum;
	}

	public Integer getPurchaseMethod() {
		return purchaseMethod;
	}

	public void setPurchaseMethod(Integer purchaseMethod) {
		this.purchaseMethod = purchaseMethod;
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
		this.operator = operator;
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
		this.customerName = customerName;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
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
		this.apperance = apperance;
	}

	public String getRandomAttachment() {
		return randomAttachment;
	}

	public void setRandomAttachment(String randomAttachment) {
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
		this.purRemarks = purRemarks;
	}

	public String getQuRemark() {
		return quRemark;
	}

	public void setQuRemark(String quRemark) {
		this.quRemark = quRemark;
	}

	public Integer getProductInfoBrand() {
		return productInfoBrand;
	}

	public void setProductInfoBrand(Integer productInfoBrand) {
		this.productInfoBrand = productInfoBrand;
	}

	public Integer getProductInfoModel() {
		return productInfoModel;
	}

	public void setProductInfoModel(Integer productInfoModel) {
		this.productInfoModel = productInfoModel;
	}

	public Integer getProductInfoStandrad() {
		return productInfoStandrad;
	}

	public void setProductInfoStandrad(Integer productInfoStandrad) {
		this.productInfoStandrad = productInfoStandrad;
	}

	public Integer getDicMethodCode() {
		return dicMethodCode;
	}

	public void setDicMethodCode(Integer dicMethodCode) {
		this.dicMethodCode = dicMethodCode;
	}

	public String getDicMethodDescription() {
		return dicMethodName;
	}

	public void setDicMethodDescription(String dicMethodDescription) {
		this.dicMethodName = dicMethodDescription;
	}

	public Integer getProductBrandCode() {
		return productBrandCode;
	}

	public void setProductBrandCode(Integer productBrandCode) {
		this.productBrandCode = productBrandCode;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public Integer getProductModelCode() {
		return productModelCode;
	}

	public void setProductModelCode(Integer productModelCode) {
		this.productModelCode = productModelCode;
	}

	public String getProductModelName() {
		return productModelName;
	}

	public void setProductModelName(String productModelName) {
		this.productModelName = productModelName;
	}

	public Integer getProductStandradCode() {
		return productStandradCode;
	}

	public void setProductStandradCode(Integer productStandradCode) {
		this.productStandradCode = productStandradCode;
	}

	public String getProductStandradName() {
		return productStandradName;
	}

	public void setProductStandradName(String productStandradName) {
		this.productStandradName = productStandradName;
	}

	public Integer getProductColorCode() {
		return productColorCode;
	}

	public void setProductColorCode(Integer productColorCode) {
		this.productColorCode = productColorCode;
	}

	public String getProductColorName() {
		return productColorName;
	}

	public void setProductColorName(String productColorName) {
		this.productColorName = productColorName;
	}

	public Integer getProductGuaranteeCode() {
		return productGuaranteeCode;
	}

	public void setProductGuaranteeCode(Integer productGuaranteeCode) {
		this.productGuaranteeCode = productGuaranteeCode;
	}

	public String getProductGuaranteeName() {
		return productGuaranteeName;
	}

	public void setProductGuaranteeName(String productGuaranteeName) {
		this.productGuaranteeName = productGuaranteeName;
	}

	public Integer[] getProductApperanceCode() {
		return productApperanceCode;
	}

	public void setProductApperanceCode(Integer[] productApperanceCode) {
		this.productApperanceCode = productApperanceCode;
	}

	public String[] getProductApperanceName() {
		return productApperanceName;
	}

	public void setProductApperanceName(String[] productApperanceName) {
		this.productApperanceName = productApperanceName;
	}

	public Integer[] getProductAttachmentCode() {
		return productAttachmentCode;
	}

	public void setProductAttachmentCode(Integer[] productAttachmentCode) {
		this.productAttachmentCode = productAttachmentCode;
	}

	public String[] getProductAttachmentName() {
		return productAttachmentName;
	}

	public void setProductAttachmentName(String[] productAttachmentName) {
		this.productAttachmentName = productAttachmentName;
	}

	public Integer getProductStateCode() {
		return productStateCode;
	}

	public void setProductStateCode(Integer productStateCode) {
		this.productStateCode = productStateCode;
	}

	public String getProductStateName() {
		return productStateName;
	}

	public void setProductStateName(String productStateName) {
		this.productStateName = productStateName;
	}

	public Integer getProductInfoId() {
		return productInfoId;
	}

	public void setProductInfoId(Integer productInfoId) {
		this.productInfoId = productInfoId;
	}

	public String getDicMethodName() {
		return dicMethodName;
	}

	public void setDicMethodName(String dicMethodName) {
		this.dicMethodName = dicMethodName;
	}

	public Long getDrawBackUserId() {
		return drawBackUserId;
	}

	public void setDrawBackUserId(Long drawBackUserId) {
		this.drawBackUserId = drawBackUserId;
	}

	public Long getDrawBackPurchaseId() {
		return drawBackPurchaseId;
	}

	public void setDrawBackPurchaseId(Long drawBackPurchaseId) {
		this.drawBackPurchaseId = drawBackPurchaseId;
	}

	public String getDrawBackLogisticsNum() {
		return drawBackLogisticsNum;
	}

	public void setDrawBackLogisticsNum(String drawBackLogisticsNum) {
		this.drawBackLogisticsNum = drawBackLogisticsNum;
	}

	public String getDrawBackLogisticsCompany() {
		return drawBackLogisticsCompany;
	}

	public void setDrawBackLogisticsCompany(String drawBackLogisticsCompany) {
		this.drawBackLogisticsCompany = drawBackLogisticsCompany;
	}

	public BigDecimal getDrawBackCarriagePrice() {
		return drawBackCarriagePrice;
	}

	public void setDrawBackCarriagePrice(BigDecimal drawBackCarriagePrice) {
		this.drawBackCarriagePrice = drawBackCarriagePrice;
	}

	public Date getDrawBackTime() {
		return drawBackTime;
	}

	public void setDrawBackTime(Date drawBackTime) {
		this.drawBackTime = drawBackTime;
	}

	public Long getSoldOutUserId() {
		return soldOutUserId;
	}

	public void setSoldOutUserId(Long soldOutUserId) {
		this.soldOutUserId = soldOutUserId;
	}

	public Long getSoldOutPurchaseId() {
		return soldOutPurchaseId;
	}

	public void setSoldOutPurchaseId(Long soldOutPurchaseId) {
		this.soldOutPurchaseId = soldOutPurchaseId;
	}

	public String getSoldOutLogisicsNum() {
		return soldOutLogisicsNum;
	}

	public void setSoldOutLogisicsNum(String soldOutLogisicsNum) {
		this.soldOutLogisicsNum = soldOutLogisicsNum;
	}

	public String getSoldOutLogisicsNumCompany() {
		return soldOutLogisicsNumCompany;
	}

	public void setSoldOutLogisicsNumCompany(String soldOutLogisicsNumCompany) {
		this.soldOutLogisicsNumCompany = soldOutLogisicsNumCompany;
	}

	public BigDecimal getSoldOutCarriagePrice() {
		return soldOutCarriagePrice;
	}

	public void setSoldOutCarriagePrice(BigDecimal soldOutCarriagePrice) {
		this.soldOutCarriagePrice = soldOutCarriagePrice;
	}

	public String getSoldOutSalesPerson() {
		return soldOutSalesPerson;
	}

	public void setSoldOutSalesPerson(String soldOutSalesPerson) {
		this.soldOutSalesPerson = soldOutSalesPerson;
	}

	public Date getSoldOutTime() {
		return soldOutTime;
	}

	public void setSoldOutTime(Date soldOutTime) {
		this.soldOutTime = soldOutTime;
	}
	
}
