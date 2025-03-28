package fr.univamu.iut.up.utilisateur;

import fr.univamu.iut.up.DBManager;

import java.sql.*;
import java.util.ArrayList;

/**
 * The UserRepositoryMariadb class implements the UserRepositoryInterface
 * and provides database operations for managing users using a MariaDB database.
 */
public class UserRepositoryMariadb implements UserRepositoryInterface {
    private Connection dbConnection;

    /**
     * Constructs a UserRepositoryMariadb instance and establishes a database connection.
     *
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the MariaDB JDBC driver is not found
     */
    public UserRepositoryMariadb() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DBManager.getConnection();
    }

    /**
     * Closes the database connection.
     */
    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Retrieves a user by their ID from the database.
     *
     * @param id the ID of the user to retrieve
     * @return the User object with the specified ID, or null if not found
     */
    @Override
    public User getUser(String id) {
        User user = null;
        String query = "SELECT * FROM User WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                user = new User(
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all User objects in the database
     */
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                User user = new User(
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
