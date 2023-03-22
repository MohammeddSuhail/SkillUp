package com.codingchallengers.skillup.model;

public class Experience {
    String CTCOffered, adviceToFreshers, companyName, role, rounds, selected, type;
    int campusDriveYear;

    public Experience() {
    }

    public Experience(String CTCOffered, String adviceToFreshers, String companyName, String role, String rounds, String selected, String type, int campusDriveYear) {
        this.CTCOffered = CTCOffered;
        this.adviceToFreshers = adviceToFreshers;
        this.companyName = companyName;
        this.role = role;
        this.rounds = rounds;
        this.selected = selected;
        this.type = type;
        this.campusDriveYear = campusDriveYear;
    }

    public String getCTCOffered() {
        return CTCOffered;
    }

    public void setCTCOffered(String CTCOffered) {
        this.CTCOffered = CTCOffered;
    }

    public String getAdviceToFreshers() {
        return adviceToFreshers;
    }

    public void setAdviceToFreshers(String adviceToFreshers) {
        this.adviceToFreshers = adviceToFreshers;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRounds() {
        return rounds;
    }

    public void setRounds(String rounds) {
        this.rounds = rounds;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCampusDriveYear() {
        return campusDriveYear;
    }

    public void setCampusDriveYear(int campusDriveYear) {
        this.campusDriveYear = campusDriveYear;
    }
}
