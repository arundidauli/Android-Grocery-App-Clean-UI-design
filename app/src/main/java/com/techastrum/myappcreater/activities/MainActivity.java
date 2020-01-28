package com.techastrum.myappcreater.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techastrum.myappcreater.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static String TAG=MainActivity.class.getSimpleName();
    private List<String> SubCategory_list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner category =findViewById(R.id.spinner_category);
        Spinner subcategory = findViewById(R.id.spinner_subcategory);
        List<String> category_list = new ArrayList<>();
        SubCategory_list=new ArrayList<>();
        category_list.add("All");
        category_list.add("IT Service");
        category_list.add("Retail");
        category_list.add("Individual");
        category_list.add("Product/Service");
        SubCategory_list.add("All");


        SpinnerMethod(category_list, category,"category");
        SpinnerMethod(SubCategory_list, subcategory,"subcategory");
        findViewById(R.id.btn_create_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateNew.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_my_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeProfile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        findViewById(R.id.rl_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }

    private void SpinnerMethod(List<String> arrayList, Spinner spinner,String flag) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getApplicationContext()),
                android.R.layout.simple_list_item_single_choice, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                if (position==0){
                    SubCategory_list.clear();
                    SubCategory_list.add("Auto Mobile");
                    SubCategory_list.add("Electronic Gadget");
                    SubCategory_list.add("Clothes");
                    SubCategory_list.add("Grocery");
                }else if (position==1){
                    SubCategory_list.clear();

                    SubCategory_list.add("Electronic Gadget");

                }else if (position==2){
                    SubCategory_list.clear();

                    SubCategory_list.add("Clothes");
                    SubCategory_list.add("Grocery");
                }else if(position==3){
                    SubCategory_list.clear();
                    SubCategory_list.add("Auto Mobile");
                    SubCategory_list.add("Electronic Gadget");
                    SubCategory_list.add("Clothes");
                    SubCategory_list.add("Grocery");
                }else if (position==4){
                    SubCategory_list.clear();
                    SubCategory_list.add("Electronic Gadget");
                    SubCategory_list.add("Clothes");
                    SubCategory_list.add("Grocery");
                }
               // prefs.SetValue(flag, (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}