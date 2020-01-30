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

import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private List<Product> productList;
    private Context context;
    private Integer count=1;

    public CartAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {




        holder.qty.setText(String.valueOf(count));


            holder.minus.setOnClickListener(v -> {

                count -= 1;
                if (count<=1){
                    count=1;
                    holder.qty.setText(String.valueOf(count));
                }else {
                    holder.qty.setText(String.valueOf(count));
                }


            });


        holder.plus.setOnClickListener(v -> {
            count += 1;

            holder.qty.setText(String.valueOf(count));

        });



    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView pack_amount, qty;
        private ImageView plus,minus,product_image;
        private CardView card_select;

        MyViewHolder(View view) {
            super(view);
            qty =  view.findViewById(R.id.qty);
            minus = view.findViewById(R.id.minus);
            plus = view.findViewById(R.id.plus);

        }
    }
    @Override
    public int getItemCount() {
        return 44;
    }
}
