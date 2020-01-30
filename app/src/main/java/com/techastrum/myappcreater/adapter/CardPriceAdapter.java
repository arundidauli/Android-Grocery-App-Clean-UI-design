package com.techastrum.myappcreater.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.model.Product;

import java.util.List;

public class CardPriceAdapter extends RecyclerView.Adapter<CardPriceAdapter.MyViewHolder> {
    private List<Product> productList;
    private Context context;

    public CardPriceAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardPriceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trending_card_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {





    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView pack_amount, pack_des;
        private ImageView pack_img;
        private CardView card_select;

        MyViewHolder(View view) {
            super(view);


        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
