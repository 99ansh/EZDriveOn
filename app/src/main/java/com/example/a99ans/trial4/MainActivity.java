package com.example.a99ans.trial4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView t1;
    private TextView t2;
    SharedPreferences pref;
    Button mic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pref = getSharedPreferences("MyPref", 0);
        t1=findViewById(R.id.belowIconTextView1);
        t2=findViewById(R.id.belowIconTextView2);

        mic = (Button)findViewById(R.id.mic);
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 10);
                } else {
                    Toast.makeText(MainActivity.this, "Feature not supported!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileFragment()).commit();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_message:
                Intent intent = new Intent(this,cam2.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_profile:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //new ProfileFragment()).commit();
                Intent intent2 = new Intent(this,profile_view.class);
                startActivity(intent2);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_logs:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //      new ProfileFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent3 = new Intent(this,Logs.class);
                startActivity(intent3);
                break;
            case R.id.accelerometer:
                drawer.closeDrawer(GravityCompat.START);
                Intent intent5 = new Intent(this,accelerometer.class);
                startActivity(intent5);
                break;

            case R.id.nav_phone:
                drawer.closeDrawer(GravityCompat.START);
                Intent intent4 = new Intent(Intent.ACTION_DIAL);
                intent4.setData(Uri.parse("tel:"+pref.getString("emergency_no","108")));
                startActivity(intent4);
                break;

            case R.id.speed:
                drawer.closeDrawer(GravityCompat.START);
                Intent intent6 = new Intent(this,speed.class);
                startActivity(intent6);
                break;

        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10 && data!=null) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (!results.isEmpty()) {
                Toast.makeText(this, results.toString(), Toast.LENGTH_LONG).show();
                for(String result:results) {

                    if (result.contains("profile") || result.contains("myprofile")) {
                        Intent intent2 = new Intent(this, profile_view.class);
                        startActivity(intent2);
                    }
                    if (result.contains("begin") || result.contains("start") || result.contains("track") || result.contains("tracking") || result.contains("monitor")|| result.contains("monitoring")) {
                        Intent intent = new Intent(this, cam2.class);
                        startActivity(intent);
                    }
                    if (result.contains("log") || result.contains("logs") || result.contains("history")) {
                        Intent intent3 = new Intent(this, Logs.class);
                        startActivity(intent3);
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

}

