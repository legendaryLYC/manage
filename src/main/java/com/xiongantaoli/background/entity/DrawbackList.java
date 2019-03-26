package com.xiongantaoli.background.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DrawbackList {
    private Long id;

    private Long userId;

    private Long purchaseId;

    private String logisticsNum;

    private String logisticsCompany;

    private BigDecimal carriagePrice;

    private Date time;

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

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
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
}