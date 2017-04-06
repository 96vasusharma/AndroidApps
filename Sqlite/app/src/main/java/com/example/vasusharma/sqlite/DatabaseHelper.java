package com.example.vasusharma.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vasu Sharma on 30-06-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="MARKS";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null , 1);
        //SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    //inserting in database
    public boolean insertdata(String name,String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_2,name);
        cv.put(COL_3,marks);
        long result=sqLiteDatabase.insert(TABLE_NAME,null,cv);
        if (result==-1)
            return false;
        else
        return true;
    }

    //get data from database
    public Cursor getalldata()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor c= sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return c;
    }
}
