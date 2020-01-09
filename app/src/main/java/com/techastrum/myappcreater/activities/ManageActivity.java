package com.techastrum.myappcreater.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.adapter.ProductAdapter;
import com.techastrum.myappcreater.adapter.ProductAdapter1;
import com.techastrum.myappcreater.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ManageActivity extends AppCompatActivity {
    private ProductAdapter1 productAdapter;
    private List<Product> productList;
    private RecyclerView recyclerView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        context=ManageActivity.this;


        productList =new ArrayList<>();
        recyclerView = findViewById(R.id.rv_service);

        productAdapter = new ProductAdapter1(productList,context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);

        MyproductList();
    }

    private void MyproductList() {
        productList.add(new Product("Samsung Galexy S7","","","9999/-","Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera","https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7","","","9999/-","Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera","https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7","","","9999/-","Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera","https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7","","","9999/-","Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera","https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7","","","9999/-","Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera","https://i.ibb.co/fqkKRWS/download.jpg"));
    }
}
