package com.example.a99ans.trial4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class start extends AppCompatActivity {

    private Button register;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        register = (Button)findViewById(R.id.email_sign_in_button2);
        login =(Button)findViewById(R.id.email_sign_in_button3);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(start.this,LoginActivity.class);
                        startActivity(intent);
                    }
                },1000);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(start.this,LoginActivity2.class);
                        startActivity(intent);
                    }
                },500);
            }
        });
    }


}
