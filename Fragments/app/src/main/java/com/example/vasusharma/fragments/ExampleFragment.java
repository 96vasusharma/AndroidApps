package com.example.vasusharma.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Vasu Sharma on 28-06-2017.
 */

public class ExampleFragment extends Fragment{
//    public static ExampleFragment getInstance(){
//        return new ExampleFragment();
//    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.example_of_frame_layout,container,false);



        //extra part (checking)
        Button b =(Button)v.findViewById(R.id.randAct);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),ExampleActivity.class);
                startActivity(i);
            }
        });


        return v;

    }

}
