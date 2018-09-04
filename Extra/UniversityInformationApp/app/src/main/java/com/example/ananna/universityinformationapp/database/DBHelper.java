package com.example.ananna.universityinformationapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ananna on 10/10/2017.
 */

public class DBHelper extends SQLiteOpenHelper{
    public static final String TABLE_NAME ="";
    public static final String DATABASE_NAME ="universitydb.sqlite";
    public static final String STUDENT_TABLE_NAME="tblstudent";
    public static final String STUDENT_COLUMN_STID="id";
    public static final String STUDENT_COLUMN_STNAME = "name";

    public static final String STUDENT_COLUMN_STINTAKE="intake";

    public static final String STUDENT_COLUMN_STDEPARTMENT="department";

    public static final String STUDENT_COLUMN_STSECTION="section";

    public static final String STUDENT_COLUMN_STPHONE="phone";

    public static final String INTAKE_TABLE_NAME="tblintake";
    public static final String INTAKE_COLUMN_INTID="id";
    public static final String INTAKE_COLUMN_INTSECTION = "section";

    public static final String DEPARTMENT_TABLE_NAME="tbldepartment";
    public static final String DEPARTMENT_COLUMN_DPTID="id";
    public static final String DEPARTMENT_COLUMN_DPTNAME = "name";
    private HashMap hp;





  /*  private String studentName;
    private String studentId;
    private String studentIntakeId;
    private String studentDepartmentId;


    private String studentSectionId;
    private String studentContact;*/


    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+STUDENT_TABLE_NAME+" (" +STUDENT_COLUMN_STID+ " integer primary key autoincrement,"+
                        STUDENT_COLUMN_STNAME + " text," +STUDENT_COLUMN_STDEPARTMENT+" integer,"+
                        STUDENT_COLUMN_STINTAKE+ " integer," +STUDENT_COLUMN_STSECTION+" integer,"
                        +STUDENT_COLUMN_STPHONE +" text)");

        db.execSQL(
                "create table "+DEPARTMENT_TABLE_NAME+" (" +DEPARTMENT_COLUMN_DPTID+ " integer primary key autoincrement,"+
                        DEPARTMENT_COLUMN_DPTNAME + " text)");
        db.execSQL(
                "create table "+INTAKE_TABLE_NAME+" (" +INTAKE_COLUMN_INTID+ " integer,"+
                        INTAKE_COLUMN_INTSECTION + " integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+STUDENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DEPARTMENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+INTAKE_TABLE_NAME);

        onCreate(db);
    }
    public boolean addSingleStudent(Integer studentid,String studentname,String studentdepartment,String studentintake, String studentsection, String studentphone){
        /*,*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("id", studentid);
        contantValues.put("name",studentname);
        contantValues.put("department",studentdepartment);
        contantValues.put("intake",studentintake);
        contantValues.put("section",studentsection);
        contantValues.put("phone",studentphone);
        db.insert(STUDENT_TABLE_NAME, null, contantValues);
        db.close();
        return true;
    }

    public boolean updateStudentContact(Integer studentid,String studentname,String studentdepartment,String studentintake, String studentsection, String studentphone)
    {
        /*,String contactname,*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("id", studentid);
        contantValues.put("name",studentname);
        contantValues.put("department",studentdepartment);
        contantValues.put("intake",studentintake);
        contantValues.put("section",studentsection);
        contantValues.put("phone",studentphone);
        db.update(STUDENT_TABLE_NAME, contantValues, "id = ?", new String[]{Integer.toString(studentid)});
        db.close();
        return true;
    }

    public Integer deleteContact(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(STUDENT_TABLE_NAME,"id = ?",new String[]{Integer.toString(id)});
    }
    public Cursor getData(int studentid){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+STUDENT_TABLE_NAME+" where id = " + studentid + "", null);
        return res;
    }
    public ArrayList<String> getAllStudent(){
        ArrayList<String> arraylist= new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from mycontacts",null);

        if (cursor.moveToFirst()) {
            do {
                arraylist.add(cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_STNAME)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_STDEPARTMENT)));
               /* arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUSTREET)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUEMAIL)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUCITY)));*/
            } while (cursor.moveToNext());
        }
        return arraylist;
    }


    public boolean addDepartment(Integer departmentid,String departmentname){
        /*,*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("id", departmentid);
        contantValues.put("name",departmentname);
        db.insert(DEPARTMENT_TABLE_NAME, null, contantValues);
        db.close();
        return true;
    }

}
