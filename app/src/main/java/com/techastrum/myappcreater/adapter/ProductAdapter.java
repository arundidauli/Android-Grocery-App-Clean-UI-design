package com.techastrum.myappcreater.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.model.Product;
import com.techastrum.myappcreater.utils.Utils;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

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

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, detail, price;
        private ImageView image,delete;

        MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            price =  view.findViewById(R.id.price);
            detail = view.findViewById(R.id.detail);
            image = view.findViewById(R.id.image);
            delete = view.findViewById(R.id.delete);
        }
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }
}
