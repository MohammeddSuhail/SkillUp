package com.example.skillup.model;

public class Users {
    String userName,city,country,profession,status, profileImage;
    Boolean setupFlag;

    public Users() {
    }

    public Users(String userName, String city, String country, String profession, String profileImage, String status, Boolean setupFlag) {
        this.userName = userName;
        this.city = city;
        this.country = country;
        this.profession = profession;
        this.status = status;
        this.profileImage = profileImage;
        this.setupFlag = setupFlag;
    }

    public Boolean getSetupFlag() {
        return setupFlag;
    }

    public void setSetupFlag(Boolean setupFlag) {
        this.setupFlag = setupFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
