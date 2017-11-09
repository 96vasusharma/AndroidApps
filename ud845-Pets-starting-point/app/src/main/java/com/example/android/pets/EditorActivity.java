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
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.pets.data.PetsContract;
import com.example.android.pets.data.PetsContract.PetsEntry;


/**
 * Allows user to create a new pet or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    /** EditText field to enter the pet's name */
    private EditText mNameEditText;

    /** EditText field to enter the pet's breed */
    private EditText mBreedEditText;

    /** EditText field to enter the pet's weight */
    private EditText mWeightEditText;

    /** EditText field to enter the pet's gender */
    private Spinner mGenderSpinner;

    /**
     * Gender of the pet. The possible values are:
     * 0 for unknown gender, 1 for male, 2 for female.
     */
    private int mGender = 0;
    Uri specificPetUri;
    private boolean mPetChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mPetChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);


        Intent updatePet = getIntent();
        specificPetUri = updatePet.getData();



        if (specificPetUri!=null){
            setTitle("Edit Pet");
            getLoaderManager().initLoader(1,null,this);
//            Toast.makeText(this, specificPetUri.toString(), Toast.LENGTH_SHORT).show();
//            String [] projection={
//                    PetsEntry._ID,
//                    PetsEntry.COLUMN_NAME,
//                    PetsEntry.COLUMN_BREED,
//                    PetsEntry.COLUMN_GENDER,
//                    PetsEntry.COLUMN_WEIGHT
//            };
//            Cursor specificPetCursor = getContentResolver().query(specificPetUri,
//                    projection,
//                    null,
//                    null,
//                    null);
//            specificPetCursor.moveToFirst();
//            int nameInd=specificPetCursor.getColumnIndex(PetsEntry.COLUMN_NAME);
//
//            int breedInd=specificPetCursor.getColumnIndex(PetsEntry.COLUMN_BREED);
//
//            int weightInd=specificPetCursor.getColumnIndex(PetsEntry.COLUMN_WEIGHT);
//            Log.i(getLocalClassName(),nameInd+" "+breedInd+" "+weightInd);
////            Toast.makeText(this, nameInd+" "+breedInd+" "+weightInd, Toast.LENGTH_SHORT).show();
//            mNameEditText.setText(specificPetCursor.getString(nameInd));
//            mBreedEditText.setText(specificPetCursor.getString(breedInd));
//            mWeightEditText.setText(String.valueOf(specificPetCursor.getInt(weightInd)));
//            specificPetCursor.close();
        }
        else {
            setTitle("Add Pet");

            // Invalidate the options menu, so the "Delete" menu option can be hidden.
            // (It doesn't make sense to delete a pet that hasn't been created yet.)
            invalidateOptionsMenu();
        }

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_pet_name);
        mBreedEditText = (EditText) findViewById(R.id.edit_pet_breed);
        mWeightEditText = (EditText) findViewById(R.id.edit_pet_weight);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);

        mNameEditText.setOnTouchListener(mTouchListener);
        mBreedEditText.setOnTouchListener(mTouchListener);
        mWeightEditText.setOnTouchListener(mTouchListener);
        mGenderSpinner.setOnTouchListener(mTouchListener);


        setupSpinner();
    }


    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = PetsEntry.GENDER_MALE; // Male
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = PetsEntry.GENDER_FEMALE; // Female
                    } else {
                        mGender = PetsEntry.GENDER_UNKNOWN; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0; // Unknown
            }
        });
    }
    private void savePet(){
//        PetDbHelper petDbHelper = new PetDbHelper(this);
//        SQLiteDatabase sqLiteDatabase = petDbHelper.getWritableDatabase();
        String name = mNameEditText.getText().toString().trim();
        String breed = mBreedEditText.getText().toString().trim();
        String weight = mWeightEditText.getText().toString().trim();

        if (specificPetUri == null && TextUtils.isEmpty(name) &&
                TextUtils.isEmpty(breed) && TextUtils.isEmpty(weight)
                && mGender == PetsEntry.GENDER_UNKNOWN){
            return;
        }
        if (TextUtils.isEmpty(weight)){
            weight="0";
        }

        ContentValues values = new ContentValues();
        values.put(PetsContract.PetsEntry.COLUMN_NAME , name);
        values.put(PetsContract.PetsEntry.COLUMN_BREED , breed );
        values.put(PetsContract.PetsEntry.COLUMN_GENDER , mGender);
        values.put(PetsContract.PetsEntry.COLUMN_WEIGHT , Integer.parseInt(weight));

//        long newRowId=sqLiteDatabase.insert(PetsContract.PetsEntry.TABLE_NAME,null,values);

        if (specificPetUri==null){
            Uri newUri = getContentResolver().insert(PetsEntry.CONTENT_URI,
                    values);
//        Log.v("EditorActivity","Row id ="+ContentUris.parseId(newUri));
            Toast.makeText(this, (newUri != null) ?
                            getString(R.string.editor_insert_pet_successful) :
                            getString(R.string.editor_insert_pet_failed)
                    , Toast.LENGTH_SHORT).show();
        }
        else {
            int rowsUpdated=getContentResolver().update(specificPetUri,
                    values,null,null);
            if (rowsUpdated==0){
                Toast.makeText(this,"Update Failed",Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Pet updated",Toast.LENGTH_SHORT).show();
        }


    }

    private void showUnsavedChangesDialog(DialogInterface.OnClickListener
                                          discardButtonClickListener){
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing,
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void confirmDeletionDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (specificPetUri!=null){
                    int rowsDeleted =
                            getContentResolver().delete(specificPetUri,
                                    null,
                                    null);
                    Toast.makeText(EditorActivity.this,(rowsDeleted>0)?
                            R.string.pet_delete_success_editor :
                            R.string.pet_delete_failed_editor, Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });

        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface!=null){
                    dialogInterface.dismiss();
                }
            }
        });

        AlertDialog deleteAlertBox = builder.create();
        deleteAlertBox.show();
    }

    @Override
    public void onBackPressed() {
        if(!mPetChanged){
            super.onBackPressed();

            return;  //cause onBackPressed is an async call / in background
            //else we see the alert box for a sec

        }
        DialogInterface.OnClickListener discardButtonClickListener = new
                DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User clicked "Discard" button, close the current activity.
                finish();
            }
        };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        // If this is a new pet, hide the "Delete" menu item.
        if (specificPetUri==null){
            MenuItem deleteMenuItem = menu.findItem(R.id.action_delete);
            deleteMenuItem.setVisible(false);
        }
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Do nothing for now
                savePet();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                confirmDeletionDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                if (!mPetChanged){
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button,
                                // navigate to parent activity.

                                NavUtils.navigateUpFromSameTask(
                                        EditorActivity.this);
                            }
                        };
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                PetsEntry._ID,
                PetsEntry.COLUMN_NAME,
                PetsEntry.COLUMN_BREED,
                PetsEntry.COLUMN_GENDER,
                PetsEntry.COLUMN_WEIGHT };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,
                specificPetUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()){
            int nameInd=cursor.getColumnIndex(PetsEntry.COLUMN_NAME);
            int breedInd=cursor.getColumnIndex(PetsEntry.COLUMN_BREED);
            int genderInd=cursor.getColumnIndex(PetsEntry.COLUMN_GENDER);
            int weightInd=cursor.getColumnIndex(PetsEntry.COLUMN_WEIGHT);

            mNameEditText.setText(cursor.getString(nameInd));
            mBreedEditText.setText(cursor.getString(breedInd));
            mGenderSpinner.setSelection(cursor.getInt(genderInd));
            mWeightEditText.setText(String.valueOf(cursor.getInt(weightInd)));
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mNameEditText.setText("");
        mBreedEditText.setText("");
        mGenderSpinner.setSelection(0);
        mWeightEditText.setText("");
    }
}