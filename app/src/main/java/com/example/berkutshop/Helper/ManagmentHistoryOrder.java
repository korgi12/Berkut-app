package com.example.berkutshop.Helper;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.DB.Order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ManagmentHistoryOrder {
    private static ManagmentHistoryOrder instance;

    public List<Order> getCartUserHistory() {
        return cartUserHistory;
    }

    public boolean contains(Dish dish) {
        return cartUserHistory.contains(dish);
    }

    private final List<Order> cartUserHistory = new LinkedList<>();

    private ManagmentHistoryOrder() {
        // Приватный конструктор для предотвращения создания нескольких экземпляров.
    }

    public static synchronized ManagmentHistoryOrder getInstance() {
        if (instance == null) {
            instance = new ManagmentHistoryOrder();
        }
        return instance;
    }

}
