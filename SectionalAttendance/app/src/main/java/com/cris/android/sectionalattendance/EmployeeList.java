package com.cris.android.sectionalattendance;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.location.Geocoder.isPresent;
import static android.media.CamcorderProfile.get;

/**
 * Created by Vasu Sharma on 08-06-2017.
 */

public class EmployeeList extends Fragment {
    Button button;

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
        button = (Button)viewGroup.findViewById(R.id.submitAttendance);
        TextView section_name =(TextView)viewGroup.findViewById(R.id.section_name);
        TextView shift_name =(TextView)viewGroup.findViewById(R.id.shift_name);
        ListView listView = (ListView)viewGroup.findViewById(R.id.records);
        final ArrayList<Employee> arrayList = new ArrayList<>();
        arrayList.add(new Employee("rid","rahul","desig."));
        arrayList.add(new Employee("sid","sarvesh","desig."));
        arrayList.add(new Employee("tid","tanuj","desig."));
        arrayList.add(new Employee("hid","hemant","desig."));
        arrayList.add(new Employee("cid","cira","desig."));
        arrayList.add(new Employee("vid","vishal","desig."));
        arrayList.add(new Employee("aid","abhishek","desig."));
        arrayList.add(new Employee("bid","bharat","desig."));





        final EmployeeAdapter employee = new
                EmployeeAdapter(viewGroup.getContext(),
                R.layout.list_item,arrayList);
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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Employee> selectedEmployees = new ArrayList<>();
                for (int v=0;v<arrayList.size();v++){
                    Employee e = arrayList.get(v);
                    if (e.isPresent()){
                        selectedEmployees.add(e);
                    }
                }

                for(int x=0;x<selectedEmployees.size();x++){
//                    Log.i("important",selectedEmployees.get(x).getmEmpName());
                    Toast.makeText(view.getContext(),
                            selectedEmployees.get(x).getmEmpName(),
                            Toast.LENGTH_SHORT).show();
                }
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
        return viewGroup;
    }

}
