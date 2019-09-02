package com.yw.log;

public enum CommonLogType {
    SYS_CONTROLLER("sys.controller"), SYS_BIZ("sys.biz"), SYS_INTEGRATION("sys.integration"), SYS_SERVICE(
            "sys.service"), SYS_COMMON("sys.common"), SYS_DAL(
                    "sys.dal"), SYS_ERRORPOLICY("sys.errorpolicy"), SYS_PERFORMANCE("sys.performance");

    private String logName;

    private CommonLogType(String logName) {
        this.logName = logName;
    }

    public String getLogName() {
        return logName;
    }
}
