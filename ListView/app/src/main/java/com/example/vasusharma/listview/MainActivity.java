package com.example.vasusharma.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*lv=(ListView)findViewById(R.id.listView);
        ArrayAdapter ad=new ArrayAdapter<String>(this,R.layout.activity_main,s);
        lv.setAdapter(ad);
        */
        String[] s={"HTC","Nokia","Microsoft","Spice","Karbon","Oppo","Lennovo","Sony","Samsung","Le","LG", "Mi", "Motorolla","One Plus"};
        ListAdapter la=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,s);
        ListView lv=(ListView)findViewById(R.id.vasulist);
        lv.setAdapter(la);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String mobilePhones=String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(MainActivity.this,mobilePhones,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
