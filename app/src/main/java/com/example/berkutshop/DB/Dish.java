package com.example.berkutshop.DB;

import lombok.*;


public class Dish {
    public Dish(String name, String description, String composition, String price) {
        Name = name;
        Description = description;
        Composition = composition;
        Price = price;
    }

    private String Name;
    private String Description;
    private String Composition;
    private String Price;

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


