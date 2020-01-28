package com.techastrum.myappcreater.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techastrum.myappcreater.Interface.Onholderitemclick;
import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.adapter.ProductAdapter1;
import com.techastrum.myappcreater.fragment.AddFragment;
import com.techastrum.myappcreater.fragmentdialog.MerchantInfo;
import com.techastrum.myappcreater.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeProfile extends AppCompatActivity implements Onholderitemclick {
    private static String TAG=HomeProfile.class.getSimpleName();
    @BindView(R.id.rv_service)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton mfab;
    private List<Product> productList;
    private ProductAdapter1 productAdapter;
    private Context context;
    private Intent intent;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profile);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = HomeProfile.this;
        ButterKnife.bind(this);
        intent = getIntent();
        setRecyclerView();
        findViewById(R.id.fab).setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),ManageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }





    private void setRecyclerView() {
        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_service);
        productAdapter = new ProductAdapter1(productList, context, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);
        if (intent.getStringExtra("title") != null && intent.getStringExtra("image") != null && intent.getStringExtra("price") != null && intent.getStringExtra("detail") != null) {
            productList.add(new Product(intent.getStringExtra("title"), intent.getStringExtra("category"), intent.getStringExtra("subcategory"), "Rs. " + intent.getStringExtra("price"), intent.getStringExtra("detail"), intent.getStringExtra("image")));
            productList.add(new Product(intent.getStringExtra("title"), intent.getStringExtra("category"), intent.getStringExtra("subcategory"), "Rs. " + intent.getStringExtra("price"), intent.getStringExtra("detail"), intent.getStringExtra("image")));

        }

        product_list();
    }

    private void product_list() {
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
        productList.add(new Product("Samsung Galexy S7", "", "", "9999/-", "Samsung galexy s7 new launch with 6GB Ram 128 GB ROM and dual Camera", "https://i.ibb.co/fqkKRWS/download.jpg"));
    }

    private void fragment_call(Fragment fragment){
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void replace_Fragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container, fragment, backStateName);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

   /* public void onPopupButtonClick(View button) {
        PopupMenu popup = new PopupMenu(this, button);
        popup.getMenuInflater().inflate(R.menu.menu_home_profile, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.enquiry:
                    Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.download_app:
                    Toast.makeText(getApplicationContext(), "Item 2 Selected", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.publish_app:
                    Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
                    return true;
            }
            return true;
        });
        popup.show();
    }*/


    @Override
    public void onholderitemclick_listner(int position) {
        show_dialog(position);
    }

  /*  public void replace_Fragment(Fragment fragment) {

        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container, fragment, backStateName);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }*/


    private void show_dialog(int position) {
        final Dialog dialog = new Dialog(context, R.style.WideDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setCancelable(true);
        Rect displayRectangle = new Rect();
        Window window = dialog.getWindow();
        assert window != null;
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        // View layout = getLayoutInflater().inflate(R.layout.fragment_dialog, null);
        // View.inflate(context, R.layout.fragment_dialog, null);
        dialog.setContentView(View.inflate(context, R.layout.fragment_dialog, null));
        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.PopupAnimation;
        EditText et_name = dialog.findViewById(R.id.et_enter_name);
        EditText et_price = dialog.findViewById(R.id.et_price);
        EditText et_details = dialog.findViewById(R.id.et_detail);
        Button btn_save = dialog.findViewById(R.id.btn_save);

        et_name.setText(productList.get(position).getName());
        et_price.setText(productList.get(position).getPrice());
        et_details.setText(productList.get(position).getDetail());


        btn_save.setOnClickListener(v -> {
            if (et_name.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter something", Toast.LENGTH_LONG).show();
            } else {
                productList.get(position).setName(et_name.getText().toString());
                productList.get(position).setPrice(et_price.getText().toString());
                productList.get(position).setDetail(et_details.getText().toString());
                productAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }


        });

        dialog.show();
    }
    private void show_dialog_download_app(int position) {
        final Dialog dialog = new Dialog(context, R.style.WideDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setCancelable(true);
        Rect displayRectangle = new Rect();
        Window window = dialog.getWindow();
        assert window != null;
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        // View layout = getLayoutInflater().inflate(R.layout.fragment_dialog, null);
        // View.inflate(context, R.layout.fragment_dialog, null);
        dialog.setContentView(View.inflate(context, R.layout.fragment_dialog, null));
        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.PopupAnimation;
        EditText et_name = dialog.findViewById(R.id.et_enter_name);
        EditText et_price = dialog.findViewById(R.id.et_price);
        EditText et_details = dialog.findViewById(R.id.et_detail);
        Button btn_save = dialog.findViewById(R.id.btn_save);

        et_name.setText(productList.get(position).getName());
        et_price.setText(productList.get(position).getPrice());
        et_details.setText(productList.get(position).getDetail());


        btn_save.setOnClickListener(v -> {
            if (et_name.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter something", Toast.LENGTH_LONG).show();
            } else {
                productList.get(position).setName(et_name.getText().toString());
                productList.get(position).setPrice(et_price.getText().toString());
                productList.get(position).setDetail(et_details.getText().toString());
                productAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }


        });

        dialog.show();
    }

    private void show_dialog_publish_app(int position) {
        final Dialog dialog = new Dialog(context, R.style.WideDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setCancelable(true);
        Rect displayRectangle = new Rect();
        Window window = dialog.getWindow();
        assert window != null;
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        // View layout = getLayoutInflater().inflate(R.layout.fragment_dialog, null);
        // View.inflate(context, R.layout.fragment_dialog, null);
        dialog.setContentView(View.inflate(context, R.layout.fragment_dialog, null));
        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.PopupAnimation;
        EditText et_name = dialog.findViewById(R.id.et_enter_name);
        EditText et_price = dialog.findViewById(R.id.et_price);
        EditText et_details = dialog.findViewById(R.id.et_detail);
        Button btn_save = dialog.findViewById(R.id.btn_save);

        et_name.setText(productList.get(position).getName());
        et_price.setText(productList.get(position).getPrice());
        et_details.setText(productList.get(position).getDetail());


        btn_save.setOnClickListener(v -> {
            if (et_name.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter something", Toast.LENGTH_LONG).show();
            } else {
                productList.get(position).setName(et_name.getText().toString());
                productList.get(position).setPrice(et_price.getText().toString());
                productList.get(position).setDetail(et_details.getText().toString());
                productAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }


        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_profile, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.enquiry:
                Intent intent=new Intent(getApplicationContext(),CreateNew.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                return true;
            case R.id.download_app:
                MerchantInfo bottomSheetDialog = MerchantInfo.getInstance();
                bottomSheetDialog.setCancelable(true);
                bottomSheetDialog.show(getSupportFragmentManager(), TAG);

                return true;
            case R.id.add_product:
                intent=new Intent(getApplicationContext(),ManageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                return true;
            case R.id.publish_app:
                intent=new Intent(getApplicationContext(),App_Pricing.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return true;
    }
}
