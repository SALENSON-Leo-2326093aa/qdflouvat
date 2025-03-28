package fr.univamu.iut.up.utilisateur;

import java.util.ArrayList;

/**
 * The UserRepositoryInterface defines the contract for a user repository
 * that manages user data, including retrieval operations.
 */
public interface UserRepositoryInterface {

    /**
     * Closes any resources associated with the repository.
     */
    void close();

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the User object with the specified ID, or null if not found
     */
    User getUser(String id);

    /**
     * Retrieves all users in the repository.
     *
     * @return a list of all User objects in the repository
     */
    ArrayList<User> getAllUsers();
}
