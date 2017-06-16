package com.cris.android.sectionalattendance;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Vasu Sharma on 09-06-2017.
 */

public class EmployeeAdapter extends ArrayAdapter<EmployeeRecords> {

    public EmployeeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<EmployeeRecords> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                    parent,false);
        }
        EmployeeRecords currentEmployee = getItem(position);
        TextView empId = (TextView)listItemView.findViewById(R.id.empId);
        empId.setText(currentEmployee.getmEmpId());
        TextView empName = (TextView)listItemView.findViewById(R.id.empName);
        empName.setText(currentEmployee.getmEmpName());
        TextView empDesignation = (TextView)listItemView.findViewById(R.id.empDesignation);
        empDesignation.setText(currentEmployee.getmEmpDesignation());

        return listItemView;
    }
}
