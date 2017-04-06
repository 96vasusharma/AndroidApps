package com.example.android.sos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vasu Sharma on 02-08-2016.
 */
public class settings extends AppCompatActivity {
    public static final String num1="First";
    public static final String num2="Second";
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Button button = (Button)findViewById(R.id.button);
        final EditText editText = (EditText)findViewById(R.id.editText);
        final EditText editText1 = (EditText)findViewById(R.id.editText2);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        TextView textView=(TextView)findViewById(R.id.first_number);
        TextView textView1=(TextView)findViewById(R.id.second_number);
        String s1=sharedPreferences.getString(num1,"Not Registered");
        String s2=sharedPreferences.getString(num2,"Not Registered");
        textView.setText(s1);
        textView1.setText(s2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ph1=editText.getText().toString();
                String ph2 = editText1.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(num1,ph1);
                editor.putString(num2,ph2);
                editor.commit();
                Toast.makeText(settings.this, "Data Updated", Toast.LENGTH_LONG).show();
                String s1=sharedPreferences.getString(num1,"Not Registered");
                String s2=sharedPreferences.getString(num2,"Not Registered");
                TextView textView=(TextView)findViewById(R.id.first_number);
                TextView textView1=(TextView)findViewById(R.id.second_number);
                textView.setText(s1);
                textView1.setText(s2);

            }
        });
    }
}
