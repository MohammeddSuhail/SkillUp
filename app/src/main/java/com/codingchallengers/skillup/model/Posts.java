package com.codingchallengers.skillup.model;

public class Posts {
    //must have the same name as the fields of firebase
    private String datePost,postDec,postImageUrl,userProfileImage,username,userId,com, postUsn;

    public Posts(String datePost, String postDec, String postImageUrl, String userProfileImage, String username, String userId, String com, String postUsn) {
        this.datePost = datePost;
        this.postDec = postDec;
        this.postImageUrl = postImageUrl;
        this.userProfileImage = userProfileImage;
        this.username = username;
        this.userId = userId;
        this.com = com;
        this.postUsn = postUsn;
    }

    public Posts(String datePost, String postDec, String postImageUrl, String userProfileImage, String username, String userId) {
        this.datePost = datePost;
        this.postDec = postDec;
        this.postImageUrl = postImageUrl;
        this.userProfileImage = userProfileImage;
        this.username = username;
        this.userId = userId;

    }

    public Posts() {
    }

    public Posts(String datePost, String postDec, String postImageUrl, String userProfileImage, String username) {
        this.datePost = datePost;
        this.postDec = postDec;
        this.postImageUrl = postImageUrl;
        this.userProfileImage = userProfileImage;
        this.username = username;
    }

    public Posts(String datePost, String postDec, String postImageUrl, String userProfileImage, String username, String userId, String com) {
        this.datePost = datePost;
        this.postDec = postDec;
        this.postImageUrl = postImageUrl;
        this.userProfileImage = userProfileImage;
        this.username = username;
        this.userId = userId;
        this.com = com;
    }

    public String getPostUsn() {
        return postUsn;
    }

    public void setPostUsn(String postUsn) {
        this.postUsn = postUsn;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getPostDec() {
        return postDec;
    }

    public void setPostDec(String postDec) {
        this.postDec = postDec;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
