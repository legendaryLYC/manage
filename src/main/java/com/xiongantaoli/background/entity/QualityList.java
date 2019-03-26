package com.xiongantaoli.background.entity;

import java.util.Date;

/**
 * 质检列表
 * @author 卜勇峰
 *
 */

public class QualityList {
	
    private Long id; //id

    private Long qualityId; //质检人员id

    private Long orderId; //订单id

    private Date time; //时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQualityId() {
        return qualityId;
    }

    public void setQualityId(Long qualityId) {
        this.qualityId = qualityId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}