package com.twitter.machinecoding;

import com.twitter.machinecoding.models.SystemRole;
import com.twitter.machinecoding.models.User;
import com.twitter.machinecoding.service.UserManager;
import org.hamcrest.core.Is;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import static com.twitter.machinecoding.service.TweetManager.tweetDetailsMap;
import static com.twitter.machinecoding.service.UserManager.userDetailsMap;

public class Test1 {
    UserManager userManager = new UserManager();
    @Test
    public void creteNewUser(){

        assertThat(userManager.addNewUser(new User("sohit16", SystemRole.USER,"asdfzxcv")), Is.is(true));
        assertThat(userManager.addNewUser(new User("NMO", SystemRole.USER,"jkl")), Is.is(true));
        assertThat(userManager.addNewUser(new User("ASD", SystemRole.USER,"utcsf")), Is.is(true));
        assertThat(userManager.addNewUser(new User("sohXCVit16", SystemRole.USER,"sfs")), Is.is(true));
        assertThat(userManager.addNewUser(new User("wewd", SystemRole.USER,"fsfd")), Is.is(true));

        assertThat(userDetailsMap.size(), Is.is(5));
        assertThat(userDetailsMap.size()==4, Is.is(false));

    }

    @Test
    public void deleteUser(){
        creteNewUser();

        assertThat(userManager.deleteUserByUserName("NMO"), Is.is(true));
        assertThat(userManager.deleteUserByUserName("NM222O"), Is.is(false));

        assertThat(userDetailsMap.size(), Is.is(4));
    }
}
