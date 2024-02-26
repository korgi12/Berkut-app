package com.example.berkutshop.Helper;

import android.os.Build;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.R;

import java.util.ArrayDeque;
import java.util.concurrent.CopyOnWriteArrayList;

public class ManagementCart {
    private static ManagementCart instance;
    private CopyOnWriteArrayList<Dish> cartUser = new CopyOnWriteArrayList<>();

    public ArrayDeque<Dish> getIntArrayDequeCart() {
        return intArrayDequeCart;
    }

    private ArrayDeque<Dish> intArrayDequeCart = new ArrayDeque<>();

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public void setSummaProducts(int summaProducts) {
        this.summaProducts = summaProducts;
    }

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

    public CopyOnWriteArrayList<Dish> getMapCartUser() {
        return cartUser;
    }


    public void changeCart() {
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!intArrayDequeCart.isEmpty()) {
                        Dish currentDish = intArrayDequeCart.poll();

                        if (currentDish != null) {
                            if (!cartUser.contains(currentDish)) {
                                currentDish.setCountInCart(1);
                                cartUser.add(currentDish);
                            } else {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    currentDish.setCountInCart(currentDish.getCountInCart() + 1);
                                }
                            }
                            changeTotal();
                            BadgeManager.getInstance().showBadge(BottomNavigationManager.getInstance().getBottomNavigationView().getOrCreateBadge(R.id.bottomCart));
                        }
                    }
                }

            }
        });
        c.setName("Подсчет суммы и количества");
        c.start();
    }

    public void changeTotal() {
        summaProducts = 0;
        totalProducts = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cartUser.forEach(dish -> {
                summaProducts += Integer.parseInt(dish.getPrice().split("р")[0]) * dish.getCountInCart();
                totalProducts += dish.getCountInCart();
            });
        }
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public int getSummaProducts() {
        return summaProducts;
    }
}
