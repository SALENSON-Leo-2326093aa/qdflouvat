package fr.univamu.iut.up.produit;

import java.util.ArrayList;

public interface ProductRepositoryInterface {
    void close();
    Product getProduct(String id);
    ArrayList<Product> getAllProducts();
    boolean updateProduct(String id, String name, double price, int stock);
}
