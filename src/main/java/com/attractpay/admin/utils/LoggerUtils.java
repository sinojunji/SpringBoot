package com.attractpay.admin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.attractpay.admin.common.LogFileName;

/**
 * 根据业务获取输入日志
 * @author ChenHuiLiang
 * @修改时间 2018年7月10日下午4:48:43
 * @version
 */
public class LoggerUtils {
	
    public static <T> Logger Logger(Class<T> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * 打印到指定的文件下
     *
     * @param desc 日志文件名称
     * @return
     */
    public static Logger Logger(LogFileName desc) {
        return LoggerFactory.getLogger(desc.getLogFileName());
    }
}