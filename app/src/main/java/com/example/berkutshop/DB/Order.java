package com.example.berkutshop.DB;

import com.example.berkutshop.Helper.ManagmentHistoryOrder;

public class Order {
    private String numberOrder;

    public Order(String numberOrder, String countDishes, String totalPrice) {
        this.numberOrder = numberOrder;
        this.countDishes = countDishes;
        this.totalPrice = totalPrice;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getCountDishes() {
        return countDishes;
    }

    public void setCountDishes(String countDishes) {
        this.countDishes = countDishes;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    private String countDishes;
    private String totalPrice;
}
