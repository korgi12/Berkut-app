package com.example.berkutshop.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.DB.DishesDB;
import com.example.berkutshop.Helper.ManagementCart;
import com.example.berkutshop.Helper.ManagementFavourite;
import com.example.berkutshop.R;
import com.example.berkutshop.Activity.ShowDetailActivity;

import java.util.ArrayList;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Dish> dishList;

    public ProductAdapter(ArrayList<Dish> treeMap) {
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
        holder.showDetailsItem.setImageResource(R.drawable.shax);
        holder.productName.setText(dish.getName());
        holder.price.setText(dish.getPrice().split("р")[0]+" р");
        holder.showDetailsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("name", dishList.get(position).getName());
                intent.putExtra("price", dishList.get(position).getPrice().split("р")[0]+" р");
                intent.putExtra("composition", dishList.get(position).getComposition());
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManagementCart.getInstance().getIntArrayDequeCart().add(DishesDB.getTreeMap().get(position));
            }
        });
        holder.favouriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManagementFavourite.getInstance().addFavouriteDish(DishesDB.getTreeMap().get(position));
                Objects.requireNonNull(DishesDB.getTreeMap().get(position)).toggleStateFavourite();
                updateFavoriteImage(holder.favouriteBtn, Objects.requireNonNull(DishesDB.getTreeMap().get(position)).isFavourite());
            }
        });
        updateFavoriteImage(holder.favouriteBtn, Objects.requireNonNull(DishesDB.getTreeMap().get(position)).isFavourite());
    }
    private void updateFavoriteImage(ImageButton button, boolean isFavorite) {
        int drawableId = isFavorite ? R.drawable.fav_item_press : R.drawable.fav_no_pressed;
        button.setImageResource(drawableId);
    }
    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, price;
        Button addBtn;
        ImageButton showDetailsItem, favouriteBtn;



        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.price);
            addBtn = itemView.findViewById(R.id.btnAdd);
            showDetailsItem = itemView.findViewById(R.id.btnInfoFood);
            favouriteBtn = itemView.findViewById(R.id.btnFavorite);
        }
    }
}

