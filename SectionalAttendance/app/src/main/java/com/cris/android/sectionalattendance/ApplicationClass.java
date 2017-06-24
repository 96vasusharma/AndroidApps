//package com.cris.android.sectionalattendance;
//
//import android.app.Application;
//import android.content.res.Configuration;
//import android.widget.Spinner;
//import android.widget.TextView;
//
///**
// * Created by Vasu Sharma on 19-06-2017.
// */
//
//public class ApplicationClass extends Application {
//    private static ApplicationClass singleton;
//    private Spinner mshift;
//    private TextView mplant;
//    private Spinner msection;
//
//    public static ApplicationClass getInstance(){
//        return singleton;
//    }
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        singleton = this;
//    }
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//    }
//
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//    }
//
//    @Override
//    public void onTerminate() {
//        super.onTerminate();
//    }
//
//    public Spinner getShift() {
//        return mshift;
//    }
//
//    public Spinner getSection() {
//
//        return msection;
//    }
//
//    public TextView getPlant() {
//
//        return mplant;
//    }
//}
