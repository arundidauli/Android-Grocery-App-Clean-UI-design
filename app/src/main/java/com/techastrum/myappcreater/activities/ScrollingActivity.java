package com.techastrum.myappcreater.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {
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
        System.out.println(price + "\n" + title + "\n" + image + "\n" + des);

        if (price != null && title != null && image != null && des != null) {
            toolbar.setTitle("Rs. " + price);
            Glide.with(context).load(image)
                    .placeholder(R.drawable.android)
                    .into(productImage);
            amout.setText(title);


        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        productList = new ArrayList<>();
        /*RecyclerView recyclerView = findViewById(R.id.rv_service);

        ProductAdapter1 productAdapter = new ProductAdapter1(productList, context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);*/
        product_list();
    }

    private void product_list() {
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
    }
}
