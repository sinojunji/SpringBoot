package com.attractpay.admin.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler
	@ResponseBody
	public MessageObject exceptionHandle(HttpServletRequest req, Exception e) throws Exception{
		StackTraceElement element = e.getStackTrace()[0];
		
		logger.error("<" + e.getMessage() + ">" + " at " + element.getClassName() + "(" + element.getFileName()  + ":" + element.getLineNumber() + ")");
		return new MessageObject(MessageObject.ERROR_COMMON,"系统异常");
	}
}

