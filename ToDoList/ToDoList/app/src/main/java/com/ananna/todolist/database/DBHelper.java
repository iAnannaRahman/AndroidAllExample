package com.ananna.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ananna.todolist.model.ToDo;
import com.ananna.todolist.model.ToDoItems;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ppandey on 12/15/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tododb.sqlite";
    public static final String CONTACTS_TABLE_NAME = "mycontacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_STUNAME = "name";
    public static final String CONTACTS_COLUMN_STUPHONE = "phone";
    public static final String CONTACTS_COLUMN_STUSTREET = "street";
    public static final String CONTACTS_COLUMN_STUEMAIL = "email";
    public static final String CONTACTS_COLUMN_STUCITY = "place";

    public static final String ECONTACTS_TABLE_NAME = "myenemycontacts";
    public static final String ECONTACTS_COLUMN_ID = "enemyid";
    public static final String ECONTACTS_COLUMN_STUNAME = "enemyname";
    public static final String ECONTACTS_COLUMN_STUPHONE = "enemyphone";
    public static final String ECONTACTS_COLUMN_STUSTREET = "enemystreet";
    public static final String ECONTACTS_COLUMN_STUEMAIL = "enemyemail";
    public static final String ECONTACTS_COLUMN_STUCITY = "enemyplace";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table todo " +
                        "(id integer primary key autoincrement, title text)");
        db.execSQL(
                "create table todoItem " +
                        "(id integer primary key autoincrement, todoId integer,tododescription text,isChecked int)"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todo");
        db.execSQL("DROP TABLE IF EXISTS todoItem");

        onCreate(db);
    }

    public boolean addToDo(ToDo item) {
        long result = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues contantValues = new ContentValues();
            contantValues.put("title", item.getTitle());

            if (item.getId() > 0) {
                result = db.update("todo", contantValues, "id = ?", new String[]{Integer.toString(item.getId())});
                result = db.delete("todoItem", "todoId = ?", new String[]{Integer.toString(item.getId())});
            } else {
                result = db.insert("todo", null, contantValues);
                item.setId((int) result);
            }

            for (ToDoItems cItem : item.getItems()) {
                contantValues = new ContentValues();
                contantValues.put("tododescription", cItem.getToDo());
                contantValues.put("isChecked", cItem.isChecked());
                contantValues.put("todoId", item.getId());

                result = db.insert("todoItem", null, contantValues);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }

        return result > 0;
    }


    //region Blocked
    /*public boolean addStudentContact(String contactname, String contactphone, String contactstreet, String contactemail, String contactplace){
        *//*,*//*
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("name",contactname);
        contantValues.put("phone", contactphone);
        contantValues.put("street",contactstreet);
        contantValues.put("email",contactemail);
        contantValues.put("place",contactplace);
        db.insert("mycontacts", null, contantValues);
        db.close();
        return true;
    }
    public boolean addEnemyContact(String contactname, String contactphone, String contactstreet, String contactemail, String contactplace){
        *//*,*//*
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("enemyname",contactname);
        contantValues.put("enemyphone", contactphone);
        contantValues.put("enemystreet",contactstreet);
        contantValues.put("enemyemail",contactemail);
        contantValues.put("enemyplace",contactplace);
        db.insert("myenemycontacts", null, contantValues);
        db.close();
        return true;
    }

    public boolean updateStudentContact(Integer contactid, String contactname, String contactphone, String contactstreet, String contactemail, String contactplace)
    {
        *//*,String contactname,*//*
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("name",contactname);
        contantValues.put("phone", contactphone);
        contantValues.put("street",contactstreet);
        contantValues.put("email",contactemail);
        contantValues.put("place",contactplace);
        db.update("mycontacts", contantValues, "id = ?", new String[]{Integer.toString(contactid)});
        db.close();
        return true;
    }

    public boolean updateEnemyContact(Integer contactid, String contactname, String contactphone, String contactstreet, String contactemail, String contactplace)
    {
        *//*,String contactname,*//*
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("enemyname",contactname);
        contantValues.put("enemyphone", contactphone);
        contantValues.put("enemystreet",contactstreet);
        contantValues.put("enemyemail",contactemail);
        contantValues.put("enemyplace",contactplace);
        db.update("myenemycontacts", contantValues, "id = ?", new String[]{Integer.toString(contactid)});
        db.close();
        return true;
    }

    public Integer deleteContact(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("mycontacts","id = ?",new String[]{Integer.toString(id)});
    }

    public Integer deleteEnemyContact(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("myenemycontacts","id = ?",new String[]{Integer.toString(id)});
    }

    public Cursor getData(int contactid){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from mycontacts where id = " + contactid + "", null);
        return res;
    }
    public Cursor getEnemyData(int contactid){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from myenemycontacts where enemyid = " + contactid + "", null);
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db=this.getWritableDatabase();
        int numRows=(int) DatabaseUtils.queryNumEntries(db,CONTACTS_TABLE_NAME);
        return numRows;
    }
    public ArrayList<String> getAllStudentContacts(){
        ArrayList<String> arraylist= new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from mycontacts",null);

        if (cursor.moveToFirst()) {
            do {
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUNAME)));
                *//*arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUPHONE)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUSTREET)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUEMAIL)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUCITY)));*//*
            } while (cursor.moveToNext());
        }
        return arraylist;
    }

    public ArrayList<String> getAllEnemyContacts(){
        ArrayList<String> enemyarraylist= new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from myenemycontacts",null);

        if (cursor.moveToFirst()) {
            do {
                enemyarraylist.add(cursor.getString(cursor.getColumnIndex(ECONTACTS_COLUMN_STUNAME)));
                *//*arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUPHONE)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUSTREET)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUEMAIL)));
                arraylist.add(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STUCITY)));*//*
            } while (cursor.moveToNext());
        }
        return enemyarraylist;
    }*/
    //endregion
}
