package com.example.berkutshop.Helper;

import android.content.Context;

import com.example.berkutshop.DB.Dish;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ManagementCart {
    private static Map<Dish, Integer> cartUser= new ConcurrentHashMap<>();

    public static ConcurrentHashMap<Dish, Integer> getMapCartUser() {
        return (ConcurrentHashMap<Dish, Integer>) cartUser;
    }

    public static void putDishCartUSer(Dish key,Integer value) {
       cartUser.put(key,value);
    }
    public static boolean contain(Dish dish){
        return cartUser.containsKey(dish);
    }
}
