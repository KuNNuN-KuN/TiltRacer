package com.example.tiltracer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
    }

    public void switchToGameOverScreen(View view) {
        Intent switchToGameOverScreen = new Intent(this, GameOverActivity.class);
        startActivity(switchToGameOverScreen);
    }


}