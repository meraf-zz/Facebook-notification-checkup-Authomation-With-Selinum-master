package com.example.pro.lif.adapter;

/**
 * Created by pro on 7/23/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pro.lif.ApplyFragment;
import com.example.pro.lif.AboutFragment;
import com.example.pro.lif.HomeFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {

        super(fm);
    }


    public Fragment getItem(int index) {
        switch(index) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ApplyFragment();
            case 2:
                return new AboutFragment();
            default:
                return null;
        }
    }

    public int getCount() {
        return 3;
    }
}