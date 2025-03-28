package fr.univamu.iut.up.utilisateur;

public class UserAuthenticationService {
    private UserRepositoryInterface userRepo;

    public UserAuthenticationService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    public boolean isValidUser(String id, String password) {
        User user = userRepo.getUser(id);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
