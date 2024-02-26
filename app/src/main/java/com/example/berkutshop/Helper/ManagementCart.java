package com.example.berkutshop.Helper;

import android.os.Build;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.DB.DishesDB;
import com.example.berkutshop.R;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ManagementCart {
    private static ManagementCart instance;
    private ConcurrentHashMap<Dish, Integer> cartUser = new ConcurrentHashMap<>();

    public ArrayDeque<Dish> getIntArrayDequeCart() {
        return intArrayDequeCart;
    }

    private ArrayDeque<Dish> intArrayDequeCart = new ArrayDeque<>();
    private int totalProducts = 0;
    private int summaProducts = 0;

    private ManagementCart() {
        // Приватный конструктор для предотвращения создания нескольких экземпляров.
    }

    public static synchronized ManagementCart getInstance() {
        if (instance == null) {
            instance = new ManagementCart();
        }
        return instance;
    }

    public ConcurrentHashMap<Dish, Integer> getMapCartUser() {
        return cartUser;
    }


    public void changeTotal() {
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!intArrayDequeCart.isEmpty()) {
                        Dish currentDish =intArrayDequeCart.poll();

                        if (currentDish != null) {
                            if (!cartUser.containsKey(currentDish)) {
                                cartUser.put(currentDish, 1);
                            } else {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    cartUser.computeIfPresent(currentDish, (k, v) -> ++v);
                                }
                            }

                            summaProducts = 0;
                            totalProducts = 0;
                            for (Map.Entry<Dish, Integer> s : cartUser.entrySet()) {
                                summaProducts += Integer.parseInt(s.getKey().getPrice().split("р")[0]) * s.getValue();
                                totalProducts += s.getValue();
                            }
                            BadgeManager.getInstance().showBadge(BottomNavigationManager.getInstance().getBottomNavigationView().getOrCreateBadge(R.id.bottomCart));
                        }
                    }
                }

            }
        });
        c.setName("Подсчет суммы и количества");
        c.start();
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public int getSummaProducts() {
        return summaProducts;
    }
}
