package com.example.berkutshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.DB.DishesDB;
import com.example.berkutshop.Helper.ManagementCart;
import com.example.berkutshop.R;

import java.util.TreeMap;

public class SplashActivity extends AppCompatActivity implements DishesDB.LoadDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new DishesDB(this);
    }

    @Override
    public void onDataLoaded(TreeMap<Integer, Dish> data) {

        ManagementCart.getInstance().changeCart();
        startActivity(new Intent(SplashActivity.this, MainActivity.class));

        finish();
    }

}