package com.cris.android.sectionalattendance;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Vasu Sharma on 08-06-2017.
 */

public class EmployeeForm extends Fragment {
    Spinner shift;
    TextView plant;
    Spinner section;
    public static Fragment newInstance(Context context){
        return new EmployeeForm();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View viewGroup = inflater.inflate(R.layout.employee_form,container,false);
        //        EditText plant = (EditText)view.findViewById(R.id.plantName);

//        EditText section = (EditText)viewGroup.findViewById(R.id.section);
//        Button form_sumbit = (Button)viewGroup.findViewById(R.id.form_sumbit);
//        MainActivity.plantName = plant.getText().toString();
//        MainActivity.sectionName = .getText().toString();
        shift = (Spinner)viewGroup.findViewById(R.id.shiftSelector);
        String shifts[] = {"Morning","Evening","Night","General"};
        ArrayAdapter<String> arrayShift = new
                ArrayAdapter<String>(viewGroup.getContext(),
                android.R.layout.simple_list_item_1,shifts);
        shift.setAdapter(arrayShift);
        shift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.shiftName = (String)adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        section = (Spinner)viewGroup.findViewById(R.id.sectionSelector);
        String sections[] = {"Blcroh","Oldroh","Sickline","Yard"};
        ArrayAdapter<String> arraySection = new
                ArrayAdapter<String>(viewGroup.getContext(),
                android.R.layout.simple_list_item_1, sections);
        section.setAdapter(arraySection);
        section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.sectionName = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        plant = (TextView) viewGroup.findViewById(R.id.plantName);
        shift = (Spinner)viewGroup.findViewById(R.id.shiftSelector);
        Button formSubmit = (Button)viewGroup.findViewById(R.id.form_sumbit);
        formSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = null;
                Class fragmentClass = EmployeeList.class;
                try{
                    fragment = (Fragment)fragmentClass.newInstance();

                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentManager fragmentManager =
                        getActivity().getSupportFragmentManager();  //getActivity as we are dealing with frame


                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.frame,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return viewGroup;
    }


}
