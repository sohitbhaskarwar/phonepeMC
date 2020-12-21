package com.logparser.test.service;

import com.logparser.test.model.LogParser;

import java.util.*;
import java.util.Map;

public class sortByFrequency implements Comparator<Map.Entry<String, Integer>>{
    public int compare(Map.Entry<String, Integer> o1,
                         Map.Entry<String, Integer> o2) {
        return o1.getValue() - o2.getValue();
    }
}
