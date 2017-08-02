package com.cris.android.sectionalattendance;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Vasu Sharma on 23-07-2017.
 */

public class EmployeeLoader extends AsyncTaskLoader<List<Employee>> {
    private final String url;
    public EmployeeLoader(Context context,String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Employee> loadInBackground() {
        if (url == null){
            return null;
        }
        return QueryUtils.extractEmployee(url);

    }
}
