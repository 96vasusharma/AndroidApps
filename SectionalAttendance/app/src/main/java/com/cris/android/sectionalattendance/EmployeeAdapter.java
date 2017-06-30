package com.cris.android.sectionalattendance;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vasu Sharma on 09-06-2017.
 */

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    public EmployeeAdapter(@NonNull Context context,
                           @LayoutRes int resource,
                           @NonNull ArrayList<Employee> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {


        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent,false);
        }
        final Employee currentEmployee = getItem(position);
//        CheckBox checkBox =(CheckBox)listItemView.findViewById(R.id.present);
        final CheckBox empId = (CheckBox) listItemView.findViewById(R.id.empId);
        empId.setText(currentEmployee.getmEmpId());
        empId.setChecked(currentEmployee.isPresent());   // necessary to keep the check status while
                                                        // re using views.(in case of scrolling)
        TextView empName = (TextView)listItemView.findViewById(R.id.empName);
        empName.setText(currentEmployee.getmEmpName());
        TextView empDesignation = (TextView)listItemView.findViewById(R.id.empDesignation);
        empDesignation.setText(currentEmployee.getmEmpDesignation());

        empId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (empId.isChecked()){
                    currentEmployee.setPresent(true);
                }
                else {
                    currentEmployee.setPresent(false);
                }
            }
        });
        return listItemView;
    }
}
