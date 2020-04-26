package com.example.a99ans.trial4;

import android.app.ActionBar;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class accelerometer extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    private TextView x;
    private TextView y;
    private TextView z;
    private TextView i;
    boolean mustReadSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        x =  findViewById(R.id.x);
        y =  findViewById(R.id.y);
        z =  findViewById(R.id.z);
        i =  findViewById(R.id.info);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mustReadSensor = true;
            }
        }, 0, 100);  // 1000 ms delay

    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this,sensor,100000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this,sensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!mustReadSensor) {
            return;
        }
        mustReadSensor = false;
        Log.e("Acceleration X",String.valueOf(event.values[0]));
        Log.e("Acceleration Y",String.valueOf(event.values[1]));
        Log.e("Acceleration Z",String.valueOf(event.values[2]));

        float x_acc = event.values[0];
        float y_acc = event.values[1];
        float z_acc = event.values[2];


        ViewGroup.LayoutParams params1 = x.getLayoutParams();
        params1.width= (int) (((x_acc/78)*355) * ((float) this.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT))+2;
        x.setLayoutParams(params1);

        ViewGroup.LayoutParams params2 = y.getLayoutParams();
        params2.width= (int) (((y_acc/78)*355) * ((float) this.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT))+2;
        y.setLayoutParams(params2);


        ViewGroup.LayoutParams params3 = z.getLayoutParams();
        params3.width= (int) (((z_acc/78)*355) * ((float) this.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT))+2;
        z.setLayoutParams(params3);

        double acc = Math.sqrt((x_acc*x_acc)+(y_acc*y_acc)+(z_acc*z_acc));

        ViewGroup.LayoutParams params4 = i.getLayoutParams();
        params4.width= (int) (((acc/78)*355) * ((float) this.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT))+2;
        i.setLayoutParams(params4);

        if((int)(acc)>40){
            Toast.makeText(this, "Accident", Toast.LENGTH_SHORT).show();
            i.setBackgroundColor(getColor(R.color.myred));
        }
        else{
            i.setBackgroundColor(getColor(R.color.colorAccent));
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
