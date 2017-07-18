package com.example.android.miwoklanguageconverter2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Vasu Sharma on 03-07-2017.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String titles[] = {"NUMBERS","FAMILY","COLORS","PHRASES"};
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        switch (position){
//            case 0:return "NUMBERS";
//            case 1:return "FAMILY";
//            case 2:return "COLORS";
//            case 3:return "PHRASES";
//
//        }
//        return null;
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new NumbersFragment();
        }
        else if (position == 1){
            return new FamilyFragment();
        }
        else if (position == 2){
            return new ColorsFragment();
        }
        else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
