package com.xiongantaoli.background.entity;

/**
 * 维修订单具有的维修物料
 * @author 卜勇峰
 *
 */

public class RepairManagement {
    private Long id; //维修管理id

    private Long userId; //操作员id

    private Long purchaseId; //订单id

    public RepairManagement(Long purchaseId, Integer materielId, Integer materielNum) {
		super();
		this.purchaseId = purchaseId;
		this.materielId = materielId;
		this.materielNum = materielNum;
	}

	@Override
	public String toString() {
		return "RepairManagement [id=" + id + ", userId=" + userId + ", purchaseId=" + purchaseId + ", materielId="
				+ materielId + ", selectStyle=" + selectStyle + ", materielNum=" + materielNum + "]";
	}

	private Integer materielId; //物料id

    private Integer selectStyle; //维修类型id

    private Integer materielNum; //该物料消耗数量

    public RepairManagement() {
    	
    }
    
    public RepairManagement(Long id, Long userId, Long purchaseId, Integer materielId, Integer selectStyle,
			Integer materielNum) {
		super();
		this.id = id;
		this.userId = userId;
		this.purchaseId = purchaseId;
		this.materielId = materielId;
		this.selectStyle = selectStyle;
		this.materielNum = materielNum;
	}

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

    public Integer getMaterielId() {
        return materielId;
    }

    public void setMaterielId(Integer materielId) {
        this.materielId = materielId;
    }

    public Integer getSelectStyle() {
        return selectStyle;
    }

    public void setSelectStyle(Integer selectStyle) {
        this.selectStyle = selectStyle;
    }

    public Integer getMaterielNum() {
        return materielNum;
    }

    public void setMaterielNum(Integer materielNum) {
        this.materielNum = materielNum;
    }

}