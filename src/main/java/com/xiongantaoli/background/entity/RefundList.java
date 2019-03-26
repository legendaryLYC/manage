package com.xiongantaoli.background.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RefundList {
    private Integer id;

    private Long orderId;

    private String logisticsNum;

    private String logisticsCompany;

    private BigDecimal carriagePrice;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getorderId() {
        return orderId;
    }

    public void setorderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(String logisticsNum) {
        this.logisticsNum = logisticsNum == null ? null : logisticsNum.trim();
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany == null ? null : logisticsCompany.trim();
    }

    public BigDecimal getCarriagePrice() {
        return carriagePrice;
    }

    public void setCarriagePrice(BigDecimal carriagePrice) {
        this.carriagePrice = carriagePrice;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

	@Override
	public String toString() {
		return "RefundList [id=" + id + ", orderId=" + orderId + ", logisticsNum=" + logisticsNum
				+ ", logisticsCompany=" + logisticsCompany + ", carriagePrice=" + carriagePrice + ", time=" + time
				+ "]";
	}
    
}