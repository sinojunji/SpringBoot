package com.attractpay.admin.third.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * /druid监控
 * @author OEM
 *
 */
@WebServlet(urlPatterns = "/druid/*",
        initParams = {
                // IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name = "deny", value = ""),
                // 用户名
                @WebInitParam(name = "loginUsername", value = "admin"),
                // 密码
                @WebInitParam(name = "loginPassword", value = "!Qaz2wsx"),
                // 禁用HTML页面上的“Reset All”功能
                @WebInitParam(name = "resetEnable", value = "false")
        }
)
public class DruidServlet extends StatViewServlet {
    private static final long serialVersionUID = 1L;

}