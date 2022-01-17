package com.example.tiltracer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class GameplayActivity extends AppCompatActivity {

    //Sensor Instances
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;

    //Layout Instance
    ConstraintLayout constraintLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);



//        constraintLayout = new ConstraintLayout(this);
//
//        ImageView playerView = new ImageView(this);
//        playerView.setImageResource(R.drawable.playercar);
//
//        playerView.setAdjustViewBounds(true);
//        playerView.setLayoutParams(new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        ));
//        constraintLayout.addView(playerView);
//        setContentView(constraintLayout);



        //Gyroscope Sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gyroscopeEventListener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                constraintLayout = findViewById(R.id.constraingLayoutGAME); //Set the linear layout for the constrainlayout
                ConstraintSet constraintSet = new ConstraintSet(); //Create new constrain set
                constraintSet.clone(constraintLayout); //Apply dimensions from linear layout to constrain set

                //Tilt to right
                if (sensorEvent.values[1] > 1) {
                    for (int i = 0; i < 1; i++) {
                        constraintSet.connect(R.id.player, ConstraintSet.BOTTOM, R.id.imageView3, ConstraintSet.BOTTOM);
                        constraintSet.applyTo(constraintLayout);
                        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    }
                } else if (sensorEvent.values[1] < -1){
                    for (int i = 0; i < 1; i++) {
                        constraintSet.connect(R.id.player, ConstraintSet.BOTTOM, R.id.imageView4, ConstraintSet.BOTTOM);
                        constraintSet.applyTo(constraintLayout);
                        getWindow().getDecorView().setBackgroundColor(Color.RED);
                    }
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