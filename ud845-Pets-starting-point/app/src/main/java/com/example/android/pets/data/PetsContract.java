package com.example.android.pets.data;

import android.provider.BaseColumns;

/**
 * Created by Vasu Sharma on 10-10-2017.
 */

public final class PetsContract {
    public static final class PetsEntry implements BaseColumns{
        public static final String TABLE_NAME="pets";
        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_BREED="breed";
        public static final String COLUMN_GENDER="gender";
        public static final String COLUMN_WEIGHT="weight";

        public static final int GENDER_UNKNOWN=0;
        public static final int GENDER_MALE=1;
        public static final int GENDER_FEMALE=2;

    }
}
