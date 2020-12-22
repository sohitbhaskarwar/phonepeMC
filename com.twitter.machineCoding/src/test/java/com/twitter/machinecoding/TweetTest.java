package com.twitter.machinecoding;

import com.twitter.machinecoding.models.*;
import com.twitter.machinecoding.service.Manager;
import com.twitter.machinecoding.service.TweetManager;
import com.twitter.machinecoding.service.UserManager;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;

import static com.twitter.machinecoding.service.TweetManager.tweetDetailsMap;
import static com.twitter.machinecoding.service.UserManager.userDetailsMap;

public class TweetTest {
    TweetManager tweetManager = new TweetManager();
    UserManager userManager = new UserManager();
    Manager manager = new Manager();

    @Test
    public void addNewTweet() {
        List<User> userList = addUser();
        int i=1;
        for(User user : userList){
            assertThat(tweetManager.postATweet(new Tweet(i, "Its a Freezing cold today !!!", i + "/DEC", user)), Is.is(true));
        }
        assertThat(tweetDetailsMap.size(), Is.is(5));
    }

    @Test
    public void deleteTweet() {
        addNewTweet();
        addUser();
        tweetDetailsMap.get(1);
        User user = userDetailsMap.get("ARV");

        assertThat(user.getUserTweetList().size(), Is.is(1));
        tweetManager.deleteTweet(user, 5);
        assertThat(user.getUserTweetList().size(), Is.is(0));
    }

    @Test
    public void updateTweet() {
        addNewTweet();
        addUser();

        long tweetID = Long.parseLong("5");
        User user = userDetailsMap.get("ARV");
        Tweet tweet = tweetDetailsMap.get(tweetID);

        assertThat(tweet.getTweetData(), Is.is("Its a Freezing cold today !!!"));
        assertThat(user.getUserTweetList().get(tweetID).getTweetData(), Is.is("Its a Freezing cold today " +
                "!!!"));

        if(tweetManager.updateTweet(tweet, "Its Holiday Today!!!")){
            assertThat(tweet.getTweetData(), Is.is("Its Holiday Today!!!"));
            assertThat(user.getUserTweetList().get(tweetID).getTweetData(), Is.is("Its Holiday Today!!!"));
        }
    }

    public List<User> addUser() {

        ArrayList<User> userList = new ArrayList<>();
        User user = new User("ASD", SystemRole.USER, "asdfzxcv");
        userManager.addNewUser(user);
        userList.add(user);

        user = new User("ZXC", SystemRole.USER, "asdfzxcv");
        userManager.addNewUser(user);
        userList.add(user);

        user = new User("QWE", SystemRole.USER, "asdfzxcv");
        userManager.addNewUser(user);
        userList.add(user);

        user = new User("HRF", SystemRole.ADMIN, "asdfzxcv");
        userManager.addNewUser(user);
        userList.add(user);

        user = new User("ARV", SystemRole.USER, "asdfzxcv");
        userManager.addNewUser(user);
        userList.add(user);

        return userList;
    }

    @Test
    public void AdminUpdateTweet(){
        addNewTweet();
        addUser();

        assertThat(manager.getAllRequest().size(), Is.is(0));

        for(Tweet tweet : tweetManager.listAllTweets()){
            System.out.println(tweet.getId()+" "+tweet.getTweetData());
        }
        manager.requestForUpdateAndDelete(5);
        assertThat(manager.getAllRequest().size(), Is.is(1));

        
        List<SuperAdminRequest> superAdminRequestList = manager.getAllRequest();

        for (SuperAdminRequest sar : superAdminRequestList) {
            System.out.println(sar.getRequestId() + " " + sar.getCrudOperation() + " " + sar.getTweet().getTweetData() + " ");
        }

        System.out.println("Enter request id you want to continue ");
        Integer requestId = 1;
        Tweet tweet = manager.getSuperAdminMap().get(requestId).getTweet();

        manager.processSuperAdminRequest(requestId, RequestOperation.REJECT);
        assertThat(tweet.getTweetData(), Is.is("Its a Freezing cold today !!!"));

        manager.processSuperAdminRequest(requestId, RequestOperation.APPROVE);
        assertThat(tweet.getTweetData(), Is.is("Its XMAS time!!"));
    }
}
