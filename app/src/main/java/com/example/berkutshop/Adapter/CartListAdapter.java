package com.example.berkutshop.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.Helper.ManagementCart;
import com.example.berkutshop.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private List<Dish> dishList;
    private List<Integer> countDishList;

    public CartListAdapter(ConcurrentHashMap<Dish, Integer> treeMap) {
        this.dishList = new ArrayList<>(treeMap.keySet());
        this.countDishList = new ArrayList<>(treeMap.values());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(dishList.get(position).getName());
        holder.num.setText(countDishList.get(position).toString());
        holder.feeEachItem.setText(dishList.get(position).getPrice());
        holder.totalEachItem.setText(String.valueOf(Integer.parseInt(dishList.get(position).getPrice().split("р")[0])*countDishList.get(position)));
    }

    @Override
    public int getItemCount() {
        return countDishList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, totalEachItem, num;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.txtTitleCart);
            feeEachItem = view.findViewById(R.id.feeEachItem);
            totalEachItem = view.findViewById(R.id.totalEachItem);
            num = view.findViewById(R.id.numItems);


        }

    }
}
