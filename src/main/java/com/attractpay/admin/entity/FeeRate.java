package com.attractpay.admin.entity;

import com.attractpay.admin.common.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@TableName("t_feerate")
@Getter
@Setter
public class FeeRate implements Serializable {

	private static final long serialVersionUID = -8696753149183987576L;

	private Long id;
	
	private Long store_id;//店铺主键
	
	/////////支付宝///////////
	private Double alipay_offline_charge_store;	// 线下收店铺
	private Double alipay_offline_charge_client;	//线下收客人
	private Double alipay_offline_agent_store; // 线下二级代理收店铺

	private Double alipay_online_charge_store;		//线上收店铺
	private Double alipay_online_charge_client;		//线上收客人
	private Double alipay_online_agent_store; // 线上二级代理收店铺
	
	
	/////////支付宝///////////
	private Double wechat_offline_charge_store;  // 线下收店铺
	private Double wechat_offline_charge_client; //线下收客人
	private Double wechat_offline_agent_store; // 线下二级代理收店铺
	
	private Double wechat_online_charge_store;    //线上收店铺
	private Double wechat_online_charge_client;   //线上收客人
	private Double wechat_online_agent_store; // 线上二级代理收店铺
	
	/////////银联///////////
	private Double upop_offline_charge_store;  // 线下收店铺
	private Double upop_offline_charge_client; //线下收客人
	private Double upop_offline_agent_store; // 线下二级代理收店铺
	
	private Double upop_online_charge_store;    //线上收店铺
	private Double upop_online_charge_client;   //线上收客人
	private Double upop_online_agent_store; // 线上二级代理收店铺
	
	private Date update_date;//更新时间


	
	private String fee_source;//费率计算方式 P:paidAmount O:orderAmount

}
