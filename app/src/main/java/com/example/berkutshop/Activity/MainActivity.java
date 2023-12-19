package com.example.berkutshop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.berkutshop.Adapter.ProductAdapter;
import com.example.berkutshop.DB.Dish;
import com.example.berkutshop.DB.DishesDB;
import com.example.berkutshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static DishesDB dishesDB = new DishesDB();
    private EditText searchView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search_edit_text);
        initialSearchEditText();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottomShop);

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
        recycleViewPopularDish();
    }

    private void initialSearchEditText() {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }


        });
    }

    private void filter(String text) {
        ArrayList<Dish> filterItem = new ArrayList<>();
        for (Dish tmp : dishesDB.getTreeMap().values()){
            if (tmp.getName().toLowerCase().contains(text.toLowerCase())){
                filterItem.add(tmp);
            }
        }
        ProductAdapter adapter = new ProductAdapter(filterItem, this);
        recyclerView.setAdapter(adapter);
    }


    private void recycleViewPopularDish() {
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        ProductAdapter adapter = new ProductAdapter(new ArrayList<>(dishesDB.getTreeMap().values()), this);
        recyclerView.setAdapter(adapter);
    }

//    private void replacedFragment(Object activity) {
//        startActivity(new Intent(getApplicationContext(), activity.class));
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        finish();
//
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}