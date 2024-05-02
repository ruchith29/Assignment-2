package com.nextrow.json.to.csv.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;


public class Student {

    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("branch")
    private String branch;
    @JsonProperty("address")
    private String address;
    @JsonProperty("marks")
    private HashMap<String,Integer> marks;


    @JsonProperty("totalmarks")
    private int totalmarks;

/*    @JsonProperty("averagemarks")
    private double averagemarks;*/

    @JsonProperty("percentage")
    private double percentage;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, Integer> getMarks() {
        return marks;
    }

    public void setMarks(HashMap<String, Integer> marks) {
        this.marks = marks;
    }

    public double getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(int totalmarks) {
        this.totalmarks = totalmarks;
    }


    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

/*    public double getAveragemarks() {
        return averagemarks;
    }

    public void setAveragemarks(double averagemarks) {
        this.averagemarks = averagemarks;
    }*/
}
