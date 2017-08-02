package com.example.android.quakereport;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Vasu Sharma on 20-07-2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private final String mUrl;
    public EarthquakeLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        return QueryUtils.extractEarthquakes(mUrl);

    }


}
