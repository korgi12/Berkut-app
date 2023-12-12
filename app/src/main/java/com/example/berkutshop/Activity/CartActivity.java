package com.example.berkutshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.berkutshop.Adapter.CartListAdapter;
import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.Helper.ManagementCart;
import com.example.berkutshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Map;
import java.util.TreeMap;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.bottomCart);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (R.id.bottomShop == item.getItemId()) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
            } else if (R.id.bottomExplore == item.getItemId()) {
                startActivity(new Intent(getApplicationContext(), ExploreActivity.class));
                overridePendingTransition(0, 0);
                finish();
            } else if (R.id.bottomCart == item.getItemId()) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                overridePendingTransition(0, 0);
                finish();
            } else if (R.id.bottomFavourite == item.getItemId()) {
                startActivity(new Intent(getApplicationContext(), FavouriteActivity.class));
                overridePendingTransition(0, 0);
                finish();
            } else if (R.id.bottomAccount == item.getItemId()) {
                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }

            return false;
        });
        initView();
        initList();
        changeTotal();
    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.txtTotalFee);
        taxTxt = findViewById(R.id.txtTax);
        deliveryTxt = findViewById(R.id.txtDelveryService);
        totalTxt = findViewById(R.id.textView20);
        scrollView = findViewById(R.id.scrollable);
        recyclerViewList = findViewById(R.id.rvItemsInCart);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(ManagementCart.getMapCartUser());
        recyclerViewList.setAdapter(adapter);
    }

    private void changeTotal() {
        int total = 0;
        int sumCount = 0;
        for (Map.Entry<Dish, Integer> s : ManagementCart.getMapCartUser().entrySet()) {
            total += Integer.parseInt(s.getKey().getPrice().split("р")[0]) * s.getValue();
            sumCount += s.getValue();
        }
        totalTxt.setText(String.valueOf(total));
        totalFeeTxt.setText(String.valueOf(sumCount));

    }

}