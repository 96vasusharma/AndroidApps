package com.example.android.miwoklanguageconverter2;

/**
 * Created by Vasu Sharma on 28-08-2016.
 */

public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation ;
    private int mImageResourceId=0;
    private int mAudioResourceId;
//    ArrayList<String> miwokTranslation= new ArrayList<>();
//    ArrayList<String> defaultTranslation= new ArrayList<>();
    public Word (String miwokTranslation,String defaultTranslation,int audioId){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mAudioResourceId = audioId;
    }
    public Word(String miwokTranslation,String defaultTranslation, int imageId,int audioId){
        mMiwokTranslation=miwokTranslation;
        mDefaultTranslation=defaultTranslation;
        mImageResourceId = imageId;
        mAudioResourceId = audioId;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;

    }
    public String getDefaultTranslation(){
        return mDefaultTranslation ;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
}
