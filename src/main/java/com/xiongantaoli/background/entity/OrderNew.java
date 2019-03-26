package com.xiongantaoli.background.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderNew {
    private Long id;

    private String num;

    private Date date;

    private String model;

    private String color;

    private String version;

    private String memory;

    private String seller;

    private String express;

    private BigDecimal purchasePrice;

    private BigDecimal purchaseExpress;

    private BigDecimal repairPrice;

    private BigDecimal sellPrice;

    private BigDecimal sellExpress;

    private BigDecimal profit;

    private String buyer;

    private String sellNumber;

    private String state;

    private String remark;

    private String user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getPurchaseExpress() {
        return purchaseExpress;
    }

    public void setPurchaseExpress(BigDecimal purchaseExpress) {
        this.purchaseExpress = purchaseExpress;
    }

    public BigDecimal getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(BigDecimal repairPrice) {
        this.repairPrice = repairPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getSellExpress() {
        return sellExpress;
    }

    public void setSellExpress(BigDecimal sellExpress) {
        this.sellExpress = sellExpress;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSellNumber() {
        return sellNumber;
    }

    public void setSellNumber(String sellNumber) {
        this.sellNumber = sellNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OrderNew{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", date=" + date +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", version='" + version + '\'' +
                ", memory='" + memory + '\'' +
                ", seller='" + seller + '\'' +
                ", express='" + express + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", purchaseExpress=" + purchaseExpress +
                ", repairPrice=" + repairPrice +
                ", sellPrice=" + sellPrice +
                ", sellExpress=" + sellExpress +
                ", profit=" + profit +
                ", buyer='" + buyer + '\'' +
                ", sellNumber='" + sellNumber + '\'' +
                ", state='" + state + '\'' +
                ", remark='" + remark + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}