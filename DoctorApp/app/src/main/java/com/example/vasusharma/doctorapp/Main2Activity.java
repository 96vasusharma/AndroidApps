package com.example.vasusharma.doctorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    TextView t1,t2;
    ListView l1;
    int attempts_made=5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //String s[]={"abc","xyz"};
        //l1=(ListView)findViewById(R.id.listView);
        //ArrayAdapter ad= new ArrayAdapter<String>(this,android.R.layout.,s);
        //l1.setAdapter(ad);
        e1=(EditText)findViewById(R.id.editText5);
        e2=(EditText)findViewById(R.id.editText6);
        b1=(Button)findViewById(R.id.button2);
        t1=(TextView)findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView3);
        t2.setText(Integer.toString(attempts_made));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e1.getText().toString().equals("user") && e2.getText().toString().equals("pass"))
                {
                    Toast.makeText(Main2Activity.this,"Login Successful",Toast.LENGTH_LONG).show();
                    Intent go=new Intent(Main2Activity.this,newpage.class);
                    startActivity(go);
                }
                else
                {
                    Toast.makeText(Main2Activity.this,"Login Failed",Toast.LENGTH_LONG).show();
                    attempts_made--;
                    t2.setText(Integer.toString(attempts_made));
                    if (Integer.toString(attempts_made).equals("0"))
                    {

                        b1.setEnabled(false);

                    }

                }


            }
        });


    }
}
