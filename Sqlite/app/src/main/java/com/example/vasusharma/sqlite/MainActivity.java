package com.example.vasusharma.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText e1,e2;
    TextView t1,t2;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        t1=(TextView) findViewById(R.id.textView);
        t2=(TextView) findViewById(R.id.textView2);
        b1=(Button) findViewById(R.id.button);
        b2=(Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b=myDb.insertdata(e1.getText().toString(),e2.getText().toString());
                if(b==true)
                {
                    Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "not inserted", Toast.LENGTH_LONG).show();
                }
            }

        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c= myDb.getalldata();
                if (c.getCount()==0)
                {
                    Toast.makeText(MainActivity.this, "no data available", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    StringBuffer sb= new StringBuffer();
                    while (c.moveToNext())
                    {
                        sb.append("Id: "+ c.getString(0)+"\n");
                        sb.append("Name: "+ c.getString(1)+"\n");
                        sb.append("Marks: "+ c.getString(2)+"\n\n");
                    }
                }
            }
        });
    }
}
