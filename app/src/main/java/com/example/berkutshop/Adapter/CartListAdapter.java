package com.example.berkutshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berkutshop.Activity.CartActivity;
import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.Helper.ManagementCart;
import com.example.berkutshop.R;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private CartActivity cartActivity;

    public CartListAdapter(CartActivity cartActivity) {
        this.cartActivity  = cartActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dish currentDish = ManagementCart.getInstance().getMapCartUser().get(position);
        listenCurrentDish(holder, currentDish);
        holder.picCart.setImageResource(currentDish.getPhoto());
        holder.plusDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManagementCart.getInstance().getMapCartUser().get(holder.getAdapterPosition()).setCountInCart(ManagementCart.getInstance().getMapCartUser().get(holder.getAdapterPosition()).getCountInCart() + 1);
                listenCurrentDish(holder, ManagementCart.getInstance().getMapCartUser().get(holder.getAdapterPosition()));
                cartActivity.changeTotalActivity();
            }
        });
        holder.minusDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ManagementCart.getInstance().getMapCartUser().get(holder.getAdapterPosition()).getCountInCart() != 1) {
                    ManagementCart.getInstance().getMapCartUser().get(holder.getAdapterPosition()).setCountInCart(ManagementCart.getInstance().getMapCartUser().get(holder.getAdapterPosition()).getCountInCart() - 1);
                    listenCurrentDish(holder, ManagementCart.getInstance().getMapCartUser().get(holder.getAdapterPosition()));
                } else {
                    ManagementCart.getInstance().getMapCartUser().remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());
                }
                cartActivity.changeTotalActivity();
            }
        });
    }

    public void listenCurrentDish(ViewHolder holder, Dish currentDish) {
        holder.title.setText(currentDish.getName());
        holder.countCurrentDish.setText(String.valueOf(currentDish.getCountInCart()));
        holder.feeEachItem.setText(currentDish.getPrice());
        holder.totalEachItem.setText(String.valueOf(Integer.parseInt(currentDish.getPrice().split("р")[0]) * currentDish.getCountInCart()));
    }

    @Override
    public int getItemCount() {
        return ManagementCart.getInstance().getMapCartUser().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, totalEachItem, countCurrentDish;
        Button minusDish, plusDish;
        ImageView picCart;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.txtTitleCart);
            feeEachItem = view.findViewById(R.id.feeEachItem);
            totalEachItem = view.findViewById(R.id.totalEachItem);
            countCurrentDish = view.findViewById(R.id.numItems);
            minusDish = view.findViewById(R.id.minBtnCart);
            plusDish = view.findViewById(R.id.plusBtnCart);
            picCart = view.findViewById(R.id.picCart);
        }

    }
}
