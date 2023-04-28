package com.codingchallengers.skillup.model;

public class Comment {
        String username,profileImage,comment,commentDate,userId;


    public Comment() {
        }

        public Comment(String username, String profileImage, String comment) {
            this.username = username;
            this.profileImage = profileImage;
            this.comment = comment;
        }

    public Comment(String username, String profileImage, String comment, String commentDate) {
        this.username = username;
        this.profileImage = profileImage;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public Comment(String username, String profileImage, String comment, String commentDate, String userId) {
        this.username = username;
        this.profileImage = profileImage;
        this.comment = comment;
        this.commentDate = commentDate;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
}
