/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getSimpleName();
    private static final String TOP_10_EARTHQUAKES = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=4&limit=20";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

//        Log.i(LOG_TAG,LOG_TAG);


//        // Create a fake list of earthquake locations.
//        ArrayList<Earthquake> earthquakes = new ArrayList<>();
//        earthquakes.add(new Earthquake("7.2","San Francisco","Feb 2,2016"));
//        earthquakes.add(new Earthquake("6.1","London","July 20,2015"));
//        earthquakes.add(new Earthquake("3.9","Tokyo","Nov 10,2014 "));
//        earthquakes.add(new Earthquake("5.4","Mexico City","May 3,2014"));
//        earthquakes.add(new Earthquake("2.8","Moscow","Jan 31,2013"));
//        earthquakes.add(new Earthquake("4.9","Rio de Janeiro","Aug 19,2012"));
//        earthquakes.add(new Earthquake("1.6","Paris","Oct 30,2011"));

        new EarthquakeAsyncTask().execute(TOP_10_EARTHQUAKES);



    }

    private class EarthquakeAsyncTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            if (params.length<1 || params[0] == null){
                return null;
            }
            return QueryUtils.extractEarthquakes(params[0]);

        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            if(jsonResponse == null){
                return;
            }
//            super.onPostExecute(earthquakes);
            List<Earthquake> earthquakes= extractFeatureFromJson(jsonResponse);

            updateUi(earthquakes);
        }
    }
    private void updateUi(final List<Earthquake> earthquakes){
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        final EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(
                this, android.R.layout.simple_list_item_1, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        if (earthquakeListView != null) {
            earthquakeListView.setAdapter(earthquakeAdapter);
        }
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake clickedItem = earthquakes.get(position);
//                Earthquake clickedItem2 = (Earthquake)parent.getItemAtPosition(position);
//                Earthquake clickedItem3 = earthquakeAdapter.getItem(position);
                Intent usgsWebsite = new Intent();
                usgsWebsite.setAction(Intent.ACTION_VIEW);
                usgsWebsite.setData(Uri.parse(clickedItem.getmUrl()));
                startActivity(usgsWebsite);
            }
        });
    }
    private static ArrayList<Earthquake> extractFeatureFromJson(String earthquakeJson){

        if (TextUtils.isEmpty(earthquakeJson)){
            return null;
        }
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject root = new JSONObject(earthquakeJson);
            JSONArray features = root.getJSONArray("features");
            for (int i = 0 ; i<features.length();i++){
                JSONObject featuresElement = features.getJSONObject(i);
                JSONObject properties = featuresElement.getJSONObject("properties");

                double magnitude = properties.getDouble("mag");
                String place = properties.getString("place");
                long time = properties.getLong("time");
                String url = properties.getString("url");

                earthquakes.add(new Earthquake(magnitude,place,time,url));
            }
            return earthquakes;
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }
}
