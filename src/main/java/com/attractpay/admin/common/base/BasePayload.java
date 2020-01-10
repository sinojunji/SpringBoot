package com.attractpay.admin.common.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePayload {

	//用户ID
	private Long user_id;
	//商户ID
	private Long merchant_id;
	//店铺ID
	private Long store_id;
	//交易货币
	private String currency;
	//交易金额
	private String amount;
	//交易备注
	private String comment;

}
