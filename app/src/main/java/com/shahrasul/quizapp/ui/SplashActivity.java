package com.shahrasul.quizapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.shahrasul.quizapp.MainActivity;
import com.shahrasul.quizapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         new Handler().postDelayed(() ->{
             Intent i = new Intent(SplashActivity.this, MainActivity.class);
             startActivity(i);
             finish();
         },1000);
    }

}