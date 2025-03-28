package fr.univamu.iut.up.utilisateur;

/**
 * The UserAuthenticationService class provides methods to authenticate users.
 */
public class UserAuthenticationService {
    private UserRepositoryInterface userRepo;

    /**
     * Constructs a UserAuthenticationService with the specified UserRepositoryInterface.
     *
     * @param userRepo the UserRepositoryInterface to be used by the service
     */
    public UserAuthenticationService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Validates a user's credentials.
     *
     * @param id       the ID of the user to validate
     * @param password the password of the user to validate
     * @return true if the user's credentials are valid, false otherwise
     */
    public boolean isValidUser(String id, String password) {
        User user = userRepo.getUser(id);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
