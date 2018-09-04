package com.example.smm.linearlayoutexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.smm.linearlayoutexample.model.Student;

import java.util.ArrayList;

/**
 * Created by SMM on 10-Oct-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "bdjobs.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableAttributes obj = new TableAttributes();
        String query = obj.tableCreation();

        try {
            db.execSQL(query);
            Log.i("Table Create", "Hoise");
        } catch (SQLException e) {
            Log.e("SQL Error", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertStudent(Student std) {
        ContentValues values = new ContentValues();
        values.put(TableAttributes.STUDENT_NAME, std.getUsername());
        values.put(TableAttributes.PASSWORD, std.getPassword());
        values.put(TableAttributes.CGPA, std.getCgpa());
        values.put(TableAttributes.PHONE_NO, std.getPhoneNo());

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.insert(TableAttributes.TABLE_NAME, null, values);
            Log.i("Insert", "Hoise");
        } catch (SQLException e) {
            Log.e("Insert error", e.toString());
        }finally {
            db.close();
        }
    }

    public ArrayList<Student> getAllStudentData() {
        ArrayList<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TableAttributes.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                Student std = new Student();
                std.setId(cursor.getInt(cursor.getColumnIndex(TableAttributes.STUDENT_ID)));
                std.setUsername(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_NAME)));
                std.setPassword(cursor.getString(cursor.getColumnIndex(TableAttributes.PASSWORD)));
                std.setCgpa(cursor.getFloat(cursor.getColumnIndex(TableAttributes.CGPA)));
                std.setPhoneNo(cursor.getString(cursor.getColumnIndex(TableAttributes.PHONE_NO)));

                studentList.add(std);

                cursor.moveToNext();
            }
        }

        return studentList;
    }

    public boolean deleteStudent(int id) {

        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TableAttributes.TABLE_NAME, TableAttributes.STUDENT_ID + "=" + id, null);
            Log.i("Delete", "Hoise");
            flag = true;
        } catch (SQLException e) {
            Log.e("DeleteErr", e.toString());
        }finally {
            db.close();
        }
        return flag;
    }
}
