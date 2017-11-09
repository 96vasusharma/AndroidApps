/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.pets.data.PetCursorAdapter;
import com.example.android.pets.data.PetsContract;


/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private static final int PET_LOADER=0;
    PetCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        ListView displayView = (ListView) findViewById(R.id.listView);
        View emptyView = findViewById(R.id.noPet);
        displayView.setEmptyView(emptyView);

        mCursorAdapter = new PetCursorAdapter(this,null);
        displayView.setAdapter(mCursorAdapter);

        displayView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
//                Toast.makeText(CatalogActivity.this, ""+id, Toast.LENGTH_SHORT).show();
                Intent updatePet = new
                        Intent(CatalogActivity.this,EditorActivity.class);
                updatePet.setData(
                        ContentUris.withAppendedId(PetsContract.PetsEntry.CONTENT_URI, id)
                );
                startActivity(updatePet);


            }
        });
        //kick off the loader
        getLoaderManager().initLoader(PET_LOADER,null,this);
//        displayDatabaseInfo();

    }

    private void insertDummyPet(){
//        SQLiteDatabase sqLiteDatabase = petDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PetsContract.PetsEntry.COLUMN_NAME,"Rocky");
        cv.put(PetsContract.PetsEntry.COLUMN_BREED,"German Shepherd");
        cv.put(PetsContract.PetsEntry.COLUMN_GENDER,
                PetsContract.PetsEntry.GENDER_MALE);
        cv.put(PetsContract.PetsEntry.COLUMN_WEIGHT,34);

//        long newRowId = sqLiteDatabase.insert(PetsContract.PetsEntry.TABLE_NAME,null,cv);
//        Log.v("CatalogActivity","Row id = "+newRowId);
        getContentResolver().insert(PetsContract.PetsEntry.CONTENT_URI,
                cv);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
////        displayDatabaseInfo();
//    }

    private void confirmDeleteAllDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_all_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int rowsDeleted =
                        getContentResolver().delete(PetsContract.PetsEntry.CONTENT_URI,
                                null,null);
                Toast.makeText(CatalogActivity.this, (rowsDeleted>0)?
                        getString(R.string.all_pets_delete_success_catalog) :
                        getString(R.string.all_pets_delete_failed_catalog),
                        Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface!=null){
                    dialogInterface.dismiss();
                }
            }
        });
        builder.create().show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:

                insertDummyPet();
//                displayDatabaseInfo();

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:

               confirmDeleteAllDialog();

//                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String [] projection = {PetsContract.PetsEntry._ID ,
                PetsContract.PetsEntry.COLUMN_NAME ,
                PetsContract.PetsEntry.COLUMN_BREED};

        return new CursorLoader(this,
                PetsContract.PetsEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.


        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.

//        String [] projection={  // columns
//                PetsContract.PetsEntry._ID,
//                PetsContract.PetsEntry.COLUMN_NAME,
//                PetsContract.PetsEntry.COLUMN_BREED,
//                PetsContract.PetsEntry.COLUMN_GENDER,
//                PetsContract.PetsEntry.COLUMN_WEIGHT
//        };


//        Cursor cursor = db.rawQuery("SELECT * FROM " +
//                PetsContract.PetsEntry.TABLE_NAME, null);

//        Cursor cursor = db.query(PetsContract.PetsEntry.TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null);
//

//        Cursor cursor = getContentResolver().query(PetsContract.PetsEntry.CONTENT_URI,
//                projection,
//                null,
//                null,
//                null
//        );
//
//        mCursorAdapter = new PetCursorAdapter(this,cursor);
//        displayView.setAdapter(mCursorAdapter);
//
//




//        try {
//            // Display the number of rows in the Cursor (which reflects the number of rows in the
//            // pets table in the database).
//
//            displayView.setText("Number of rows in pets database table: " +
//                    cursor.getCount()+"\n");
//            displayView.append("\n"+PetsContract.PetsEntry._ID + " - " +
//                    PetsContract.PetsEntry.COLUMN_NAME + " - "+
//                    PetsContract.PetsEntry.COLUMN_BREED + " - "+
//                    PetsContract.PetsEntry.COLUMN_GENDER + " - "+
//                    PetsContract.PetsEntry.COLUMN_WEIGHT +"\n");
//
//            int idColumnIndex=cursor.getColumnIndex(PetsContract.PetsEntry._ID);
//            int nameColIndex=cursor.getColumnIndex(PetsContract.PetsEntry.COLUMN_NAME);
//
//            while (cursor.moveToNext()){
//                displayView.append(
//                                "\n" + cursor.getInt(idColumnIndex)
//                                +" - "+cursor.getString(nameColIndex)
//                                +" - "+cursor.getString(2)
//                                +" - "+cursor.getInt(3)
//                                +" - "+cursor.getInt(4));
//            }
//
//        } finally {
//            // Always close the cursor when you're done reading from it. This releases all its
//            // resources and makes it invalid.
//            cursor.close();
//        }
    }
}
