package com.techastrum.myappcreater.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.model.Product;
import com.techastrum.myappcreater.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 101;
    Intent intent;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Context context = ScrollingActivity.this;
        intent = getIntent();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView productImage = findViewById(R.id.product_image);
        TextView amout = findViewById(R.id.main_price);


        String price = intent.getStringExtra("price");
        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        String des = intent.getStringExtra("des");

        if (price != null && title != null && image != null && des != null) {
            toolbar.setTitle(price);
            amout.setText(title);
            Glide.with(context).load(Utils.StringToBitMap(image))
                    .placeholder(R.drawable.android)
                    .into(productImage);

        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_DIAL);
            startActivity(i);
        });
        productList = new ArrayList<>();

        /*RecyclerView recyclerView = findViewById(R.id.rv_service);
        ProductAdapter1 productAdapter = new ProductAdapter1(productList, context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);*/

        product_list();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }


    private void product_list() {
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
    }
}
