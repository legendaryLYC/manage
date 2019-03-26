package com.xiongantaoli.background.form;

import java.math.BigDecimal;

/**
 * 从数据库拿出数据存储的form类
 *  时间:2019/1/10
 * 最后修改时间:2019/1/13
 * 注意事项:无
* @author 周天
*
*/
public class ProductInfoForm {
	 	private Long id;

	    private Integer brand;

	    private Integer model;

	    private Integer standard;

	    private BigDecimal baseRoyalty;

	    private BigDecimal saleRoyalty;

	    private BigDecimal scratchPrice;

	    private BigDecimal knockPrice;
	    
	    private BigDecimal scratchPriceTwo;

	    private BigDecimal knockPriceTwo;
	    
	    private BigDecimal nationLock;
	    
	    private BigDecimal warranty;
	    
	    private BigDecimal packingPrice;

	    private BigDecimal attachmentPrice;

	    private BigDecimal basePrice;

	    private String brandname;
	    
	    private String modelname;
	    
	    private String standardname;

		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
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



		public Integer getStandard() {
			return standard;
		}



		public void setStandard(Integer standard) {
			this.standard = standard;
		}



		public BigDecimal getBaseRoyalty() {
			return baseRoyalty;
		}



		public void setBaseRoyalty(BigDecimal baseRoyalty) {
			this.baseRoyalty = baseRoyalty;
		}



		public BigDecimal getSaleRoyalty() {
			return saleRoyalty;
		}



		public void setSaleRoyalty(BigDecimal saleRoyalty) {
			this.saleRoyalty = saleRoyalty;
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



		public BigDecimal getBasePrice() {
			return basePrice;
		}



		public void setBasePrice(BigDecimal basePrice) {
			this.basePrice = basePrice;
		}



		public String getBrandname() {
			return brandname;
		}



		public void setBrandname(String brandname) {
			this.brandname = brandname;
		}



		public String getModelname() {
			return modelname;
		}



		public void setModelname(String modelname) {
			this.modelname = modelname;
		}



		public String getStandardname() {
			return standardname;
		}



		public void setStandardname(String standardname) {
			this.standardname = standardname;
		}


		public ProductInfoForm() {
			super();
		}



		public ProductInfoForm(Long id, Integer brand, Integer model, Integer standard, BigDecimal baseRoyalty,
				BigDecimal saleRoyalty, BigDecimal scratchPrice, BigDecimal knockPrice, BigDecimal scratchPriceTwo,
				BigDecimal knockPriceTwo, BigDecimal nationLock, BigDecimal warranty, BigDecimal packingPrice,
				BigDecimal attachmentPrice, BigDecimal basePrice, String brandname, String modelname,
				String standardname) {
			super();
			this.id = id;
			this.brand = brand;
			this.model = model;
			this.standard = standard;
			this.baseRoyalty = baseRoyalty;
			this.saleRoyalty = saleRoyalty;
			this.scratchPrice = scratchPrice;
			this.knockPrice = knockPrice;
			this.scratchPriceTwo = scratchPriceTwo;
			this.knockPriceTwo = knockPriceTwo;
			this.nationLock = nationLock;
			this.warranty = warranty;
			this.packingPrice = packingPrice;
			this.attachmentPrice = attachmentPrice;
			this.basePrice = basePrice;
			this.brandname = brandname;
			this.modelname = modelname;
			this.standardname = standardname;
		}



		@Override
		public String toString() {
			return "ProductInfoForm [id=" + id + ", brand=" + brand + ", model=" + model + ", standard=" + standard
					+ ", baseRoyalty=" + baseRoyalty + ", saleRoyalty=" + saleRoyalty + ", scratchPrice=" + scratchPrice
					+ ", knockPrice=" + knockPrice + ", scratchPriceTwo=" + scratchPriceTwo + ", knockPriceTwo="
					+ knockPriceTwo + ", nationLock=" + nationLock + ", warranty=" + warranty + ", packingPrice="
					+ packingPrice + ", attachmentPrice=" + attachmentPrice + ", basePrice=" + basePrice
					+ ", brandname=" + brandname + ", modelname=" + modelname + ", standardname=" + standardname + "]";
		}
		

	    
}
	