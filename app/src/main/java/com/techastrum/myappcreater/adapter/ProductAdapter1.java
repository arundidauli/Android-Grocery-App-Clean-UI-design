package com.techastrum.myappcreater.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techastrum.myappcreater.Interface.Onholderitemclick;
import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.activities.ScrollingActivity;
import com.techastrum.myappcreater.model.Product;
import com.techastrum.myappcreater.utils.Utils;

import java.util.List;

public class ProductAdapter1 extends RecyclerView.Adapter<ProductAdapter1.MyViewHolder> {
    private List<Product> productList;
    private Context context;
    private Onholderitemclick onholderitemclick;
    public ProductAdapter1(List<Product> productList, Context context, Onholderitemclick onholderitemclick) {
        this.productList = productList;
        this.context = context;
        this.onholderitemclick = onholderitemclick;
    }

    @NonNull
    @Override
    public ProductAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_custom_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(productList.get(position).getName());
        holder.price.setText(productList.get(position).getPrice());
        holder.detail.setText(productList.get(position).getDetail());
        Glide.with(context).load(Utils.StringToBitMap(productList.get(position).getImageurl()))
                .placeholder(R.drawable.android)
                .into(holder.image);

        holder.delete.setOnClickListener(v -> {
            productList.remove(position);
            notifyDataSetChanged();
        });

        holder.book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ScrollingActivity.class);
                intent.putExtra("image",productList.get(position).getImageurl());
                intent.putExtra("title",productList.get(position).getName());
                intent.putExtra("des",productList.get(position).getDetail());
                intent.putExtra("price",productList.get(position).getPrice());
                context.startActivity(intent);
            }
        });

        holder.title.setOnClickListener(v -> {
            onholderitemclick.onholderitemclick_listner(position);
        });
        holder.price.setOnClickListener(v -> {
            onholderitemclick.onholderitemclick_listner(position);

        });
        holder.detail.setOnClickListener(v -> {
            onholderitemclick.onholderitemclick_listner(position);

        });

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, detail, price,book_now;
        private ImageView image,delete;

        MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            price =  view.findViewById(R.id.price);
            detail = view.findViewById(R.id.detail);
            image = view.findViewById(R.id.image);
            delete = view.findViewById(R.id.delete);
            book_now = view.findViewById(R.id.book_now);
        }
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }




}
