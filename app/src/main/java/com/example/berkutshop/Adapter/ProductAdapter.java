package com.example.berkutshop.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.MainActivity;
import com.example.berkutshop.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private TreeMap<Integer, Dish> treeMap;
    private List<Dish> dishList;

    public ProductAdapter(TreeMap<Integer, Dish> treeMap ) {
        this.treeMap = treeMap;
        this.dishList = new ArrayList<>(treeMap.values());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dish dish = dishList.get(position);
        holder.productName.setText(dish.getName());
        holder.price.setText(dish.getPrice());

    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.price);

        }
    }
}

