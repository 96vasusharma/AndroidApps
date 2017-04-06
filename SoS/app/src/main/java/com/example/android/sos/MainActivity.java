package com.example.android.sos;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    SharedPreferences sp;
    double latitude;
    double longitude;
    int shareflag=0;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String num1="First";
    public static final String num2="Second";
    LocationManager locationManager;
    LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double lati = location.getLatitude();
                double longi = location.getLongitude();
                if(shareflag==0) {
                    Toast.makeText(MainActivity.this, "The co-ordinates are\n" +lati +" , "+ longi, Toast.LENGTH_LONG).show();
                    shareflag=1;
                }

                latitude=lati;
                longitude=longi;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                switch(status)
                {
                    case LocationProvider.AVAILABLE:
                        Toast.makeText(getApplicationContext(),"Available",Toast.LENGTH_LONG).show();
                        break;
                    case LocationProvider.OUT_OF_SERVICE:
                        Toast.makeText(getApplicationContext(),"Out of Service",Toast.LENGTH_LONG).show();
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        Toast.makeText(getApplicationContext(),"Unavailable",Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(getApplicationContext(),"Enabled",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(getApplicationContext(),"Disabled",Toast.LENGTH_LONG).show();
            }
        };
    }
    protected void onResume()
    {
        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        super.onResume();
    }
    protected void onPause()
    {
        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        locationManager.removeUpdates(locationListener);
        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.settings,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                Intent intent=new Intent(this,settings.class);
                startActivity(intent);
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void HelpMe(View view)
    {
        String hlp1 = sp.getString(num1, "Not Registered");
        String hlp2 = sp.getString(num2, "Not Registered");
        Toast.makeText(MainActivity.this, hlp1 + " " + hlp2, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + hlp1));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PackageManager.PERMISSION_GRANTED);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }
    public void WatHelp(View view)
    {
        if(shareflag==1)
        {
            PackageManager pm = getPackageManager();
            try {

                Intent waIntent = new Intent(Intent.ACTION_SEND);
                waIntent.setType("text/plain");
                String text = "https://www.google.co.in/maps/dir//" + latitude+ "," +longitude+"\n\nHelp ME ! \nI'm in danger";
                PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                waIntent.setPackage("com.whatsapp");

                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(waIntent, "Share with"));

            }
            catch (PackageManager.NameNotFoundException e)
            {
                Toast.makeText(MainActivity.this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
            }
            shareflag++;
        }
        else
        {
            Toast.makeText(MainActivity.this,"Location Not Fetched",Toast.LENGTH_LONG).show();
        }
    }
    public void MsgHelp(View view)
    {
        if(shareflag==1)
        {
            String text = "https://www.google.co.in/maps/dir//" + latitude+ "," +longitude+"\n\nHelp ME ! \nI'm in danger";
            
            String sms1 = sp.getString(num1, "Not Registered");
            String sms2 = sp.getString(num2, "Not Registered");
            Toast.makeText(MainActivity.this, "Message Sent to\n"+sms1 + " & " + sms2, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + sms1+";"+sms2));
            intent.putExtra("sms_body", text);
            startActivity(intent);

            shareflag++;
        }
        else
        {
            Toast.makeText(MainActivity.this,"Location Not Fetched",Toast.LENGTH_LONG).show();
        }
    }
}
