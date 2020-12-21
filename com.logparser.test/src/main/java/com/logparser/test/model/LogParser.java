package com.logparser.test.model;

public class LogParser {
    String timeStamp;
    String actualURL;
    String maskedURL;
    String methodName;
    Integer responseTime;
    Integer responseCode;

    public LogParser(String timeStamp, String actualURL, String maskedURL, String methodName,
                     Integer responseTime, Integer responseCode) {
        this.timeStamp = timeStamp;
        this.actualURL = actualURL;
        this.maskedURL = maskedURL;
        this.methodName = methodName;
        this.responseTime = responseTime;
        this.responseCode = responseCode;
    }


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getActualURL() {
        return actualURL;
    }

    public void setActualURL(String actualURL) {
        this.actualURL = actualURL;
    }

    public String getMaskedURL() {
        return maskedURL;
    }

    public void setMaskedURL(String maskedURL) {
        this.maskedURL = maskedURL;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
}
