package com.example.berkutshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berkutshop.Helper.ManagmentHistoryOrder;
import com.example.berkutshop.R;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.old_order_for_account, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.idOrder.setText("Номер заказа "+ManagmentHistoryOrder.getInstance().getCartUserHistory().get(position).getNumberOrder());
        holder.countItem.setText("Товаров "+ManagmentHistoryOrder.getInstance().getCartUserHistory().get(position).getCountDishes());
        holder.totalPrice.setText("Цена "+ManagmentHistoryOrder.getInstance().getCartUserHistory().get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return ManagmentHistoryOrder.getInstance().getCartUserHistory().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idOrder, countItem, totalPrice;


        public ViewHolder(View itemView) {
            super(itemView);
            idOrder = itemView.findViewById(R.id.textOrderNumber);
            countItem = itemView.findViewById(R.id.textItemCount);
            totalPrice = itemView.findViewById(R.id.textTotalAmount);

        }
    }
}
