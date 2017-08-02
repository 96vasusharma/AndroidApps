package com.cris.android.sectionalattendance;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Vasu Sharma on 23-07-2017.
 */

public class EmployeeJson extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Employee>> {
    EmployeeAdapter employeeAdapter;
    ProgressBar progressBar;
    String url="";
    TextView noEmployees;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_json);
        ListView jsonEmployeesListView = (ListView)findViewById(R.id.jsonEmployees);
        noEmployees =(TextView)findViewById(R.id.no_employees);
        jsonEmployeesListView.setEmptyView(noEmployees);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        ArrayList<Employee> jsonEmployees = new ArrayList<>();

        employeeAdapter = new
                EmployeeAdapter(this,jsonEmployees);

        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected){
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getSupportLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(0, null, this);


        }else {

            progressBar.setVisibility(View.GONE);
            noEmployees.setText(getString(R.string.NoInternet));
        }


    }



    @Override
    public Loader<List<Employee>> onCreateLoader(int id, Bundle args) {
        return new EmployeeLoader(this,url);
    }

    @Override
    public void onLoadFinished(Loader<List<Employee>> loader, List<Employee> data) {
        noEmployees.setText("No Employees");
        progressBar.setVisibility(View.GONE);
        employeeAdapter.clear();

        if (data!=null && !data.isEmpty()){
            employeeAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Employee>> loader) {
        employeeAdapter.clear();
    }
}
