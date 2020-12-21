package com.twitter.machinecoding.service;

import com.twitter.machinecoding.models.Tweet;
import com.twitter.machinecoding.models.User;

import java.util.*;

import static com.twitter.machinecoding.service.UserManager.userDetailsMap;

public class TweetManager {
    Scanner scanner = new Scanner(System.in);
    public static HashMap<Long, Tweet> tweetDetailsMap = new HashMap<>();
    public static long tweetId = 0;

    public boolean postATweet(Tweet tweet) {
        try {
            if (tweet == null)
                throw new Exception("Enter Valid Tweet Details !!!!");
            else {
                tweetId++;
                tweet.setId(tweetId);
                tweetDetailsMap.put(tweetId, tweet);
            }
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteTweet(User user, long tweetID) {
        tweetDetailsMap.remove(tweetID);
        user.getUserTweetList().remove(tweetID);
        return true;
    }

    public Tweet findTweetById(long tweetId) {
        Tweet tweet = tweetDetailsMap.get(tweetId);
        try {
            if (tweet == null) {
                throw new Exception("Enter Valid Tweet Id !!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return tweet;
    }


    public List<Tweet> searchTweetByWord(String word) {
        List<Tweet> tweetList = new ArrayList<>();
        for (Map.Entry<Long, Tweet> mapData : tweetDetailsMap.entrySet()) {
            if (mapData.getValue().getTweetData().matches("*" + word + "*")) {
                tweetList.add(mapData.getValue());
            }
        }
        return tweetList;
    }

    public boolean updateTweet(Tweet tweet, String tweetData) {
        try {
            if (tweet == null) {
                throw new Exception("Tweet with tweetID"+tweet.getId()+" is not present");
            }
            tweet.setTweetData(tweetData);
            tweetDetailsMap.put(tweet.getId(), tweet);

            tweet.getUser().getUserTweetList().put(tweet.getId(), tweet);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
