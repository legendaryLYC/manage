package com.xiongantaoli.background.form;

/**
 * @author 周天
 * @Date 2019/1/16 17:52
 *  三个属性的实体类，后端接受参数使用
 */
public class ThreeConditonDto {
	
	private Integer brand; //品牌的code值

	private Integer model; //型号的code值

    private Integer standard; //规格的code值


	
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

	public ThreeConditonDto(Integer brand, Integer model, Integer standard) {
		super();
		this.brand = brand;
		this.model = model;
		this.standard = standard;
	}



	public ThreeConditonDto() {
		
	}

}
