package com.example.tiltracer;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GameplayActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        //Gyroscope Sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        gyroscopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                //Tilt to right
                if (sensorEvent.values[1] > 1) {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    //Move image to right
                    //Tilt to left
                } else if (sensorEvent.values[1] < -1){
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }


    //Best Practice, to avoid battery draining
    @Override
    public void onResume(){
        super.onResume();
        sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor, sensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroscopeEventListener);
    }

    public void switchToGameOverScreen(View view) {
        Intent switchToGameOverScreen = new Intent(this, GameOverActivity.class);
        startActivity(switchToGameOverScreen);
    }
}