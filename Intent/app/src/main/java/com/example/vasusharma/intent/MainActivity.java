package com.example.vasusharma.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void transfer(View view){
        Intent i = new Intent(this,Main2Activity.class);
        EditText e1=(EditText)findViewById(R.id.editText);
        String s=e1.getText().toString();
        i.putExtra("main_activity_data",s);

        startActivity(i);
    }
}
