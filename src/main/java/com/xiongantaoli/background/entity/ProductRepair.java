package com.xiongantaoli.background.entity;

import java.math.BigDecimal;

public class ProductRepair {
	
    public ProductRepair(Long id, Integer code, String name, BigDecimal price) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = price;
	}

    public ProductRepair() {
    	
    }
    
	@Override
	public String toString() {
		return "ProductRepair [id=" + id + ", code=" + code + ", name=" + name + ", price=" + price + "]";
	}

	private Long id;

    private Integer code;

    private String name;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}