package com.techastrum.myappcreater.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.activities.App_Pricing;
import com.techastrum.myappcreater.model.Product;
import com.techastrum.myappcreater.utils.Utils;

import java.util.List;

public class CardProductAdapter extends RecyclerView.Adapter<CardProductAdapter.MyViewHolder> {
    private List<Product> productList;
    private Context context;

    public CardProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.pack_des.setText(productList.get(position).getDetail());
        holder.pack_amount.setText(productList.get(position).getPrice());
        holder.card_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"App published",Toast.LENGTH_LONG).show();

            }
        });




    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView pack_amount, pack_des;
        private ImageView pack_img;
        private CardView card_select;

        MyViewHolder(View view) {
            super(view);
            pack_amount =  view.findViewById(R.id.pack_amount);
            pack_des =  view.findViewById(R.id.pack_des);
            pack_img = view.findViewById(R.id.pack_img);
            card_select = view.findViewById(R.id.card_select);

        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
