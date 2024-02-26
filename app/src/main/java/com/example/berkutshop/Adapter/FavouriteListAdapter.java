package com.example.berkutshop.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.DB.DishesDB;
import com.example.berkutshop.Helper.ManagementCart;
import com.example.berkutshop.Helper.ManagementFavourite;
import com.example.berkutshop.R;

import java.util.Objects;

public class FavouriteListAdapter extends RecyclerView.Adapter<FavouriteListAdapter.ViewHolder> {
    @NonNull
    @Override
    public FavouriteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new FavouriteListAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Dish dish = ManagementFavourite.getInstance().getCartUser().get(position);
        holder.productName.setText(dish.getName());
        holder.price.setText(dish.getPrice().split("р")[0] + " р");
        holder.favouriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFavoriteImage(holder.favouriteBtn, ManagementFavourite.getInstance().getCartUser().get(position).isFavourite());
                ManagementFavourite.getInstance().removeFavouriteDish(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManagementCart.getInstance().getIntArrayDequeCart().add(ManagementFavourite.getInstance().getCartUser().get(position));
            }
        });

        updateFavoriteImage(holder.favouriteBtn, ManagementFavourite.getInstance().getCartUser().get(position).isFavourite());
    }

    private void updateFavoriteImage(ImageButton button, boolean isFavorite) {
        int drawableId = isFavorite ? R.drawable.fav_item_press : R.drawable.fav_no_pressed;
        button.setImageResource(drawableId);
    }

    @Override
    public int getItemCount() {
        return ManagementFavourite.getInstance().getCartUser().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton favouriteBtn;
        TextView productName, price;
        Button addBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.price);
            favouriteBtn = itemView.findViewById(R.id.btnFavorite);
            addBtn = itemView.findViewById(R.id.btnAdd);
        }
    }
}
