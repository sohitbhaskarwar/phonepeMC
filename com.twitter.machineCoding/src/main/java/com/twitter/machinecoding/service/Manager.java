package com.twitter.machinecoding.service;

import com.twitter.machinecoding.models.CrudOperation;
import com.twitter.machinecoding.models.RequestOperation;
import com.twitter.machinecoding.models.SuperAdminRequest;
import com.twitter.machinecoding.models.Tweet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static com.twitter.machinecoding.service.TweetManager.tweetDetailsMap;

public class Manager {
    TweetManager tweetManager = new TweetManager();
    Scanner scanner = new Scanner(System.in);
    public static List<SuperAdminRequest> superAdminRequestList = new ArrayList<>();
    public static HashMap<Integer, SuperAdminRequest> superAdminRequestMap = new HashMap<>();
    public static Integer requestCounter = 0;

    public void raiseRequestForSuperAdmin(SuperAdminRequest superAdminRequest) {
        superAdminRequestList.add(superAdminRequest);
        superAdminRequestMap.put(requestCounter, superAdminRequest);
        System.out.println("Request Added for SUPER-ADMIN !!!");
    }

    public void requestForUpdateAndDelete(long tweetID) {
        System.out.println("[1].Update \n[2].Delete");
        Tweet tweet = tweetDetailsMap.get(tweetID);
        // Integer ch = scanner.nextInt();
        Integer ch = 1;
        SuperAdminRequest superAdminRequest = new SuperAdminRequest();
        switch (ch) {
            case 1:
                System.out.println("Update Tweet Data :: (enter data for tweet to update)");
                //Tweet updateTweet = new Tweet(tweet.getId(), scanner.next(), "", tweet.getUser());
                Tweet updateTweet = new Tweet(tweet.getId(), "Its XMAS time!!", "",
                        tweet.getUser());
                requestCounter++;
                superAdminRequest.setRequestId(requestCounter);
                superAdminRequest.setTweet(tweet);
                superAdminRequest.setCrudOperation(CrudOperation.UPDATE);
                superAdminRequest.setUpdatedTweet(updateTweet);

                raiseRequestForSuperAdmin(superAdminRequest);
                break;
            case 2:
                superAdminRequest.setRequestId(requestCounter);
                superAdminRequest.setCrudOperation(CrudOperation.DELETE);
                superAdminRequest.setTweet(tweet);
                raiseRequestForSuperAdmin(superAdminRequest);
                break;
        }
    }

    public List<SuperAdminRequest> getAllRequest() {
        return superAdminRequestList;
    }

    public void processSuperAdminRequest(Integer requestId, RequestOperation approve) {
        SuperAdminRequest sar = superAdminRequestMap.get(requestId);
        switch (approve) {
            case APPROVE:
                switch (sar.getCrudOperation()) {
                    case UPDATE:
                        tweetManager.updateTweet(sar.getTweet(), sar.getUpdatedTweet().getTweetData());
                        superAdminRequestList.remove(sar);
                        break;
                    case DELETE:
                        tweetManager.deleteTweet(sar.getTweet().getUser(), sar.getTweet().getId());
                        superAdminRequestList.remove(sar);
                        break;
                }
                sar.setRequestOperation(RequestOperation.APPROVE);
                break;
            case REJECT:
                superAdminRequestList.remove(sar);
                sar.setRequestOperation(RequestOperation.REJECT);
                break;
        }

    }

    public HashMap<Integer, SuperAdminRequest> getSuperAdminMap() {
        return superAdminRequestMap;
    }
}
