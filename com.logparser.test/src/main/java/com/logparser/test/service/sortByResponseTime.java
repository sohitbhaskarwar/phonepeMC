package com.logparser.test.service;

import com.logparser.test.model.LogParser;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class sortByResponseTime implements Comparator<LogParser> {
public int compare( LogParser l1,
        LogParser l2) {
        return l1.getResponseTime() - l2.getResponseTime();
        }
}
