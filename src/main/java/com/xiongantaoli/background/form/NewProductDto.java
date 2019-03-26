package com.xiongantaoli.background.form;

import java.math.BigDecimal;

/**
 * 在手机添加页面,接受前端传来参数的Dto类
 *  时间:2019/1/12
 * 最后修改时间:2019/1/12
 * 注意事项:无
* @author 周天
*
*/
public class NewProductDto {
	
	private Long id; //品牌的id值

	private int brand; //品牌的code值

	private int model; //型号的code值

    private int standard; //规格的code值
    
    private BigDecimal basePrice;
    
    private BigDecimal scratchPrice;

    private BigDecimal knockPrice;

    private BigDecimal packingPrice;

    private BigDecimal attachmentPrice;
    
    private BigDecimal baseRoyaltyPrice;
    
    private BigDecimal saleRoyaltyPrice;
    
    private BigDecimal scratchPriceTwo;

    private BigDecimal knockPriceTwo;
    
    private BigDecimal nationLock;
    
    private BigDecimal warranty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrand() {
		return brand;
	}

	public void setBrand(int brand) {
		this.brand = brand;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getScratchPrice() {
		return scratchPrice;
	}

	public void setScratchPrice(BigDecimal scratchPrice) {
		this.scratchPrice = scratchPrice;
	}

	public BigDecimal getKnockPrice() {
		return knockPrice;
	}

	public void setKnockPrice(BigDecimal knockPrice) {
		this.knockPrice = knockPrice;
	}

	public BigDecimal getPackingPrice() {
		return packingPrice;
	}

	public void setPackingPrice(BigDecimal packingPrice) {
		this.packingPrice = packingPrice;
	}

	public BigDecimal getAttachmentPrice() {
		return attachmentPrice;
	}

	public void setAttachmentPrice(BigDecimal attachmentPrice) {
		this.attachmentPrice = attachmentPrice;
	}

	public BigDecimal getBaseRoyaltyPrice() {
		return baseRoyaltyPrice;
	}

	public void setBaseRoyaltyPrice(BigDecimal baseRoyaltyPrice) {
		this.baseRoyaltyPrice = baseRoyaltyPrice;
	}

	public BigDecimal getSaleRoyaltyPrice() {
		return saleRoyaltyPrice;
	}

	public void setSaleRoyaltyPrice(BigDecimal saleRoyaltyPrice) {
		this.saleRoyaltyPrice = saleRoyaltyPrice;
	}

	public BigDecimal getScratchPriceTwo() {
		return scratchPriceTwo;
	}

	public void setScratchPriceTwo(BigDecimal scratchPriceTwo) {
		this.scratchPriceTwo = scratchPriceTwo;
	}

	public BigDecimal getKnockPriceTwo() {
		return knockPriceTwo;
	}

	public void setKnockPriceTwo(BigDecimal knockPriceTwo) {
		this.knockPriceTwo = knockPriceTwo;
	}

	public BigDecimal getNationLock() {
		return nationLock;
	}

	public void setNationLock(BigDecimal nationLock) {
		this.nationLock = nationLock;
	}

	public BigDecimal getWarranty() {
		return warranty;
	}

	public void setWarranty(BigDecimal warranty) {
		this.warranty = warranty;
	}


	


}
