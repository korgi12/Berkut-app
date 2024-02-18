package com.example.berkutshop.Helper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationManager {

    private static BottomNavigationManager instance;
    private BottomNavigationView bottomNavigationView;

    private BottomNavigationManager() {
        // Приватный конструктор для предотвращения создания нескольких экземпляров.
    }

    public static synchronized BottomNavigationManager getInstance() {
        if (instance == null) {
            instance = new BottomNavigationManager();
        }
        return instance;
    }

    public void setBottomNavigationView(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }
}
