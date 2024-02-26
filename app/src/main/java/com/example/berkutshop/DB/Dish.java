package com.example.berkutshop.DB;

import lombok.*;


public class Dish {
    public Dish(String name, String description, String composition, String price) {
        Name = name;
        Description = description;
        Composition = composition;
        Price = price;
        Favourite = false;
        CountInCart = 0;
    }

    private String Name;
    private String Description;
    private String Composition;
    private String Price;
    private boolean Favourite;

    public int getCountInCart() {
        return CountInCart;
    }

    public void setCountInCart(int countInCart) {
        CountInCart = countInCart;
    }

    private int CountInCart;

    public boolean isFavourite() {
        return Favourite;
    }

    public void toggleStateFavourite() {
        Favourite = !Favourite;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getComposition() {
        return Composition;
    }

    public void setComposition(String composition) {
        Composition = composition;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}


