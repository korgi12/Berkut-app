package com.example.berkutshop.DB;

import com.example.berkutshop.DB.Interface.ISortedMapBD;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DishesDB extends DataBase {
    private TreeMap<Integer, Dish> treeMap;

    public DishesDB() {
        getAllCategoriesNameDishes();
    }

    public Map<Integer, Dish> getAllCategoriesNameDishes() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    treeMap = new TreeMap<>();
                    Statement stmt = databaseConnection.createStatement();
                    for (String categoryName: DBLibrary.listCategoriesNameFromDb) {
                        ResultSet dataAboutDish = stmt.executeQuery("select * from " + categoryName);
                        while (dataAboutDish.next())
                            treeMap.put(Integer.parseInt(dataAboutDish.getString("id")),
                                    new Dish(
                                            dataAboutDish.getString("name"),
                                            dataAboutDish.getString("description"),
                                            dataAboutDish.getString("composition"),
                                            dataAboutDish.getString("price")
                                    ));
                    }

                } catch (Exception e) {
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeMap;
    }

    public TreeMap<Integer, Dish> getTreeMap() {
        return treeMap;
    }
}
