package com.twitter.machinecoding;

import com.twitter.machinecoding.models.*;
import com.twitter.machinecoding.service.Manager;
import com.twitter.machinecoding.service.TweetManager;
import com.twitter.machinecoding.service.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.twitter.machinecoding.service.TweetManager.tweetId;

public class Driver {
    public static void main(String[] args) {
        String userName, password;
        boolean loop = true;
        try {
            while (loop) {
                Scanner scanner = new Scanner(System.in);
                UserManager userManager = new UserManager();
                TweetManager tweetManager = new TweetManager();
                Manager manager = new Manager();


                System.out.println("Enter your choice\n[1].Sign Up !!" +
                        "\n[2].Login \n[3].Exit");
                Integer ch = scanner.nextInt();
                switch (ch) {
                    case 1:
                        System.out.println("Enter UserName");
                        userName = scanner.next();
                        password = scanner.next();
                        userManager.addNewUser(new User(userName, SystemRole.USER, password));
                        break;

                    case 2:
                        System.out.println("Enter UserName:");
                        userName = scanner.next();
                        System.out.println("Enter Password:");
                        password = scanner.next();
                        if (userManager.authenticateUser(userName, password)) {
                            User user = userManager.getUserDetails(userName);
                            switch (user.getSystemRole()) {
                                case USER:
                                    while (true) {
                                        System.out.println("Enter your choice \n[1]. List All Tweets \n[2].Post " +
                                                "New Tweet \n[3].Delete");

                                        Integer userChoice = scanner.nextInt();
                                        switch (userChoice) {
                                            case 1:
                                                for (Map.Entry<Long, Tweet> tweet : user.getUserTweetList().entrySet())
                                                    System.out.println(tweet.getKey() + " " + tweet.getValue().getTweetData());
                                                break;
                                            case 2:
                                                System.out.println("Write A Tweet!!!");
                                                Tweet newTweet = new Tweet(tweetId, scanner.next(), "", user);
                                                tweetManager.postATweet(newTweet);

                                                System.out.println("Your Tweet has been posted " +
                                                        "successfully!!!");
                                                break;
                                            case 3:
                                                System.out.println("Enter Tweet id which you want to delete");
                                                for (Map.Entry<Long, Tweet> tweet : user.getUserTweetList().entrySet())
                                                    System.out.println(tweet.getKey() + " " + tweet.getValue().getTweetData());

                                                tweetManager.deleteTweet(user, scanner.nextInt());
                                                break;
                                        }
                                    }
                                case ADMIN:
                                    System.out.println("Enter Tweet id which you want to Update!!");
                                    for (Map.Entry<Long, Tweet> tweet : user.getUserTweetList().entrySet())
                                        System.out.println(tweet.getKey() + " " + tweet.getValue().getTweetData());

                                    manager.requestForUpdateAndDelete(scanner.nextInt());
                                    break;
                                case SUPERADMIN:
                                    System.out.println("Request Need to be Responded :: ");
                                    List<SuperAdminRequest> superAdminRequestList = manager.getAllRequest();

                                    for (SuperAdminRequest sar : superAdminRequestList) {
                                        System.out.println(sar.getRequestId() + " " + sar.getCrudOperation() + " " + sar.getTweet().getTweetData() + " ");
                                    }

                                    System.out.println("Enter request id you want to continue ");
                                    Integer requesId = scanner.nextInt();
                                    System.out.println("Operation you want to do [1]." + RequestOperation.APPROVE + "\n[2]." + RequestOperation.REJECT);
                                    Integer requestOp = scanner.nextInt();

                                    manager.processSuperAdminRequest(requesId, RequestOperation.APPROVE);
                                    break;
                            }


                        }
                        break;
                    case 3:
                        loop = false;
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
