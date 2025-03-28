package fr.univamu.iut.up;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DBManager class provides a utility to establish a database connection.
 */
public class DBManager {
    private static final String URL = "jdbc:mysql://mysql-turlure.alwaysdata.net:3306/turlure_cooperative";
    private static final String USER = "turlure_projet";
    private static final String PASSWORD = "projetcooperative";

    /**
     * Establishes a connection to the database using the specified URL, user, and password.
     *
     * @return a Connection object representing the database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
