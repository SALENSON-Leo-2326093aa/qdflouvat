package fr.univamu.iut.up.produit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class ProductApplication extends Application {

    @Produces
    private ProductRepositoryInterface openDbConnection() {
        try {
            return new ProductRepositoryMariadb();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private void closeDbConnection(@Disposes ProductRepositoryInterface productRepo) {
        productRepo.close();
    }
}
