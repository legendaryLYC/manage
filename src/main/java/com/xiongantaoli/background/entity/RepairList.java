package com.xiongantaoli.background.entity;

import java.util.Date;

/**
 * 维修列表
 * @author 卜勇峰
 */

public class RepairList {
	
    private Long id; //id

    private Long repairId; //维修人员id

    private Long orderId; //订单id

    private String repairStyle; //维修类别

    private Date time; //时间

    public RepairList() {
    	
    }
    
    public RepairList(Long repairId, Long orderId, String repairStyle, Date time) {
		super();
		this.repairId = repairId;
		this.orderId = orderId;
		this.repairStyle = repairStyle;
		this.time = time;
	}

	public RepairList(Long id, Long repairId, Long orderId, String repairStyle, Date time) {
		super();
		this.id = id;
		this.repairId = repairId;
		this.orderId = orderId;
		this.repairStyle = repairStyle;
		this.time = time;
	}

	@Override
	public String toString() {
		return "RepairList [id=" + id + ", repairId=" + repairId + ", orderId=" + orderId + ", repairStyle="
				+ repairStyle + ", time=" + time + "]";
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getRepairStyle() {
        return repairStyle;
    }

    public void setRepairStyle(String repairStyle) {
        this.repairStyle = repairStyle == null ? null : repairStyle.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}