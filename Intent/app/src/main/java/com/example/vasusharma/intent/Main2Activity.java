package com.example.vasusharma.intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle mains_data=getIntent().getExtras();
        if (mains_data==null){
            return;
        }
        TextView t1=(TextView)findViewById(R.id.textView);
        String main_activity_data=mains_data.getString("main_activity_data");

        t1.setText(main_activity_data);


    }
}
