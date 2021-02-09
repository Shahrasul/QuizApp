package com.shahrasul.quizapp.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.shahrasul.quizapp.ui.history.HistoryFragment;
import com.shahrasul.quizapp.ui.main.MainFragment;
import com.shahrasul.quizapp.ui.settings.SettingsFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public MainViewPagerAdapter(@NonNull FragmentManager fm){
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position){
            case 0 :
                fragment = new MainFragment().newInstance();
                break;
            case 1 :
                fragment= new HistoryFragment().newInstance();
                break;
            case 2:
                fragment= new SettingsFragment().newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
