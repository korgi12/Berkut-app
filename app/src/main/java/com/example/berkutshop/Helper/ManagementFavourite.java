package com.example.berkutshop.Helper;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.DB.DishesDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ManagementFavourite {
    private static ManagementFavourite instance;

    public List<Dish> getCartUser() {
        return cartUser;
    }

    public boolean contains(Dish dish) {
        return cartUser.contains(dish);
    }

    private final List<Dish> cartUser = new ArrayList<>();

    private ManagementFavourite() {
        // Приватный конструктор для предотвращения создания нескольких экземпляров.
    }

    public static synchronized ManagementFavourite getInstance() {
        if (instance == null) {
            instance = new ManagementFavourite();
        }
        return instance;
    }

    public void addFavouriteDish(Dish dish) {
        if(!cartUser.contains(dish))cartUser.add(dish);

    }
    public void removeFavouriteDish(int position){
        if (!cartUser.isEmpty()) {
            Objects.requireNonNull(cartUser.remove(position)).toggleStateFavourite();
        }
    }


}
