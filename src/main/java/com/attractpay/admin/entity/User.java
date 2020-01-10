package com.attractpay.admin.entity;

import java.util.Date;

import com.attractpay.admin.common.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * 商户用户
 * @author ChenHuiLiang
 * @修改时间 2018年4月18日上午10:30:43
 * @version
 */
@TableName(value="t_user")
@Data
public class User {

    /**
     *
     */
    private Long id;// 主键id

    private String user_no;//用户码
    private String username;// 用户名
    private String password;// 密码
    private Date create_time;// 创建时间
    private Long create_by;// 由谁创建
    private Integer locked;//锁定0 未锁定 1
    private Integer status;//状态 0正常 1逻辑删除 2用户对应的商户/店铺 被作废
    private String salt;
    private Integer type; // 用户对应的类型(系统管理员1/销售2/合规3/财务4/商户5/店铺6/店铺组7/二级代理8/店铺cashier9)
    private Long type_id; // 对应的类型的ID值
    private String nickname; //  昵称
    private Date update_date;
    private Long update_by;

    private Date signup_time;       //注册时间
    private Date last_login_time;   //最近登陆时间

}
