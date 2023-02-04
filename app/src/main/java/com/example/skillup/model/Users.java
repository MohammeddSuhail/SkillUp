package com.example.skillup.model;

public class Users {
    String userName,yearOfGrad,course,branch,currYear,profession,city,status, profileImage;
    String usn, phoneNo;
    Boolean setupFlag; //to check if the user completed setup part

    public Users() {
    }

    public String getUsn() {
        return usn;
    }

    public Users(String userName, String yearOfGrad, String course, String branch, String currYear, String profession, String city, String profileImage, String status, Boolean setupFlag) {
        this.userName = userName;
        this.yearOfGrad = yearOfGrad;
        this.course = course;
        this.branch = branch;
        this.currYear = currYear;
        this.profession = profession;
        this.city = city;
        this.profileImage = profileImage;
        this.status = status;
        this.setupFlag = setupFlag;
    }



    public Users(String usn, String phoneNo, Boolean setupFlag) {
        this.usn = usn;
        this.phoneNo = phoneNo;
        this.setupFlag = setupFlag;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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

    public String getYearOfGrad() {
        return yearOfGrad;
    }

    public void setYearOfGrad(String yearOfGrad) {
        this.yearOfGrad = yearOfGrad;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCurrYear() {
        return currYear;
    }

    public void setCurrYear(String currYear) {
        this.currYear = currYear;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
