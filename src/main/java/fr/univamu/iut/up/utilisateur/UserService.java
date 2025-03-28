package fr.univamu.iut.up.utilisateur;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * The UserService class provides methods to manage users and convert them to JSON format.
 */
public class UserService {
    private UserRepositoryInterface userRepo;

    /**
     * Constructs a UserService with the specified UserRepositoryInterface.
     *
     * @param userRepo the UserRepositoryInterface to be used by the service
     */
    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Retrieves all users and converts them to a JSON string.
     * Sensitive information such as email and password are removed from the JSON output.
     *
     * @return a JSON string representing all users
     */
    public String getAllUsersJSON() {
        ArrayList<User> users = userRepo.getAllUsers();
        for (User user : users) {
            user.setEmail("");
            user.setPassword("");
        }
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(users);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a user by their ID and converts them to a JSON string.
     *
     * @param id the ID of the user to retrieve
     * @return a JSON string representing the user with the specified ID, or null if not found
     */
    public String getUserJSON(String id) {
        User user = userRepo.getUser(id);
        if (user != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                return jsonb.toJson(user);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
