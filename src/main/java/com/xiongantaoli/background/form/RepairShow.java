package com.xiongantaoli.background.form;

/**
 * 维修物料展示类
 * 
 * @author 卜勇峰
 */

public class RepairShow{
	private Long id; //维修管理列表id
	
	private Long purchaseId; //订单id
	
	private int materielId; //物料编号
	
	private int materielNum; //物料使用数量
	
	private String materielName; //物料名字
	
	private int selectStyle; //维修类型编号
	
	private String name; //维修类型名字

	public Long getId() {
		return id;
	}
	
	public RepairShow() {
		
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getMaterielId() {
		return materielId;
	}

	@Override
	public String toString() {
		return "RepairShow [id=" + id + ", purchaseId=" + purchaseId + ", materielId=" + materielId + ", materielNum="
				+ materielNum + ", materielName=" + materielName + ", selectStyle=" + selectStyle + ", name=" + name
				+ "]";
	}

	public void setMaterielId(int materielId) {
		this.materielId = materielId;
	}

	public int getMaterielNum() {
		return materielNum;
	}

	public void setMaterielNum(int materielNum) {
		this.materielNum = materielNum;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public int getSelectStyle() {
		return selectStyle;
	}

	public void setSelectStyle(int selectStyle) {
		this.selectStyle = selectStyle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RepairShow(String name, Long id,Long purchaseId, int materielId,  int selectStyle,int materielNum, String materielName
			) {
		super();
		this.id = id;
		this.purchaseId = purchaseId;
		this.materielId = materielId;
		this.materielNum = materielNum;
		this.materielName = materielName;
		this.selectStyle = selectStyle;
		this.name = name;
	}
	
	
}
