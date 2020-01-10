package com.attractpay.admin.entity;

import java.util.Date;

import com.attractpay.admin.common.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@TableName("t_merchant")
public class Merchant {

	private Long id;
	
	private String merchant_code;//NZ0064
	
	private String registration_name;//注册名称
	
	private String registration_code;//注册编号 NZBN
	
	private String registration_region;//注册地区
	
	private String registration_district;//注册区域
	
	private String registration_address;//注册详细地址
	
	private String show_name;//显示名称
	
	private String industry_id;//所属行业ID
	
	private String website;//官网
	
	private String contact;//联系人
	
	private String phone;//电话
	
	private String email;//邮箱
	
	private String risk_classification;//风险分级  LOW(默认)、MID、HIGH
	
	private String cdd;//CDD  STANDARD(默认)、SIMPLELIFIED、ENHANCED
	
	private String check_status;//审核状态:  0待审核    1 已审核通过   2驳回
	
	private String system_status;//系统状态 O开C关
	
	private Date create_date;//创建时间

    
}
