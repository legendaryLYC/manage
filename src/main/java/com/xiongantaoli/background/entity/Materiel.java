package com.xiongantaoli.background.entity;

import java.math.BigDecimal;

/**
 * 物料类
 * @author 卜勇峰
 *
 */

public class Materiel {


	private Long id; //物料id

    private Integer code; //物料编号

    public Materiel(Long id, Integer code, String materielName, BigDecimal materielPrice, Integer materielNum) {
		super();
		this.id = id;
		this.code = code;
		this.materielName = materielName;
		this.materielPrice = materielPrice;
		this.materielNum = materielNum;
	}
    
    public Materiel(String materielName, BigDecimal materielPrice, Integer materielNum) {
		super();
		this.materielName = materielName;
		this.materielPrice = materielPrice;
		this.materielNum = materielNum;
	}
    
    public Materiel() {
    	
    }

	@Override
	public String toString() {
		return "Materiel [id=" + id + ", code=" + code + ", materielName=" + materielName + ", materielPrice="
				+ materielPrice + ", materielNum=" + materielNum + "]";
	}

	private String materielName; //物料名称

    private BigDecimal materielPrice; //物料单价

    private Integer materielNum; ////物料数量

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName == null ? null : materielName.trim();
    }

    public BigDecimal getMaterielPrice() {
        return materielPrice;
    }

    public void setMaterielPrice(BigDecimal materielPrice) {
        this.materielPrice = materielPrice;
    }

    public Integer getMaterielNum() {
        return materielNum;
    }

    public void setMaterielNum(Integer materielNum) {
        this.materielNum = materielNum;
    }
}