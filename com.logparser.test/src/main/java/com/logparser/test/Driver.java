package com.logparser.test;

import com.logparser.test.model.LogParser;
import com.logparser.test.model.ResultURL;
import com.logparser.test.service.Manager;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Driver {

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.convertCSVData("E:\\Git\\com.logparser" +
                ".test\\src\\main\\resources\\csv_input.csv");

        try {
            List<Map.Entry<String, Integer>> logParserList = manager.getTop5URL();

            for (Map.Entry<String, Integer> data : logParserList) {

                String maskedURL = data.getKey().split("-")[0];
                String methodName = data.getKey().split("-")[1];
                Integer frequency = data.getValue();

                System.out.println(maskedURL + " " + methodName + " " + frequency);
            }


            List<ResultURL> resultList = manager.getStatsOfMethods();
            for (ResultURL resultURL : resultList) {
                System.out.println(resultURL.getLogParser().getMethodName() + " " + resultURL.getLogParser().getMaskedURL() + " " + resultURL.getMinimumTime() + " " + resultURL.getMaximumTime() + " " + resultURL.getAverageTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
