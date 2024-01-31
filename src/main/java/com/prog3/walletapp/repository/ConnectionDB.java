package com.prog3.walletapp.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private final String url;
    private final String username;
    private final String password;
    private Connection connection;
    private static ConnectionDB instance;
    private ConnectionDB(){
        this.url=System.getenv("url");
        this.username=System.getenv("username");
        this.password=System.getenv("password");
        createConnection();
    }
    private Connection createConnection() {
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {

        }
        return connection;
    }
    //  singleton connection
    public static ConnectionDB getConnection(){
        if(instance==null)
            instance=new ConnectionDB();
        return instance;
    }
    public Connection getConnectionInstance(){
        return connection;
    }
}

