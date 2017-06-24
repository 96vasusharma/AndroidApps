package com.cris.android.sectionalattendance;

/**
 * Created by Vasu Sharma on 09-06-2017.
 */

public class Employee {
    private String mEmpId;
    private boolean mIsPresent=false;
    private String mEmpName;
    private String mEmpDesignation;
    public Employee(String empId, String empName, String empDesignation){
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

    public void setPresent(boolean present) {
        mIsPresent = present;
    }

    public boolean isPresent() {
        return mIsPresent;
    }
}
