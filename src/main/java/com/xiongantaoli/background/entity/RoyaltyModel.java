package com.xiongantaoli.background.entity;

import java.math.BigDecimal;

/**
 * 统计封装模型类
 * @author HRX
 *
 */
public class RoyaltyModel {
	
	private BigDecimal total;	// 总成交金额
	
	private BigDecimal base;	// 成本
	
	private BigDecimal royalty;	// 提成
	
	public RoyaltyModel() {
		this.total = new BigDecimal("0");
		this.base = new BigDecimal("0");
		this.royalty = new BigDecimal("0");
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getBase() {
		return base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	public BigDecimal getRoyalty() {
		return royalty;
	}

	public void setRoyalty(BigDecimal royalty) {
		this.royalty = royalty;
	}

	
}
