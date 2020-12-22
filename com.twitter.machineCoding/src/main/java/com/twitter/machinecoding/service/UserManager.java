package com.twitter.machinecoding.service;

import com.twitter.machinecoding.models.Tweet;
import com.twitter.machinecoding.models.User;

import java.util.*;

public class UserManager {

    public static HashMap<String, User> userDetailsMap = new HashMap<>();

    public boolean addNewUser(User user) {

        try {
            if (user == null)
                throw new Exception("Enter Valid User Details !!!!");
            if (!userDetailsMap.containsKey(user.getUserName())) {
                userDetailsMap.put(user.getUserName(), user);
                return true;
            } else {
                System.out.println("User already present with Id " + user.getUserName() + " get " +
                        "another " +
                        "Id!!!");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean authenticateUser(String userName, String password) {
        try {
            if (userName == null)
                throw new Exception("UserName not Provided !!!!\n Enter username!!!");
            if (userDetailsMap.containsKey(userName)) {
                User user = userDetailsMap.get(userName);
                if (user.getUserPassword().equals(password)) {
                    return true;
                } else {
                    throw new Exception("Incorrect Password!!!!");
                }
            } else {
                throw new Exception("No user present with this username!! \n Please Sign " +
                        "up!!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User getUserDetails(String userName) {
        UserManager userManager = new UserManager();
        return userDetailsMap.get(userName);
    }

    public boolean deleteUserByUserName(String userName) {
        try {
            if (userDetailsMap.containsKey(userName)) {
                userDetailsMap.remove(userName);
                return true;
            } else {
                System.out.println("No user present with username: " + userName + " !!");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


}
