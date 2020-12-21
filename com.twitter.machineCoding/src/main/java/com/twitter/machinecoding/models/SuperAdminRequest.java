package com.twitter.machinecoding.models;

public class SuperAdminRequest {
    Integer requestId;
    Tweet tweet;
    CrudOperation crudOperation;
    RequestOperation requestOperation;
    Tweet updatedTweet;

    public RequestOperation getRequestOperation() {
        return requestOperation;
    }

    public void setRequestOperation(RequestOperation requestOperation) {
        this.requestOperation = requestOperation;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Tweet getUpdatedTweet() {
        return updatedTweet;
    }

    public void setUpdatedTweet(Tweet updatedTweet) {
        this.updatedTweet = updatedTweet;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public CrudOperation getCrudOperation() {
        return crudOperation;
    }

    public void setCrudOperation(CrudOperation crudOperation) {
        this.crudOperation = crudOperation;
    }
}
