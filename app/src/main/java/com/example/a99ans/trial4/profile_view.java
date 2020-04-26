package com.example.a99ans.trial4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class profile_view extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    Button sv_button;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText1 = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText8);
        editText3 = (EditText)findViewById(R.id.editText9);
        editText4 = (EditText)findViewById(R.id.editText10);
        editText5 = (EditText)findViewById(R.id.editText11);
        editText6 = (EditText)findViewById(R.id.editText12);
        sv_button = (Button)findViewById(R.id.save_button);
        pref = getSharedPreferences("MyPref", 0);
        editText1.setText(pref.getString("name",""));
        editText2.setText(pref.getString("age",""));
        editText3.setText(pref.getString("emergency_no",""));
        editText4.setText(pref.getString("address",""));
        editText5.setText(pref.getString("username",""));
        editText6.setText(pref.getString("password",""));
        sv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("username", editText5.getText().toString()); // Storing string
                editor.putString("password", editText6.getText().toString()); // Storing string
                editor.putString("name",editText1.getText().toString());
                editor.putString("age",editText2.getText().toString());
                editor.putString("emergency_no",editText3.getText().toString());
                editor.putString("address",editText4.getText().toString());
                editor.commit();
                Intent intent = new Intent(profile_view.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
