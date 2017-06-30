package com.example.vasusharma.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Vasu Sharma on 26-06-2017.
 */

public class FrameBackground extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_background);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        ExampleFragment exampleFragment = new ExampleFragment();
        fragmentTransaction.add(R.id.frame,exampleFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

}
