package com.example.berkutshop.Adapter;

import static com.mikepenz.iconics.Iconics.getApplicationContext;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.Helper.ManagementCart;
import com.example.berkutshop.R;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private final Iterator<Map.Entry<Dish, Integer>> iterator;

    public CartListAdapter(ConcurrentHashMap<Dish, Integer> treeMap) {
        iterator = treeMap.entrySet().iterator();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map.Entry<Dish, Integer> entry = iterator.next();
        holder.title.setText(entry.getKey().getName());
        holder.countCurrentDish.setText(entry.getValue().toString());
        holder.feeEachItem.setText(entry.getKey().getPrice());
        holder.totalEachItem.setText(String.valueOf(Integer.parseInt(entry.getKey().getPrice().split("р")[0]) * entry.getValue()));


    }

    @Override
    public int getItemCount() {
        return ManagementCart.getInstance().getMapCartUser().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, totalEachItem, countCurrentDish;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.txtTitleCart);
            feeEachItem = view.findViewById(R.id.feeEachItem);
            totalEachItem = view.findViewById(R.id.totalEachItem);
            countCurrentDish = view.findViewById(R.id.numItems);


        }

    }
}
