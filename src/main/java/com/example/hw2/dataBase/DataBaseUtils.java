package com.example.hw2.dataBase;

public class DataBaseUtils {
    private final   String url = "secret";
    private final String user = "root";
    private final String password = "root";
    public static String driver = "org.postgresql.Driver";


    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
