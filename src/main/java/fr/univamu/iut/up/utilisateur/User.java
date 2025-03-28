package fr.univamu.iut.up.utilisateur;

/**
 * The User class represents a user with an ID, name, email, and password.
 */
public class User {
    private String id;
    private String name;
    private String email;
    private String password;

    /**
     * Default constructor for the User class.
     */
    public User() {}

    /**
     * Constructs a User with the specified ID, name, email, and password.
     *
     * @param id       the unique identifier for the user
     * @param name     the name of the user
     * @param email    the email address of the user
     * @param password the password of the user
     */
    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public String getId() { return id; }

    /**
     * Sets the ID of the user.
     *
     * @param id the ID to set
     */
    public void setId(String id) { this.id = id; }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() { return name; }

    /**
     * Sets the name of the user.
     *
     * @param name the name to set
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() { return email; }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() { return password; }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) { this.password = password; }
}
