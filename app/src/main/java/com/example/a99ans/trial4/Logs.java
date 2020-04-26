package com.example.a99ans.trial4;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Logs extends AppCompatActivity {

    ListView listView;
    SharedPreferences log_preferences;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        i=0;
        log_preferences = getSharedPreferences("log_details",0);
        listView = (ListView)findViewById(R.id.logs_list);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.clear();
        while(i<20){
            i+=1;
            Log.i("MSG"+String.valueOf(i),log_preferences.getString(String.valueOf(i),"none"));
            if (log_preferences.getString(String.valueOf(i),"nil").equals("nil")){

            }
            else
                {
                arrayList.add(log_preferences.getString(String.valueOf(i), "nil"));
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(Logs.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        listView.smoothScrollToPosition(0);
    }
}
