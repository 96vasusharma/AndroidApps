package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.R.attr.start;
import static android.provider.LiveFolders.INTENT;
import static android.view.View.inflate;
import static com.example.android.quakereport.R.id.magnitude;

/**
 * Created by Vasu Sharma on 04-07-2017.
 */

class EarthquakeAdapter extends ArrayAdapter<Earthquake>{
    private Context context;
    private static final String LOCATION_SEPARATOR = " of ";
     EarthquakeAdapter(@NonNull  Context context,@NonNull List<Earthquake> objects) {
        super(context, 0 , objects);
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(context)
                    .inflate(R.layout.earthquake_list_item,
                    parent,false);
        }
        final Earthquake currentEarthquake = getItem(position);

        //magnitude
        TextView magnitudeOfEarthquake = (TextView)listItemView.findViewById(R.id.magnitude);
        magnitudeOfEarthquake.setText(formatMagnitude(currentEarthquake.getmMagnitude()));

        //getting background of magnitude text view , which we have defined a circle
        GradientDrawable magnitudeCircle = (GradientDrawable)magnitudeOfEarthquake.
                getBackground();
        //getting apt color code for a specific magnitude
        int desiredBackground = getMagnitudeColor(currentEarthquake.getmMagnitude());
        //setting that color to the background
        magnitudeCircle.setColor(desiredBackground);

        //location
        String location = currentEarthquake.getmLocation();
        String primaryLocation,locationOffset;

        //My try
//        int index = 0;
//        if (location.contains(" of ")){
//            index = location.indexOf(" of ") + 4;
//            locationOffset = location.substring(0,index);
//        }else
//        {
//            locationOffset ="Near the";
//        }
//        primaryLocation = location.substring(index);
        if (location.contains(LOCATION_SEPARATOR)) {


            String combination[] = location.split(LOCATION_SEPARATOR);
            locationOffset = combination[0] + LOCATION_SEPARATOR;
            primaryLocation = combination[1];
        }else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = location;
        }
        TextView locationOffsetOfEarthquake =(TextView)listItemView.findViewById(
                R.id.location_offset);
        locationOffsetOfEarthquake.setText(locationOffset);

        TextView primaryLocationOfEarthquake = (TextView)listItemView.findViewById(
                R.id.primary_location);
        primaryLocationOfEarthquake.setText(primaryLocation);

        //creating date object from time in milliseconds(long datatype)
        Date date = new Date(currentEarthquake.getmTimeInMilliSeconds());

        //date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy");

        TextView dateOfEarthquake = (TextView)listItemView.findViewById(R.id.date);
        dateOfEarthquake.setText(dateFormat.format(date));

        //time format
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String timeToDisplay = timeFormat.format(date);

        TextView timeOfEarthquake = (TextView)listItemView.findViewById(R.id.time);
        timeOfEarthquake.setText(timeToDisplay);

        return listItemView;
    }

    // Helper method
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double mag){
        int color=0;
        switch ((int)mag){
            case 0:
            case 1:return ContextCompat.getColor(getContext(),R.color.magnitude1);
            case 2:return ContextCompat.getColor(getContext(),R.color.magnitude2);
            case 3:return ContextCompat.getColor(getContext(),R.color.magnitude3);
            case 4:return ContextCompat.getColor(getContext(),R.color.magnitude4);
            case 5:return ContextCompat.getColor(getContext(),R.color.magnitude5);
            case 6:return ContextCompat.getColor(getContext(),R.color.magnitude6);
            case 7:return ContextCompat.getColor(getContext(),R.color.magnitude7);
            case 8:return ContextCompat.getColor(getContext(),R.color.magnitude8);
            case 9:return ContextCompat.getColor(getContext(),R.color.magnitude9);
            default:return ContextCompat.getColor(getContext(),R.color.magnitude10plus);
        }

    }
}
