package com.example.berkutshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.berkutshop.Adapter.CartListAdapter;
import com.example.berkutshop.Adapter.FavouriteListAdapter;
import com.example.berkutshop.Helper.BadgeManager;
import com.example.berkutshop.Helper.BottomNavigationManager;
import com.example.berkutshop.Helper.ManagementCart;
import com.example.berkutshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FavouriteActivity extends AppCompatActivity {
    private RecyclerView recyclerViewList;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        BottomNavigationManager.getInstance().setBottomNavigationView(findViewById(R.id.bottomNavigation));
        BottomNavigationManager.getInstance().getBottomNavigationView().setSelectedItemId(R.id.bottomFavourite);
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
        initView();
        initList();
    }
    private void initView() {
        recyclerViewList = findViewById(R.id.rvItemsInFavourite);
    }
    private void initList() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewList.setLayoutManager(layoutManager);
        adapter = new FavouriteListAdapter();
        recyclerViewList.setAdapter(adapter);
    }
}