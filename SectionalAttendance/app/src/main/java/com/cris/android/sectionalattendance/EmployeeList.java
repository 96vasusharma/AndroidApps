package com.cris.android.sectionalattendance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;
import static com.cris.android.sectionalattendance.R.id.frame;

/**
 * Created by Vasu Sharma on 08-06-2017.
 */

public class EmployeeList extends Fragment {
    Button attendance,fromInternet;

    public static Fragment newInstance(Context context){
        return new EmployeeList();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View viewGroup = inflater.inflate(R.layout.employees_list,
                container,false);
        attendance = (Button)viewGroup.findViewById(R.id.submitAttendance);
        fromInternet = (Button)viewGroup.findViewById(R.id.from_internet);
        TextView section_name =(TextView)viewGroup.findViewById(R.id.section_name);
        TextView shift_name =(TextView)viewGroup.findViewById(R.id.shift_name);
        ListView listView = (ListView)viewGroup.findViewById(R.id.records);
        final ArrayList<Employee> arrayList = new ArrayList<>();
        arrayList.add(new Employee("101","Kira","SSE"));
        arrayList.add(new Employee("102","Sarvesh","SSE INCH"));
        arrayList.add(new Employee("103","Tanuj","Technician-I"));
        arrayList.add(new Employee("104","Hemant","Technician-II"));
        arrayList.add(new Employee("105","Abhishek","Technician-III"));
        arrayList.add(new Employee("106","Vishal","Welder-I"));
        arrayList.add(new Employee("107","Shaurya","Welder-II"));
        arrayList.add(new Employee("108","Rahul","Painter-I"));
        arrayList.add(new Employee("109","Bharat","Painter-II"));
        arrayList.add(new Employee("110","Tanmay","MCM"));





        final EmployeeAdapter employee = new
                EmployeeAdapter(viewGroup.getContext(),arrayList);
        listView.setAdapter(employee);
        section_name.setText(MainActivity.sectionName);
        shift_name.setText(MainActivity.shiftName);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView,
//                                    View view, int i, long l) {
//                CheckBox check = (CheckBox)view.findViewById(R.id.empId);
//                Employee records =
//                        (Employee)adapterView.getItemAtPosition(i);
//                Log.i("important",records.getmEmpName());
//                if (check.isChecked()){
//                    Log.i("important","clicked checkbox");
//                    Toast.makeText(view.getContext(),
//                            records.getmEmpName(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Employee> selectedEmployees = new ArrayList<>();
                for (int v=0;v<arrayList.size();v++){
                    Employee e = arrayList.get(v);
                    if (e.isPresent()){
                        selectedEmployees.add(e);
                    }
                }
                StringBuilder stringBuilder = new StringBuilder("These guys are present :\n\n");
                if (selectedEmployees.isEmpty()){
                    stringBuilder.append("No One is present:|");
                }
                for(int x=0;x<selectedEmployees.size();x++){
//                    Log.i("important",selectedEmployees.get(x).getmEmpName());

//                              Toast.makeText(view.getContext(),
//                            selectedEmployees.get(x).getmEmpName(),
//                            Toast.LENGTH_SHORT).show();
                    stringBuilder.append(selectedEmployees.get(x).getmEmpName()+"\n");
                }
                Toast.makeText(getActivity(), stringBuilder, Toast.LENGTH_LONG).show();
//                employee.getView()
//                int s = arrayList.size();
//                Log.i("important", "present: "+index);


//                ListView mainList = (ListView)getView().findViewById(R.id.records);
//                for (int x=0;x<mainList.getChildCount();x++){
//                    CheckBox checkBox = (CheckBox)mainList.getChildAt(x).
//                            findViewById(R.id.empId);
//                    if(checkBox.isChecked()){
//                        selectedEmployees.add((Employee)mainList.getItemAtPosition(x));
//                    }
//
//                }

            }
        });
        fromInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EmployeeJson.class);
                startActivity(intent);

            }
        });
        return viewGroup;
    }

}
