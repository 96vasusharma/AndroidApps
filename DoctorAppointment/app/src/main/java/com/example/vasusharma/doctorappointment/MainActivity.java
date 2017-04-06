package com.example.vasusharma.doctorappointment;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //create layout
        RelativeLayout vasulayout= new RelativeLayout(this);
        vasulayout.setBackgroundColor(Color.CYAN);

        //create     button
        Button bt= new Button(this);
        bt.setBackgroundColor(Color.MAGENTA);
        bt.setText("Sign Up");
        bt.setId(1);


        RelativeLayout.LayoutParams buttondetails=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        buttondetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttondetails.addRule(RelativeLayout.CENTER_VERTICAL);

        //create Edit text(email)
        EditText e= new EditText(this);
        //e.setBackgroundColor(Color.WHITE);
        RelativeLayout.LayoutParams edittxtdetails=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        e.setId(2);
        edittxtdetails.addRule(RelativeLayout.ABOVE,bt.getId());
        edittxtdetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        edittxtdetails.setMargins(0,0,0,100);




        //add widget(button) to layout
        vasulayout.addView(bt,buttondetails);
        vasulayout.addView(e,edittxtdetails);

        //add layout to view(activity's view)
        setContentView(vasulayout);


    }
}
