package com.attractpay.admin.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import co.attractpay.gateway.constants.SystemConfig;

public class BaseController {
//	@Autowired
//	protected SystemConfig systemConfig;
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());
}
