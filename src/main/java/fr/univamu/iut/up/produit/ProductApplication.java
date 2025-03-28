package fr.univamu.iut.up.produit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * The ProductApplication class is a Jakarta RESTful Web Services application
 * that manages the lifecycle of the ProductRepositoryInterface.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class ProductApplication extends Application {

    /**
     * Produces an instance of ProductRepositoryInterface, opening a database connection.
     *
     * @return an instance of ProductRepositoryInterface
     */
    @Produces
    private ProductRepositoryInterface openDbConnection() {
        try {
            return new ProductRepositoryMariadb();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Closes the database connection for the given ProductRepositoryInterface instance.
     *
     * @param productRepo the ProductRepositoryInterface instance to close
     */
    private void closeDbConnection(@Disposes ProductRepositoryInterface productRepo) {
        productRepo.close();
    }
}
