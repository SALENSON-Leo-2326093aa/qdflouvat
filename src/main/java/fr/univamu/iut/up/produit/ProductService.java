package fr.univamu.iut.up.produit;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * The ProductService class provides methods to manage products and convert them to JSON format.
 */
public class ProductService {
    private ProductRepositoryInterface productRepo;

    /**
     * Constructs a ProductService with the specified ProductRepositoryInterface.
     *
     * @param productRepo the ProductRepositoryInterface to be used by the service
     */
    public ProductService(ProductRepositoryInterface productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * Retrieves all products and converts them to a JSON string.
     *
     * @return a JSON string representing all products
     */
    public String getAllProductsJSON() {
        ArrayList<Product> products = productRepo.getAllProducts();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(products);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a product by its ID and converts it to a JSON string.
     *
     * @param id the ID of the product to retrieve
     * @return a JSON string representing the product with the specified ID, or null if not found
     */
    public String getProductJSON(String id) {
        Product product = productRepo.getProduct(id);
        if (product != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                return jsonb.toJson(product);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Updates a product with the specified ID using the provided product data.
     *
     * @param id       the ID of the product to update
     * @param product  the Product object containing the updated data
     * @return true if the update was successful, false otherwise
     */
    public boolean updateProduct(String id, Product product) {
        return productRepo.updateProduct(id, product.getName(), product.getPrice(), product.getStock());
    }
}
