package com.example.android.miwoklanguageconverter2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide shadow under actionbar
        getSupportActionBar().setElevation(0);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //adding viewPager
        ViewPager viewPager =(ViewPager)findViewById(R.id.viewPager);
        MyFragmentPagerAdapter myFragmentPagerAdapter = new
                MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);

        //adding tabs to the viewpager
        TabLayout tabLayout =(TabLayout)findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}




