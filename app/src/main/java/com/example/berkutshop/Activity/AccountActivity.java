package com.example.berkutshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.berkutshop.Adapter.HistoryOrderAdapter;
import com.example.berkutshop.Adapter.ProductAdapter;
import com.example.berkutshop.DB.DishesDB;
import com.example.berkutshop.Helper.BadgeManager;
import com.example.berkutshop.Helper.BottomNavigationManager;
import com.example.berkutshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationManager.getInstance().setBottomNavigationView(findViewById(R.id.bottomNavigation));
        BottomNavigationManager.getInstance().getBottomNavigationView().setSelectedItemId(R.id.bottomAccount);
        BadgeManager.getInstance().showBadge(BottomNavigationManager.getInstance().getBottomNavigationView().getOrCreateBadge(R.id.bottomCart));

        BottomNavigationManager.getInstance().getBottomNavigationView().setOnItemSelectedListener(item -> {
            if (R.id.bottomShop == item.getItemId()) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0,0);
                finish();
            } else if (R.id.bottomCart == item.getItemId()) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                overridePendingTransition(0,0);
                finish();
            } else if (R.id.bottomFavourite == item.getItemId()) {
                startActivity(new Intent(getApplicationContext(), FavouriteActivity.class));
                overridePendingTransition(0,0);
                finish();
            } else if (R.id.bottomAccount == item.getItemId()) {
                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                overridePendingTransition(0,0);
                finish();
            }

            return false;
        });
        recycleViewPopularDish();
    }
    private void recycleViewPopularDish() {
        recyclerView = findViewById(R.id.recyclerViewOrders);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        HistoryOrderAdapter adapter = new HistoryOrderAdapter();
        recyclerView.setAdapter(adapter);
    }
}