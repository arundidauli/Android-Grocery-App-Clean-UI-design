package com.techastrum.myappcreater.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

public class ScreenSlidePagerAdapter extends PagerAdapter {

    public ScreenSlidePagerAdapter(FragmentManager supportFragmentManager) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }


}
