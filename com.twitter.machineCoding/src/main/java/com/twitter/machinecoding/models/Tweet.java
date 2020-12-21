package com.twitter.machinecoding.models;

public class Tweet {
    long id;
    String tweetData;
    String tweetTimeStamp;
    User user;
    Tweet updateTweet;

    public Tweet(long id, String tweetData, String tweetTimeStamp, User user) {
        this.id = id;
        this.tweetData = tweetData;
        this.tweetTimeStamp = tweetTimeStamp;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet getUpdateTweet() {
        return updateTweet;
    }

    public void setUpdateTweet(Tweet updateTweet) {
        this.updateTweet = updateTweet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTweetData() {
        return tweetData;
    }

    public void setTweetData(String tweetData) {
        this.tweetData = tweetData;
    }

    public String getTweetTimeStamp() {
        return tweetTimeStamp;
    }

    public void setTweetTimeStamp(String tweetTimeStamp) {
        this.tweetTimeStamp = tweetTimeStamp;
    }
}
