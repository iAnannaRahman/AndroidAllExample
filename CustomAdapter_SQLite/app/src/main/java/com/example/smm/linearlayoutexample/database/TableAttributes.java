package com.example.smm.linearlayoutexample.database;

/**
 * Created by SMM on 10-Oct-17.
 */

public class TableAttributes {

    public static final String TABLE_NAME = "student";
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_NAME = "name";
    public static final String CGPA = "cgpa";
    public static final String PASSWORD = "password";
    public static final String PHONE_NO = "phoneno";

    public String tableCreation() {
        String tableCreate = "CREATE TABLE " + TABLE_NAME + "(" +
                STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                STUDENT_NAME + " TEXT," +
                PASSWORD + " TEXT," +
                CGPA + " DOUBLE," +
                PHONE_NO + " TEXT " + ")";

        return tableCreate;
    }
}
