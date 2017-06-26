package com.example.vasusharma.fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        Button goTo =(Button)findViewById(R.id.goToFramePage);
        goTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Frame.class);
                startActivity(i);
            }
        });
    }
}
