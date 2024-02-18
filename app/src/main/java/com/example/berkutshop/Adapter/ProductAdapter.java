package com.example.berkutshop.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.Helper.ManagementCart;
import com.example.berkutshop.R;
import com.example.berkutshop.Activity.ShowDetailActivity;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    public Activity activity;
    private ArrayList<Dish> dishList;

    public ProductAdapter(ArrayList<Dish> treeMap, Activity activity) {
        this.activity = activity;
        this.dishList = treeMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Dish dish = dishList.get(position);
        holder.productName.setText(dish.getName());
        holder.price.setText(dish.getPrice());
        holder.showDetailsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("name", dishList.get(position).getName());
                intent.putExtra("price", dishList.get(position).getPrice());
                intent.putExtra("composition", dishList.get(position).getComposition());
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManagementCart.getInstance().getIntArrayDequeCart().add(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, price;
        Button addBtn, showDetailsItem;


        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.price);
            addBtn = itemView.findViewById(R.id.btnAdd);
            showDetailsItem = itemView.findViewById(R.id.btnInfoFood);

        }
    }
}

