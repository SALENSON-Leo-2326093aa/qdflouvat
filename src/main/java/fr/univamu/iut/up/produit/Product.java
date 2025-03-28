package fr.univamu.iut.up.produit;

/**
 * The Product class represents a product with an ID, name, price, and stock quantity.
 */
public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;

    /**
     * Default constructor for the Product class.
     */
    public Product() {}

    /**
     * Constructs a Product with the specified ID, name, price, and stock quantity.
     *
     * @param id    the unique identifier for the product
     * @param name  the name of the product
     * @param price the price of the product
     * @param stock the stock quantity of the product
     */
    public Product(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Returns the ID of the product.
     *
     * @return the ID of the product
     */
    public String getId() { return id; }

    /**
     * Sets the ID of the product.
     *
     * @param id the ID to set
     */
    public void setId(String id) { this.id = id; }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product
     */
    public String getName() { return name; }

    /**
     * Sets the name of the product.
     *
     * @param name the name to set
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() { return price; }

    /**
     * Sets the price of the product.
     *
     * @param price the price to set
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Returns the stock quantity of the product.
     *
     * @return the stock quantity of the product
     */
    public int getStock() { return stock; }

    /**
     * Sets the stock quantity of the product.
     *
     * @param stock the stock quantity to set
     */
    public void setStock(int stock) { this.stock = stock; }
}
