package com.twitter.machinecoding.models;

import java.util.HashMap;
import java.util.List;

public class User {


    String userName;
    SystemRole systemRole;
    String userPassword;
    HashMap<Long, Tweet> userTweetList;

    public User( String userName, SystemRole systemRole, String userPassword) {
        this.userName = userName;
        this.systemRole = systemRole;
        this.userPassword = userPassword;
    }
    public User(){}

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public HashMap<Long, Tweet> getUserTweetList() {
        return userTweetList;
    }

    public void setUserTweetList(HashMap<Long, Tweet> userTweetList) {
        this.userTweetList = userTweetList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public SystemRole getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }
}
