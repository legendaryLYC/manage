package com.xiongantaoli.background.entity;

import java.math.BigDecimal;

/**
 *	 用来对照物料价格和数量
 * @author HRX
 *
 */
public class PriceToMateriel {
	
	private Long id;
	 
	private BigDecimal price;	// 物料单间
	
	private BigDecimal num;		// 物料数量

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}
	
	
}
