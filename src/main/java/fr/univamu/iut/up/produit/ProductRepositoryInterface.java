package fr.univamu.iut.up.produit;

import java.util.ArrayList;

/**
 * The ProductRepositoryInterface defines the contract for a product repository
 * that manages product data, including retrieval and update operations.
 */
public interface ProductRepositoryInterface {

    /**
     * Closes any resources associated with the repository.
     */
    void close();

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the Product object with the specified ID, or null if not found
     */
    Product getProduct(String id);

    /**
     * Retrieves all products in the repository.
     *
     * @return a list of all Product objects in the repository
     */
    ArrayList<Product> getAllProducts();

    /**
     * Updates the details of a product with the specified ID.
     *
     * @param id    the ID of the product to update
     * @param name  the new name of the product
     * @param price the new price of the product
     * @param stock the new stock quantity of the product
     * @return true if the update was successful, false otherwise
     */
    boolean updateProduct(String id, String name, double price, int stock);
}
