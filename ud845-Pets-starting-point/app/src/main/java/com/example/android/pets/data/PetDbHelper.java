package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Vasu Sharma on 11-10-2017.
 */

public class PetDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="shelter.db";
    private static final int DATABASE_VERSION=1;

    public PetDbHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_PETS_TABLE="CREATE TABLE "
                +PetsContract.PetsEntry.TABLE_NAME+"("+ PetsContract.PetsEntry._ID+" INTEGER"
                +" PRIMARY KEY AUTOINCREMENT,"+PetsContract.PetsEntry.COLUMN_NAME+" TEXT"
                +" NOT NULL,"+ PetsContract.PetsEntry.COLUMN_BREED
                +" TEXT,"+ PetsContract.PetsEntry.COLUMN_GENDER+" INTEGER NOT NULL,"
                + PetsContract.PetsEntry.COLUMN_WEIGHT+" INTEGER NOT NULL DEFAULT 0);";

        sqLiteDatabase.execSQL(CREATE_PETS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

//     final String DROP_TABLE="DROP TABLE IF EXISTS "
//            + PetsContract.PetsEntry.TABLE_NAME;
//
//        sqLiteDatabase.execSQL(DROP_TABLE);
//        onCreate(sqLiteDatabase);
    }
}
