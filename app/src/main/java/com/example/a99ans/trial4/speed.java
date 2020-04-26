package com.example.a99ans.trial4;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.anastr.speedviewlib.PointerSpeedometer;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class speed extends AppCompatActivity {
    int id;
    float current,avg,max;
    DatabaseReference curr_speed_ref;
    DatabaseReference avg_speed_ref;
    DatabaseReference max_speed_ref;
    DatabaseReference sess_id;
    FirebaseDatabase database;
    PointerSpeedometer pointerSpeedometer;
    TextView avgspeed;
    TextView maxspeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);
        avgspeed = findViewById(R.id.avgspeed);
        maxspeed = findViewById(R.id.maxspeed);
        pointerSpeedometer = (PointerSpeedometer) findViewById(R.id.pointerSpeedometer);
        pointerSpeedometer.speedTo(0,0);
        pointerSpeedometer.setWithTremble(false);
        database = FirebaseDatabase.getInstance();
        sess_id = database.getReference("sess_id");
        sess_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 id = dataSnapshot.getValue(Integer.class);
                 Toast.makeText(speed.this, "Session "+id, Toast.LENGTH_SHORT).show();
                 listenchanges();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    void listenchanges(){
        Toast.makeText(this, "session_"+id, Toast.LENGTH_SHORT).show();
        curr_speed_ref = database.getReference("session_"+id+"/curr_speed");
        curr_speed_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pointerSpeedometer.speedTo(Float.valueOf(dataSnapshot.getValue().toString()),1000);
                pointerSpeedometer.setWithTremble(false);
                Toast.makeText(speed.this, dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        avg_speed_ref = database.getReference("session_"+id+"/avg_speed");
        avg_speed_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                avgspeed.setText("AVG "+dataSnapshot.getValue().toString());
                Toast.makeText(speed.this, dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        max_speed_ref = database.getReference("session_"+id+"/max_speed");
        max_speed_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxspeed.setText("MAX "+dataSnapshot.getValue().toString());
                Toast.makeText(speed.this, dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
