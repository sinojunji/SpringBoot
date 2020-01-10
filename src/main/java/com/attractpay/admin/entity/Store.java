package com.attractpay.admin.entity;

import java.io.Serializable;
import java.util.Date;

import com.attractpay.admin.common.annotation.TableName;
import lombok.Data;

@TableName("t_store")
@Data
public class Store implements Serializable {

	private static final long serialVersionUID = 7651955436776799955L;

	private Long id;
	
	private Long merchant_id;//商户主键
	
	private String store_code;//NZ0064_001
	
	private String store_name;//店铺名称
	
	private String show_name;//显示名称
	
	private String region;//地区
	
	private String district;//区域
	
	private String address;//详细地址
	
	private String industry_id;//所属行业
	
	private String expected_volume;//预计营业额
	
	private String open_time;//营业时间
	
	private String contact;//联系人
	
	private String phone;//电话
	
	private String email;//邮箱

	private String business_type;//业务类型可多选 1:Hotel 2:AIR 3:Overseas study consulting 4:Sales of goods 5:Others


	
	//////////////////////结算信息//////////////////////////////////
	
	private String bank_code;//银行编号
	
	private String bank_name;//银行名称
	
	private String account_name;//账户名
	
	private String account_number;//账号
	
	private String sett_currency;//结算币种
	
	private String check_status;//审核状态 0待审核 1已审核 2驳回
	
	private String system_status;//系统状态 O启用 C禁用
	
	private Date create_date;//创建时间
	
	private String service;// 开通服务:支付宝 线上1  支付宝线下2  微信线上3 微信线下4  银联5 VISAMASTER 6  此字段存字符串  1,2,3..  使用,分割

	private Long mt_sales; //维护销售id
	
	private Long op_sales; //open销售id
	
	private String auth_code; // 线上支付认证码
	
	private String wechat_merchant_no; //微信渠道商户号
	
	private String alipay_merchant_no; //支付宝渠道商户号
	
	private String old_merchant_id; // 旧系统商户ID

	private String virtual_store;
	

}
