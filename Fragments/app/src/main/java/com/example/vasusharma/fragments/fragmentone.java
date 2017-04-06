package com.example.vasusharma.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class fragmentone extends Fragment{
    private static EditText toptextinput,bottomtextinput;

    it_is_an_interface interface_object;

    public interface it_is_an_interface{
         void createMeme(String top,String bottom);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interface_object=(it_is_an_interface)context;
        }
        catch (ClassCastException cce){
            throw new ClassCastException(context.toString());
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragmentone,container,false);
        toptextinput=(EditText)view.findViewById(R.id.toptextinput);
        bottomtextinput=(EditText)view.findViewById(R.id.bottomtextinput);
        final Button button=(Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick(view);
            }
        });
        return view;
    }
    public void buttonClick(View view){
        interface_object.createMeme(toptextinput.getText().toString(),bottomtextinput.getText().toString());

    }
}
