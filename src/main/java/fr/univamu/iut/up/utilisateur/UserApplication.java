package fr.univamu.iut.up.utilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * The UserApplication class is a Jakarta RESTful Web Services application
 * that manages the lifecycle of the UserRepositoryInterface.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class UserApplication extends Application {

    /**
     * Produces an instance of UserRepositoryInterface, opening a database connection.
     *
     * @return an instance of UserRepositoryInterface
     */
    @Produces
    private UserRepositoryInterface openDbConnection() {
        try {
            return new UserRepositoryMariadb();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Closes the database connection for the given UserRepositoryInterface instance.
     *
     * @param userRepo the UserRepositoryInterface instance to close
     */
    private void closeDbConnection(@Disposes UserRepositoryInterface userRepo) {
        userRepo.close();
    }
}
