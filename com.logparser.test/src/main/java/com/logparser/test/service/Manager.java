package com.logparser.test.service;

import com.logparser.test.model.LogParser;
import com.logparser.test.model.ResultURL;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;

public class Manager {

    //public static HashMap<String, List<LogParser>> logparserMap = new HashMap<>();
    public static HashMap<String, HashMap<String, List<LogParser>>> logparserMap =
            new HashMap<>(); // string-> maskedURl key-> HASHMAP  (key-> methodName
    // value->List<LogParser>)
    //

    public void convertCSVData(String filePath) {
        try {
            FileReader filereader = new FileReader(filePath);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {


                LogParser logParser = new LogParser((nextRecord[0]), nextRecord[1],
                        convertMaskedURL(nextRecord[1]),
                        nextRecord[2], Integer.parseInt(nextRecord[3]),
                        Integer.parseInt(nextRecord[4]));


                if (logparserMap.containsKey(logParser.getMaskedURL())) {
                    HashMap<String, List<LogParser>> map =
                            logparserMap.get(logParser.getMaskedURL());
                    List<LogParser> logParsersList = map.get(logParser.getMethodName());
                    if (logParsersList == null) {
                        logParsersList = new ArrayList<>();
                        logParsersList.add(logParser);
                        map.put(logParser.getMethodName(), logParsersList);
                    } else {
                        logParsersList.add(logParser);
                        map.put(logParser.getMethodName(), logParsersList);
                        logparserMap.put(logParser.getMaskedURL(), map);
                    }

                } else {
                    List<LogParser> logParsersList = new ArrayList<>();
                    HashMap<String, List<LogParser>> map =
                            new HashMap<>();
                    logParsersList.add(logParser);
                    map.put(logParser.getMethodName(), logParsersList);
                    logparserMap.put(logParser.getMaskedURL(), map);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map.Entry<String,Integer>> getTop5URL() {
        List<Map.Entry<String,Integer>> resultList = new LinkedList<>();
        HashMap<String, Integer> resultMap = new HashMap<>();
        HashMap<String, List<LogParser>> list = new HashMap<>();

        for (HashMap.Entry<String, HashMap<String, List<LogParser>>> entry : logparserMap.entrySet()) {
            for (Map.Entry<String, List<LogParser>> map : entry.getValue().entrySet()) {
                resultMap.put(entry.getKey()+"-"+map.getKey(), map.getValue().size());
            }
        }

        List<Map.Entry<String,Integer>> freqUrlList = new LinkedList<>(resultMap.entrySet());

        Collections.sort(freqUrlList, new sortByFrequency());

        for (int i= freqUrlList.size()-1 ;i>=freqUrlList.size()-5;i--) {
            resultList.add(freqUrlList.get(i));
        }
        return resultList;
    }

    public ArrayList<ResultURL> getStatsOfMethods() {
        ArrayList<ResultURL> resultList = new ArrayList<>();
        try {

            for (HashMap.Entry<String, HashMap<String, List<LogParser>>> entry : logparserMap.entrySet()) {
                for (Map.Entry<String, List<LogParser>> map : entry.getValue().entrySet()) {
                    List<LogParser> list = map.getValue();
                    Integer minTime = list.get(0).getResponseTime();
                    Integer maxTime = list.get(0).getResponseTime();
                    Integer totalTime = 0;
                    LogParser lpData = null;
                    for (LogParser lp : list) {
                        minTime = Math.min(minTime, lp.getResponseTime());
                        maxTime = Math.max(maxTime, lp.getResponseTime());
                        totalTime += lp.getResponseTime();
                        lpData = lp;
                    }
                    ResultURL resultURL = new ResultURL();
                    resultURL.setMinimumTime(minTime);
                    resultURL.setMaximumTime(maxTime);
                    resultURL.setAverageTime((totalTime / list.size()));
                    resultURL.setLogParser(lpData);

                    resultList.add(resultURL);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public boolean validateURl(String url) {
        try {
            Integer data = Integer.parseInt(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public String convertMaskedURL(String actualURL) {
        try {
            if (actualURL == null) {
                throw new Exception("Input URL in CSV cannot be null");
            }

            String logParserData[] = actualURL.split("/");
            String maskedUrl = "/";
            for (int i = 1; i < logParserData.length; i++) {
                if (validateURl(logParserData[i])) {
                    maskedUrl += "/{id}";
                } else {

                    maskedUrl += "/" + logParserData[i];
                }
            }
            return maskedUrl;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void storeCsvData(String filePath) {

    }
}
