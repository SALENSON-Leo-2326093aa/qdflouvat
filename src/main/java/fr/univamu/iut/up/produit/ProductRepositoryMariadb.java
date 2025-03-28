package fr.univamu.iut.up.produit;

import fr.univamu.iut.up.DBManager;

import java.sql.*;
import java.util.ArrayList;

public class ProductRepositoryMariadb implements ProductRepositoryInterface {
    private Connection dbConnection;

    public ProductRepositoryMariadb() throws SQLException, ClassNotFoundException {
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
    public Product getProduct(String id) {
        Product product = null;
        String query = "SELECT * FROM Product WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                product = new Product(
                    result.getString("id"),
                    result.getString("name"),
                    result.getDouble("price"),
                    result.getInt("stock")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Product product = new Product(
                    result.getString("id"),
                    result.getString("name"),
                    result.getDouble("price"),
                    result.getInt("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }



    @Override
    public boolean updateProduct(String id, String name, double price, int stock) {
        String query = "UPDATE Product SET name=? price=?, stock=? WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, stock);
            ps.setString(4, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
