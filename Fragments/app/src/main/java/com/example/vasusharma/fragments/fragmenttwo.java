package com.example.vasusharma.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class fragmenttwo extends Fragment{
    private static TextView t1,t2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmenttwo,container,false);
        t1=(TextView)view.findViewById(R.id.t1);
        t2=(TextView)view.findViewById(R.id.t2);

        return view;
    }
    public void setMemeText(String s1,String s2){
        t1.setText(s1);
        t2.setText(s2);
    }
}
