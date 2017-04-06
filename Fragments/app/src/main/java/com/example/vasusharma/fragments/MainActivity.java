package com.example.vasusharma.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements fragmentone.it_is_an_interface{

    @Override
    public void createMeme(String top, String bottom) {
        fragmenttwo f2=(fragmenttwo)getSupportFragmentManager().findFragmentById(R.id.fragment2);
        f2.setMemeText(top, bottom);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
