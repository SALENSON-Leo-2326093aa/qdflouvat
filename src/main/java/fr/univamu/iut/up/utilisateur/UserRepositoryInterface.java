package fr.univamu.iut.up.utilisateur;

import java.util.ArrayList;

public interface UserRepositoryInterface {
    void close();
    User getUser(String id);
    ArrayList<User> getAllUsers();
}
