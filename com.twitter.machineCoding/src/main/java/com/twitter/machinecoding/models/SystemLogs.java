package com.twitter.machinecoding.models;

public class SystemLogs {
    LogType logType;
    String logData;
    String logTimestamp;

    public SystemLogs(LogType logType, String logData, String logTimestamp) {
        this.logType = logType;
        this.logData = logData;
        this.logTimestamp = logTimestamp;
    }

    public SystemLogs(LogType logType, String logData) {
        this.logType = logType;
        this.logData = logData;
    }

    public String getLogTimestamp() {
        return logTimestamp;
    }

    public void setLogTimestamp(String logTimestamp) {
        this.logTimestamp = logTimestamp;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }
}
