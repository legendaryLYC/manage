package com.xiongantaoli.background.form;


/**
 * 用于批量修改状态前后端json交互
 * @author 孟晓冬
 *
 */
public class LongsAndIntegerDto {
	Long[] ids;
	Integer state;
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
