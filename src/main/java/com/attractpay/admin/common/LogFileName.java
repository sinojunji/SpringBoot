package com.attractpay.admin.common;

import org.apache.commons.lang3.StringUtils;

public enum LogFileName {

	ONLINE("online"),
    OFFLINE("offline"),
	COMMON("common"),
	NOTIFY("notify"),
	STRESS("stress");
	
	private String logFileName;

    LogFileName(String fileName) {
        this.logFileName = fileName;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public static LogFileName getAwardTypeEnum(String value) {
        LogFileName[] arr = values();
        for (LogFileName item : arr) {
            if (null != item && StringUtils.isNotBlank(item.logFileName)) {
                return item;
            }
        }
        return null;
    }
}
