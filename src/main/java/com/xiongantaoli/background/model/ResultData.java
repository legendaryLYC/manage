package com.xiongantaoli.background.model;

public class ResultData<T> {
	
	/*
	 * 逻辑状态码——"000000"表示成功   "999999"表示失败  "222222"代表其他
	 */
	private String code = "999999";
	
	/*
	 * 如果发生错误，返回错误信息
	 */
	private String message;
	
	/*
	 * 如果逻辑成功，则返回数据
	 */
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultData [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	
	public static ResultData setCodeAndMessage(int key,String successMessage,String errorMessage) {
		if(key == 1) {
			return new ResultData("000000",successMessage);
		}else {
			return new ResultData("999999",errorMessage);
		}
	}

	public ResultData() {
		super();
	}

	public ResultData(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
