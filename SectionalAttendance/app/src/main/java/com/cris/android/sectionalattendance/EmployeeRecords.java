package com.cris.android.sectionalattendance;

/**
 * Created by Vasu Sharma on 09-06-2017.
 */

public class EmployeeRecords {
    String mEmpId;
    String mEmpName;
    String mEmpDesignation;
    public EmployeeRecords(String empId,String empName, String empDesignation){
        mEmpId=empId;
        mEmpName=empName;
        mEmpDesignation=empDesignation;
    }

    public String getmEmpDesignation() {

        return mEmpDesignation;
    }

    public String getmEmpId() {
        return mEmpId;
    }

    public String getmEmpName() {
        return mEmpName;
    }

}
