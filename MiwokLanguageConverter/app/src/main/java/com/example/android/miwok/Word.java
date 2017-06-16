package com.example.android.miwok;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vasu Sharma on 28-08-2016.
 */

public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation ;
//    ArrayList<String> miwokTranslation= new ArrayList<>();
//    ArrayList<String> defaultTranslation= new ArrayList<>();

    public Word(String miwokTranslation,String defaultTranslation){
        mMiwokTranslation=miwokTranslation;
        mDefaultTranslation=defaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;

    }
    public String getDefaultTranslation(){
        return mDefaultTranslation ;
    }


}
