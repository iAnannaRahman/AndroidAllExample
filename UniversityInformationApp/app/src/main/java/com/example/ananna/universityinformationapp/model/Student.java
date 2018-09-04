package com.example.ananna.universityinformationapp.model;

/**
 * Created by Ananna on 10/10/2017.
 */

public class Student {

    private String studentName;


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMothherName() {
        return mothherName;
    }

    public void setMothherName(String mothherName) {
        this.mothherName = mothherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String fatherName;
    private String mothherName;
    private String address;
    private String studentIntakeId;
    private String studentDepartmentId;
    private String studentSectionId;
    private String studentPhone;
    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    public String getStudentIntakeId() {
        return studentIntakeId;
    }

    public void setStudentIntakeId(String studentIntakeId) {
        this.studentIntakeId = studentIntakeId;
    }



    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }
    //private String CGPA;


    public String getStudentDepartmentId() {
        return studentDepartmentId;
    }

    public void setStudentDepartmentId(String studentDepartmentId) {
        this.studentDepartmentId = studentDepartmentId;
    }



    public String getStudentSectionId() {
        return studentSectionId;
    }

    public void setStudentSectionId(String studentSectionId) {
        this.studentSectionId = studentSectionId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentIntakeId='" + studentIntakeId + '\'' +
                '}';
    }
}
