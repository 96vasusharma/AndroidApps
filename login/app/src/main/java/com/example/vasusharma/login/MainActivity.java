package com.example.vasusharma.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    EditText e1,e2;
    Button b1;
    int count=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login();
    }

    public void login()
    {
        t1=(TextView)findViewById(R.id.textView2);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button);
        t1.setText(Integer.toString(count));
        //Toast.makeText(MainActivity.this, "wow", Toast.LENGTH_LONG).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e1.getText().toString().equals("user") && e2.getText().toString().equals("pass"))
                {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    Intent go =new Intent(getApplicationContext(),newpage.class);
                    startActivity(go);
                }
                else
                {

                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                    count--;
                    t1.setText(Integer.toString(count));
                    if (count==0)
                    {
                        b1.setEnabled(false);
                    }
                }
            }
        });

    }
}
