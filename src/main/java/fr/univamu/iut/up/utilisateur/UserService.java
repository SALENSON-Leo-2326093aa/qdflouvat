package fr.univamu.iut.up.utilisateur;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class UserService {
    private UserRepositoryInterface userRepo;

    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

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
