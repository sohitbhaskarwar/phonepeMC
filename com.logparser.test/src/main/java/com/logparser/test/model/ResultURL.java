package com.logparser.test.model;

public class ResultURL {
    Integer minimumTime;
    Integer maximumTime;
    long  averageTime;
    LogParser logParser;
    Integer frequency;

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getMinimumTime() {
        return minimumTime;
    }

    public void setMinimumTime(Integer minimumTime) {
        this.minimumTime = minimumTime;
    }

    public Integer getMaximumTime() {
        return maximumTime;
    }

    public void setMaximumTime(Integer maximumTime) {
        this.maximumTime = maximumTime;
    }

    public long getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(long averageTime) {
        this.averageTime = averageTime;
    }

    public LogParser getLogParser() {
        return logParser;
    }

    public void setLogParser(LogParser logParser) {
        this.logParser = logParser;
    }
}
