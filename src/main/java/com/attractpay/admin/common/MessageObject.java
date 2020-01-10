package com.attractpay.admin.common;

public class MessageObject {
	public static final Integer OK = 0;
	//ERROR CODE LIST
	
	public static final Integer ERROR_COMMON = 100;//系统异常
	public static final Integer FAILED = 101; // Failed to execute
	public static final Integer WAIT_CUSTOMER_PRESS_PASSWORD = 200; // 等待支付
	public static final Integer PAY_FAILED = 201;// 支付失败
	public static final Integer PAY_SUCCESS = 202; // 支付成功
	public static final Integer REFUND_SUCCESS = 203; // 退款成功
	public static final Integer REFUND_FAILED = 204; //  退款失败
	public static final Integer REFUND_ON_PROCESSING = 205;// 退款申请成功，等待退款
	public static final Integer APP_QRCODEURL_CREATE_SUCCESS = 206; // 创建预支付订单成功
	public static final Integer APP_QRCODEURL_CREATE_FALIED = 207; // 创建预支付订单失败
	
	public static final Integer APP_ORDER_CREATE_SUCCESS = 208; // 创建APP支付订单成功
	public static final Integer APP_ORDER_CREATE_FAILED = 209; // 创建APP支付订单失败
	
	
	
	private Integer code = 0;
	private String message;
	private Object dataObject;
	
	public MessageObject(Integer code, String message, Object dataObject) {
		super();
		this.code = code;
		this.message = message;
		this.dataObject = dataObject;
	}
	
	public MessageObject() {
		super();
	}

	public MessageObject(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getDataObject() {
		return dataObject;
	}
	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}
	
}
