package com.example.berkutshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.berkutshop.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView titleText, priceText, descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        initView();
        getBundle();
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        titleText.setText(bundle.getString("name"));
        priceText.setText(bundle.getString("price"));
        descriptionText.setText(bundle.getString("composition"));
    }


    private void initView() {
        titleText = findViewById(R.id.txtFoodName);
        priceText = findViewById(R.id.txtFoodPrice);
        descriptionText = findViewById(R.id.txtDescription);
    }
}