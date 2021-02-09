package com.shahrasul.quizapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.gson.Gson;
import com.shahrasul.quizapp.data.IQuizApiClient;
import com.shahrasul.quizapp.ui.models.Question;
import com.shahrasul.quizapp.ui.viewpager.MainViewPager;
import com.shahrasul.quizapp.ui.adapter.MainViewPagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView bottomNavigationView;
    private MainViewPager viewPager;
private MainViewPagerAdapter adapterViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager = findViewById(R.id.main_pager);
        viewPager.setOffscreenPageLimit(3);
        adapterViewPager=new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_nav:
                viewPager.setCurrentItem(0);
                break;
            case R.id.history_nav:
                viewPager.setCurrentItem(1);
                break;
            case R.id.settings_nav:
                viewPager.setCurrentItem(2);
                break;

        }
        return true;
    }
}