package com.example.tiltracer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Object GameplayActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGameplayOnButtonPress(View view) {
        Intent switchToGamePlay = new Intent(this, GameplayActivity.class);
        startActivity(switchToGamePlay);
    }
}