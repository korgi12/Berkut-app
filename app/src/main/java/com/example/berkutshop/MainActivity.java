package com.example.berkutshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.berkutshop.Adapter.ProductAdapter;
import com.example.berkutshop.DB.DishesDB;
import com.example.berkutshop.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;
    private SearchView searchView;
    private RecyclerView recyclerViewPopular;
    private RecyclerView.Adapter adapterPopular;
    private DishesDB dishesDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dishesDB = new DishesDB();

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replacedFragment(new ShopFragment());
        binding.bottomNavigation.setOnItemReselectedListener(item -> {
            if (R.id.shop == item.getItemId()) {
                replacedFragment(new ShopFragment());
            } else if (R.id.explore == item.getItemId()) {
                replacedFragment(new ExploreFragment());
            } else if (R.id.cart == item.getItemId()) {
                replacedFragment(new CartFragment());
            } else if (R.id.favourite == item.getItemId()) {
                replacedFragment(new FavouriteFragment());
            } else if (R.id.account == item.getItemId()) {
                replacedFragment(new AccountFragment());
            }
        });
        recycleViewPopularDish();
    }

    private void recycleViewPopularDish() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        ProductAdapter adapter = new ProductAdapter(dishesDB.getTreeMap());
        recyclerView.setAdapter(adapter);
    }

    private void replacedFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}