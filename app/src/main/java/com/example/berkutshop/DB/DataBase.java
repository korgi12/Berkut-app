package com.example.berkutshop.DB;
import android.annotation.SuppressLint;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DataBase {

    protected Connection databaseConnection=null;

    private final String host = "roundhouse.proxy.rlwy.net";

    private final String database = "railway";
    private final int port = 33065;
    private final String user = "postgres";
    private final String pass = "54Ca51Aeaaa2A6C6fa3c3aCcFaCDC6DB";
    private String url = "jdbc:postgresql://roundhouse.proxy.rlwy.net:33065/railway";
    private Boolean status = null;

    public DataBase()
    {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
        //this.disconnect();
        System.out.println("connection status:" + status);
    }

    @WorkerThread
    protected Connection connect()
    {
        if (databaseConnection != null) return databaseConnection;
        try
        {
            Class.forName("org.postgresql.Driver");
            databaseConnection = DriverManager.getConnection(url, user, pass);
            status = true;
            System.out.println("connected:" + status);
        }
        catch (Exception e)
        {
            status = false;
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        return databaseConnection;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }
}
