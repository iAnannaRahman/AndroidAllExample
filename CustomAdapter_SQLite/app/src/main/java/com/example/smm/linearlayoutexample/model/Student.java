package com.example.smm.linearlayoutexample.model;

/**
 * Created by SMM on 05-Oct-17.
 */

public class Student {
    private int id;
    private String username;
    private String password;
    private Float cgpa;
    private String phoneNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getCgpa() {
        return cgpa;
    }

    public void setCgpa(Float cgpa) {
        this.cgpa = cgpa;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "Username: " + username;
    }

    public String toStringForDialog(){
        return "Username: " + username + '\n'
                + "CGPA: " + cgpa + '\n'
                + "Phone no.: " + phoneNo;
    }
}

