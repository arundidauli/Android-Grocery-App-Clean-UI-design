package com.techastrum.myappcreater.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.adapter.BottomProductAdapter;
import com.techastrum.myappcreater.adapter.CategoryAdapter;
import com.techastrum.myappcreater.adapter.TrendingProductAdapter;
import com.techastrum.myappcreater.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private List<Product> productList;
    private Context context;
    private Intent intent;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;
                case R.id.cart:
                    intent = new Intent(getApplicationContext(), CartActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;
                case R.id.profile:
                    intent = new Intent(getApplicationContext(), HomeProfile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setRecyclerView_Category();
        setRecyclerView_trending();
        setRecyclerView_bottom();

    }

    private void setRecyclerView_trending() {

        productList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.rv_trending_product);
        SnapHelper snapHelper = new PagerSnapHelper();

        snapHelper.attachToRecyclerView(recyclerView);
        TrendingProductAdapter trendingProductAdapter = new TrendingProductAdapter(productList, context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(trendingProductAdapter);

        product_list();
    }

    private void setRecyclerView_Category() {

        productList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.rv_category);
        SnapHelper snapHelper = new PagerSnapHelper();

        snapHelper.attachToRecyclerView(recyclerView);
        CategoryAdapter categoryAdapter = new CategoryAdapter(productList, context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoryAdapter);

        product_list();
    }

    private void setRecyclerView_bottom() {
        productList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.rv_bottom_product);

        BottomProductAdapter bottom_productAdapter = new BottomProductAdapter(productList, context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bottom_productAdapter);


    }

    private void product_list() {
        productList.add(new Product("Samsung Galexy S7", "", "", "\u20B9 0-/", "\"Your app will be send to you by mail and \nYour app will be publish within working hours\"", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "\u20B9 500-/", "\"With more advance feature publish for 12 months your app will be send to you by mail and your app will be publish within working hours\"", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "\u20B9 500-/", "\"With more advance feature publish for 12 months your app will be send to you by mail and your app will be publish within working hours\"", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "\u20B9 500-/", "\"With more advance feature publish for 12 months your app will be send to you by mail and your app will be publish within working hours\"", "https://i.ibb.co/fqkKRWS/download.jpg"));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}