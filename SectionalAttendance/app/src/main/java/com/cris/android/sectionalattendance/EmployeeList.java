package com.cris.android.sectionalattendance;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vasu Sharma on 08-06-2017.
 */

public class EmployeeList extends Fragment {

    public static Fragment newInstance(Context context){
        EmployeeList employeeList = new EmployeeList();
        return employeeList;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewGroup = inflater.inflate(R.layout.employees_list,container,false);
        TextView section_name = (TextView)viewGroup.findViewById(R.id.section_name);
        TextView shift_name = (TextView)viewGroup.findViewById(R.id.shift_name);
        ListView listView = (ListView)viewGroup.findViewById(R.id.records);
        ArrayList<EmployeeRecords> arrayList = new ArrayList<>();
        arrayList.add(new EmployeeRecords("rid","rahul","desig."));
        arrayList.add(new EmployeeRecords("sid","sarvesh","desig."));
        arrayList.add(new EmployeeRecords("tid","tanuj","desig."));
        arrayList.add(new EmployeeRecords("hid","hemant","desig."));
        arrayList.add(new EmployeeRecords("cid","cira","desig."));
        arrayList.add(new EmployeeRecords("vid","vishal","desig."));

        EmployeeAdapter employee = new
                EmployeeAdapter(viewGroup.getContext(),
                R.layout.list_item,arrayList);
        listView.setAdapter(employee);
        section_name.setText(MainActivity.sectionName);
        shift_name.setText(MainActivity.shiftName);

        return viewGroup;
    }

//    public void onBackPressed()
//    {
//        FragmentManager fm = getActivity().getSupportFragmentManager();
//        fm.popBackStack();
//    }
}
