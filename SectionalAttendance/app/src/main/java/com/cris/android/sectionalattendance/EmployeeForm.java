package com.cris.android.sectionalattendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.R.id.edit;

/**
 * Created by Vasu Sharma on 08-06-2017.
 */

public class EmployeeForm extends Fragment {
    Spinner shift;
    TextView plant;
    Spinner section;
//    ApplicationClass applicationClass =(ApplicationClass)getContext().getApplicationContext();
//    Spinner shift=applicationClass. getShift();
//    TextView plant=applicationClass.getPlant();
//    Spinner section=applicationClass.getSection();
    public static Fragment newInstance(Context context){
        return new EmployeeForm();

    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        EditText editId;


        public void setEditId(EditText editId){
            this.editId = editId;
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            editId.setText(day+"/"+(month+1)+"/"+year);
        }
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

        final EditText selectDate = (EditText) viewGroup.findViewById(R.id.getDate);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment dpf = new DatePickerFragment();
                dpf.setEditId(selectDate);
                DialogFragment dialogFragment = dpf;
                dialogFragment.show(getFragmentManager(),"datePicker");

            }
        });
        ImageView img =(ImageView)viewGroup.findViewById(R.id.getDate2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment dpf2 = new DatePickerFragment();
                dpf2.setEditId(selectDate);
                DialogFragment dialogFragment = dpf2;
                dialogFragment.show(getFragmentManager(),"datePicker");

            }
        });
        shift = (Spinner)viewGroup.findViewById(R.id.shiftSelector);
        String shifts[] = {"Morning","Evening","Night","General"};
        ArrayAdapter<String> arrayShift = new
                ArrayAdapter<String>(viewGroup.getContext(),
                android.R.layout.simple_list_item_1,shifts);
        shift.setAdapter(arrayShift);
        shift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int i, long l) {
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
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int i, long l) {
                MainActivity.sectionName = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        plant = (TextView) viewGroup.findViewById(R.id.plantName);

        Button formSubmit = (Button)viewGroup.findViewById(R.id.form_sumbit);
        formSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = null;    //fragment should be initialized as null
                Class fragmentClass = EmployeeList.class;
                try{
                    fragment = (Fragment)fragmentClass.newInstance();

                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentManager fragmentManager =
                        getActivity().getFragmentManager();  //getActivity as we
                // are dealing with fragment


                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.frame,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return viewGroup;
    }


}
