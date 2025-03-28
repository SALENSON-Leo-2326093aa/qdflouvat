package fr.univamu.iut.up.utilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class UserApplication extends Application {

    @Produces
    private UserRepositoryInterface openDbConnection() {
        try {
            return new UserRepositoryMariadb();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private void closeDbConnection(@Disposes UserRepositoryInterface userRepo) {
        userRepo.close();
    }
}
