package com.example.berkutshop.Helper;

import android.content.Context;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;

public class BadgeManager {
    private static BadgeManager instance;

    private BadgeManager() {
    }

    public static synchronized BadgeManager getInstance() {
        if (instance == null) {
            instance = new BadgeManager();
        }
        return instance;
    }

    public void showBadge(BadgeDrawable badgeDrawable) {
        badgeDrawable.setNumber(ManagementCart.getInstance().getTotalProducts());
        badgeDrawable.setVisible(true);

    }
}
