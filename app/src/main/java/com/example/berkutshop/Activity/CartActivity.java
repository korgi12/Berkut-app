package com.example.berkutshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berkutshop.Adapter.CartListAdapter;
import com.example.berkutshop.DB.Order;
import com.example.berkutshop.Helper.BadgeManager;
import com.example.berkutshop.Helper.BottomNavigationManager;
import com.example.berkutshop.Helper.ManagementCart;
import com.example.berkutshop.Helper.ManagmentHistoryOrder;
import com.example.berkutshop.R;

import java.util.Random;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    TextView txtDelveryService, totalPrice, totalProducts;
    private double tax;
    private Button checkout;
    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        checkout = findViewById(R.id.checkout);

        BottomNavigationManager.getInstance().setBottomNavigationView(findViewById(R.id.bottomNavigation));
        BottomNavigationManager.getInstance().getBottomNavigationView().setSelectedItemId(R.id.bottomCart);
        BadgeManager.getInstance().showBadge(BottomNavigationManager.getInstance().getBottomNavigationView().getOrCreateBadge(R.id.bottomCart));

        BottomNavigationManager.getInstance().getBottomNavigationView().setOnItemSelectedListener(item -> {
            if (R.id.bottomShop == item.getItemId()) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
        initialCheckout();
        initView();
        initList();
        changeTotalActivity();
    }

    private void initView() {
        txtDelveryService = findViewById(R.id.txtDelveryService);
        totalPrice = findViewById(R.id.totalPrice);
        totalProducts = findViewById(R.id.totalProducts);
        scrollView = findViewById(R.id.scrollable);
        recyclerViewList = findViewById(R.id.rvItemsInCart);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(this);
        recyclerViewList.setAdapter(adapter);
    }

    public void changeTotalActivity() {
        ManagementCart.getInstance().changeTotal();
        totalPrice.setText(ManagementCart.getInstance().getSummaProducts() + 59 + " Р");
        totalProducts.setText(String.valueOf(ManagementCart.getInstance().getTotalProducts()));
        txtDelveryService.setText("59 Р");
    }

    private void initialCheckout() {
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] index = {0};
                BadgeManager.getInstance().showBadge(BottomNavigationManager.getInstance().getBottomNavigationView().getOrCreateBadge(R.id.bottomCart));
                ManagmentHistoryOrder.getInstance().getCartUserHistory().add(0,new Order(String.valueOf(new Random().nextInt()),(String) totalProducts.getText(),(String) totalPrice.getText()));
                totalPrice.setText("0");
                totalProducts.setText("0");
                ManagementCart.getInstance().setSummaProducts(0);
                ManagementCart.getInstance().setTotalProducts(0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    ManagementCart.getInstance().getMapCartUser().forEach(dish -> {
                        adapter.notifyItemRemoved(index[0]);
                        adapter.notifyItemRangeChanged(index[0], adapter.getItemCount());
                        index[0]++;
                    });
                }
                ManagementCart.getInstance().getMapCartUser().clear();

                Toast.makeText(getApplicationContext(), "Ваш заказ принят!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}