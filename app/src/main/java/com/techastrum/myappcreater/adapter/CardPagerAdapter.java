package com.techastrum.myappcreater.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.techastrum.myappcreater.R;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter {

    private List<String> mData;
    private float mBaseElevation;

    public CardPagerAdapter() {
        mData = new ArrayList<>();

    }


    public float getBaseElevation() {
        return mBaseElevation;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.pager_layout, container, false);
        container.addView(view);


        return view;
    }


}
