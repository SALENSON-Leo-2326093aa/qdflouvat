package fr.univamu.iut.up.produit;

import fr.univamu.iut.up.DBManager;

import java.sql.*;
import java.util.ArrayList;

/**
 * The ProductRepositoryMariadb class implements the ProductRepositoryInterface
 * and provides database operations for managing products using a MariaDB database.
 */
public class ProductRepositoryMariadb implements ProductRepositoryInterface {
    private Connection dbConnection;

    /**
     * Constructs a ProductRepositoryMariadb instance and establishes a database connection.
     *
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the MariaDB JDBC driver is not found
     */
    public ProductRepositoryMariadb() throws SQLException, ClassNotFoundException {
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
     * Retrieves a product by its ID from the database.
     *
     * @param id the ID of the product to retrieve
     * @return the Product object with the specified ID, or null if not found
     */
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

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all Product objects in the database
     */
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

    /**
     * Updates the details of a product with the specified ID in the database.
     *
     * @param id    the ID of the product to update
     * @param name  the new name of the product
     * @param price the new price of the product
     * @param stock the new stock quantity of the product
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateProduct(String id, String name, double price, int stock) {
        String query = "UPDATE Product SET name=?, price=?, stock=? WHERE id=?";
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
