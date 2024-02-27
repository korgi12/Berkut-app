package com.example.berkutshop.DB;

import android.content.Context;
import android.os.Build;
import android.os.Handler;

import com.example.berkutshop.DB.Interface.ISortedMapBD;
import com.example.berkutshop.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DishesDB extends DataBase  {
    private static TreeMap<Integer, Dish> treeMap;
    private LoadDataListener loadDataListener;
    private Context context;

    public interface LoadDataListener {
        void onDataLoaded(TreeMap<Integer, Dish> data);
    }

    public DishesDB(LoadDataListener listener, Context context) {
        this.loadDataListener = listener;
        getAllCategoriesNameDishes();
        this.context = context;
    }
    public void getAllCategoriesNameDishes() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<TreeMap<Integer, Dish>> future = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            future = CompletableFuture.supplyAsync(() -> {
                treeMap = new TreeMap<>();
                try (Connection databaseConnection = connect()){
                    Statement stmt = databaseConnection.createStatement();
                    for (String categoryName: DBLibrary.listCategoriesNameFromDb) {
                        ResultSet dataAboutDish = stmt.executeQuery("select * from " + categoryName);
                        while (dataAboutDish.next())
                            treeMap.put(Integer.parseInt(dataAboutDish.getString("id"))-1,
                                    new Dish(
                                            dataAboutDish.getString("name"),
                                            dataAboutDish.getString("description"),
                                            dataAboutDish.getString("composition"),
                                            dataAboutDish.getString("price"),
                                            context.getResources().getIdentifier("a"+new Random().nextInt(7), "drawable", context.getPackageName())
                                    ));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }, executor);
        }

        // Добавляем колбэк, который будет вызван после завершения загрузки данных
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            future.thenAccept(data -> {
                if (loadDataListener != null) {
                    loadDataListener.onDataLoaded(data);
                }
            });
        }

    }

    public static TreeMap<Integer, Dish> getTreeMap() {
        return treeMap;
    }

}
