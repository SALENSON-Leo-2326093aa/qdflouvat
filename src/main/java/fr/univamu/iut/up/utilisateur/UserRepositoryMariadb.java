package fr.univamu.iut.up.utilisateur;

import fr.univamu.iut.up.DBManager;

import java.sql.*;
import java.util.ArrayList;

public class UserRepositoryMariadb implements UserRepositoryInterface {
    private Connection dbConnection;

    public UserRepositoryMariadb() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DBManager.getConnection();
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

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
