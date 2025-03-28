package fr.univamu.iut.up;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String URL = "jdbc:mysql://mysql-turlure.alwaysdata.net:3306/turlure_cooperative";
    private static final String USER = "turlure_projet";
    private static final String PASSWORD = "projetcooperative";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}