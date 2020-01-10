package com.attractpay.admin.entity;

import java.io.Serializable;
import java.util.Date;

import com.attractpay.admin.common.annotation.TableName;

@TableName(value="t_transaction")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 8195957285247885933L;

	private Long id;
	
	private Long merchant_id;//商户主键
	
	private Long store_id;//店铺主键
	
	private String trans_no;//系统流水号
	
	private String channel_trans_no;//通道流水号
	
	private Date create_date;//创建日期
	
	private Date payment_date;// 支付时间
	
	private double amount;//金额 用户输入金额
	
	private double paid_amount;//支付金额  
	
	private double st_amount;//结算金额
	
	private double sc_amount;//收店铺 手续费
	
	private double sx_amount;//收客人 手续费
	
	private double rf_amount;//退款金额
	
	private double discount;//折扣金额
	
	private String currency;//币种
	
	private String type;//线上线下
	
	private String channel;//支付宝微信
	
	private String comment;//备注
	
	private String status;//状态 SUCCESS/FAILED/REFUND/REFUNED/CLOSED/CANCELED  P:创建订单/S:订单支付成功/C:关闭或失败/R:已全部退款/E:已有部分退款
	
	////////////线上/////////////////
	
	private String notification_url;//通知地址 仅线上有效
	
	private String notification_status;//通知状态 SUCCESS/FAILED
	
	private int notification_count;//通知次数
	
	private String merchant_trans_no; // 线上支付商户传入流水号

	private Long sales_id; // 销售id
	
	private Double rate;//汇率
	
	private Date settle_date; // 结算时间
	
	private Double settle_feerate; // 结算费率

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(Long merchant_id) {
		this.merchant_id = merchant_id;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public String getTrans_no() {
		return trans_no;
	}

	public void setTrans_no(String trans_no) {
		this.trans_no = trans_no;
	}

	public String getChannel_trans_no() {
		return channel_trans_no;
	}

	public void setChannel_trans_no(String channel_trans_no) {
		this.channel_trans_no = channel_trans_no;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
	}

	public double getSt_amount() {
		return st_amount;
	}

	public void setSt_amount(double st_amount) {
		this.st_amount = st_amount;
	}

	public double getSc_amount() {
		return sc_amount;
	}

	public void setSc_amount(double sc_amount) {
		this.sc_amount = sc_amount;
	}

	public double getSx_amount() {
		return sx_amount;
	}

	public void setSx_amount(double sx_amount) {
		this.sx_amount = sx_amount;
	}

	public double getRf_amount() {
		return rf_amount;
	}

	public void setRf_amount(double rf_amount) {
		this.rf_amount = rf_amount;
	}
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotification_url() {
		return notification_url;
	}

	public void setNotification_url(String notification_url) {
		this.notification_url = notification_url;
	}

	public String getNotification_status() {
		return notification_status;
	}

	public void setNotification_status(String notification_status) {
		this.notification_status = notification_status;
	}

	public int getNotification_count() {
		return notification_count;
	}

	public void setNotification_count(int notification_count) {
		this.notification_count = notification_count;
	}

	public String getMerchant_trans_no() {
		return merchant_trans_no;
	}

	public void setMerchant_trans_no(String merchant_trans_no) {
		this.merchant_trans_no = merchant_trans_no;
	}

	public Long getSales_id() {
		return sales_id;
	}

	public void setSales_id(Long sales_id) {
		this.sales_id = sales_id;
	}

	public Date getSettle_date() {
		return settle_date;
	}

	public void setSettle_date(Date settle_date) {
		this.settle_date = settle_date;
	}

	public Double getSettle_feerate() {
		return settle_feerate;
	}

	public void setSettle_feerate(Double settle_feerate) {
		this.settle_feerate = settle_feerate;
	}
	
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", merchant_id=" + merchant_id + ", store_id=" + store_id + ", trans_no="
				+ trans_no + ", channel_trans_no=" + channel_trans_no + ", create_date=" + create_date
				+ ", payment_date=" + payment_date + ", amount=" + amount + ", paid_amount=" + paid_amount
				+ ", st_amount=" + st_amount + ", sc_amount=" + sc_amount + ", sx_amount=" + sx_amount + ", rf_amount="
				+ rf_amount + ", discount=" + discount + ", currency=" + currency + ", type=" + type + ", channel="
				+ channel + ", comment=" + comment + ", status=" + status + ", notification_url=" + notification_url
				+ ", notification_status=" + notification_status + ", notification_count=" + notification_count
				+ ", merchant_trans_no=" + merchant_trans_no + ", sales_id=" + sales_id + ", settle_date=" + settle_date
				+ ", settle_feerate=" + settle_feerate + ", rate=" + rate + "]";
	}
	
}
