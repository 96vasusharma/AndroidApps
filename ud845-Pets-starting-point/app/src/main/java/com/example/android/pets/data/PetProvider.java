package com.example.android.pets.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.android.pets.data.PetsContract.PetsEntry;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;


/**
 * Created by Vasu Sharma on 18-10-2017.
 */

public class PetProvider extends ContentProvider {

    private PetDbHelper petDbHelper;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int PETS = 100;
    private static final int PETS_ID = 101;

    // Static initializer. This is run the first time anything is called from this class.
    static{
        sUriMatcher.addURI(PetsContract.CONTENT_AUTHORITY,PetsContract.PATH_PETS,PETS);
        sUriMatcher.addURI(PetsContract.CONTENT_AUTHORITY,
                PetsContract.PATH_PETS+"/#",PETS_ID);
    }

    @Override
    public boolean onCreate() {
        petDbHelper = new PetDbHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase = petDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match){
            case PETS :
                cursor = sqLiteDatabase.query(PetsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                        );
                break;

            case PETS_ID :
                selection = PetsEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri)) };
                cursor=sqLiteDatabase.query(PetsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        //registering for checking of changes in the uri  (data changes)
        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        int match = sUriMatcher.match(uri);
        switch (match){
            case PETS:
                return insertPet(uri,contentValues);

            default:
                throw new IllegalArgumentException("Insertion not possible for " + uri);
        }

    }
    private Uri insertPet(Uri uri,ContentValues contentValues){
        String name = contentValues.getAsString(PetsEntry.COLUMN_NAME);
        if (TextUtils.isEmpty(name)){
            throw new IllegalArgumentException("Need to have name for the pet");
        }

        Integer gender = contentValues.getAsInteger(PetsEntry.COLUMN_GENDER);
        if (gender==null || !PetsEntry.isValidGender(gender)){
            throw new IllegalArgumentException("Not a valid gender");
        }

        Integer weight = contentValues.getAsInteger(PetsEntry.COLUMN_WEIGHT);
        if (weight!=null && weight<0){
            throw new IllegalArgumentException("Weight cannot be zero");
        }

        SQLiteDatabase sqLiteDatabase = petDbHelper.getWritableDatabase();
        long rowNumber = sqLiteDatabase.insert(PetsEntry.TABLE_NAME,null,contentValues);
        if(rowNumber==-1){
            Log.e("PetProvider","Failed to insert row for "+uri);
            return null;
        }

        //notify all the listeners that data has changed for pet content uri
        getContext().getContentResolver().notifyChange(uri,null);

        return ContentUris.withAppendedId(uri,rowNumber);

    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case PETS:
                return updatePet(uri,contentValues,selection,selectionArgs);
            case PETS_ID:
                selection = PetsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePet(uri,contentValues,selection,selectionArgs);

            default:
                throw new IllegalArgumentException("Uri inappropriate ");
        }

    }

    private int updatePet(Uri uri,ContentValues contentValues , String selection ,
                          String[] selectionArgs){

        if (contentValues.size() == 0) {
            return 0;
        }

        if (contentValues.containsKey(PetsEntry.COLUMN_NAME)){
            String name = contentValues.getAsString(PetsEntry.COLUMN_NAME);
            if (TextUtils.isEmpty(name)){
                throw new IllegalArgumentException("Name of updated pet cannot be empty");
            }
        }
        if (contentValues.containsKey(PetsEntry.COLUMN_GENDER)){
            Integer gender = contentValues.getAsInteger(PetsEntry.COLUMN_GENDER);
            if (gender==null || !PetsEntry.isValidGender(gender)){
                throw new IllegalArgumentException("Gender of updated pet inappropriate");
            }
        }
        if (contentValues.containsKey(PetsEntry.COLUMN_WEIGHT)){
            Integer weight = contentValues.getAsInteger(PetsEntry.COLUMN_WEIGHT);
            if (weight!=null && weight < 0){
                throw new IllegalArgumentException("Weight of updated pet cannot be -ve");
            }
        }

        SQLiteDatabase sqLiteDatabase = petDbHelper.getWritableDatabase();
        int rowsUpdated = sqLiteDatabase.update(PetsEntry.TABLE_NAME,contentValues,
                selection,selectionArgs);
        if (rowsUpdated>0){
            //notify all the listeners that data has changed for pet content uri
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsUpdated;
    }



    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = petDbHelper.getWritableDatabase();
        int rowsDeleted;
        switch (match) {
            case PETS:
                rowsDeleted = sqLiteDatabase.delete(PetsEntry.TABLE_NAME, selection,
                        selectionArgs);break;
            case PETS_ID:
                selection = PetsEntry._ID + "=?";
                selectionArgs = new String[]{uri.getLastPathSegment()};

                rowsDeleted = sqLiteDatabase.delete(PetsEntry.TABLE_NAME, selection,
                        selectionArgs);break;
            default:
                throw new IllegalArgumentException("Inappropriate uri - " + uri);
        }
        if (rowsDeleted!=0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsDeleted;
    }



    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case PETS:
                return PetsEntry.CONTENT_LIST_TYPE;
            case PETS_ID:
                return PetsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI "
                        + uri + " with match " + match);

        }
    }
}
