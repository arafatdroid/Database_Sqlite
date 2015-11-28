package com.example.mazharulislam.database_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mazhaul Islam on 27/11/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "Student.db";
    public static final String TABLE_NAME= "Student_table";
    public static final String COL_1= "ID";
    public static final String COL_2= "NAME";
    public static final String COL_3= "SURNAME";
    public static final String COL_4= "MARKS";





    public DatabaseHelper(Context context) {
        //database will be created after calling this constructor.

        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase database=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_2 + " TEXT," +
                COL_3 + " TEXT," +
                COL_4 + " INTEGER" +")";
        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }
    // This part is only for inserting data into database

    public boolean insertData(String name,String surname, String marks)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result= db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;



    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " +TABLE_NAME,null);
        return res;

    }

    public boolean updateData(String id, String name, String surname, String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{ id });
        return true;
    }

    public int deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?", new String[] {id});
    }
}
