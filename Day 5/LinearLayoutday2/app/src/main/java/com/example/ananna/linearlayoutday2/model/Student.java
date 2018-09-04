package com.example.ananna.linearlayoutday2.model;

/**
 * Created by Ananna on 10/5/2017.
 */

public class Student {
    private String userName;
    private String userPassword;
    private String userPhone;
    private Float userCGPA;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Float getUserCGPA() {
        return userCGPA;
    }

    public void setUserCGPA(Float userCGPA) {
        this.userCGPA = userCGPA;
    }


    @Override
    public String toString() {
        return "Student Name =" + userName + ",\n " +
                " CGPA = " + userCGPA + ",\n " +

                " Phone = " + userPhone
                ;
    }

}
