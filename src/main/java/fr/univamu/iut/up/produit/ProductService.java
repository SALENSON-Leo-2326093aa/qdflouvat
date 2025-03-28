package fr.univamu.iut.up.produit;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class ProductService {
    private ProductRepositoryInterface productRepo;

    public ProductService(ProductRepositoryInterface productRepo) {
        this.productRepo = productRepo;
    }

    public String getAllProductsJSON() {
        ArrayList<Product> products = productRepo.getAllProducts();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(products);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

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

    public boolean updateProduct(String id, Product product) {
        return productRepo.updateProduct(id, product.getName(), product.getPrice(), product.getStock());
    }
}
