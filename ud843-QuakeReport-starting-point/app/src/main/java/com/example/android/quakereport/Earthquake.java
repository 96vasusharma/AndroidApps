package com.example.android.quakereport;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vasu Sharma on 04-07-2017.
 */

public class Earthquake extends AppCompatActivity {
    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliSeconds;
    private String mUrl;

    Earthquake(double mag, String location, long milliSeconds , String url){
        mMagnitude = mag;
        mLocation = location;
        mTimeInMilliSeconds = milliSeconds;
        mUrl = url;

    }

    public long getmTimeInMilliSeconds() {
        return mTimeInMilliSeconds;
    }

    public String getmLocation() {
        return mLocation;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmUrl() {
        return mUrl;
    }
}
