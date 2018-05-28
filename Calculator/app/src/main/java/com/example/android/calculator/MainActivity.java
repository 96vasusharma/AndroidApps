package com.example.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    void generalButton(View view){
        TextView display = (TextView)findViewById(R.id.display);
        Button button = (Button)view;
        int index = 0;
        int length = text.length();
        if(button.getText().toString().equals("=")){
            for (int i = 0; i < length; i++){
                if (!Character.isDigit(text.charAt(i))){
                    index = i;
                }
            }
            int firstNumber = Integer.parseInt(text.substring(0,index));
            int secondNumber = Integer.parseInt(text.substring(index+1,length));
            char operator = text.charAt(index);
            text = "";
            switch (operator){
                case '+': display.setText(String.valueOf(firstNumber + secondNumber))
                ; break;
                case '-': display.setText(String.valueOf(firstNumber - secondNumber))
                ; break;
                case '*': display.setText(String.valueOf(firstNumber * secondNumber))
                ; break;
                case '/': display.setText(String.valueOf(((float) firstNumber) / secondNumber))
                ; break;
            }

        }
        else if (button.getText().toString().equals("CE")){
            text = "";
            display.setText("0");
        }
        else {
            text += button.getText().toString();
            display.setText(text);
        }
    }
}
