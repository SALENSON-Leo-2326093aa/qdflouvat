package fr.univamu.iut.up.utilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

/**
 * The UserResource class is a Jakarta RESTful Web Services resource
 * that provides endpoints for managing users.
 */
@Path("/users")
@ApplicationScoped
public class UserResource {
    private UserService service;

    /**
     * Default constructor for the UserResource class.
     */
    public UserResource() {}

    /**
     * Constructs a UserResource with the specified UserRepositoryInterface.
     *
     * @param userRepo the UserRepositoryInterface to be used by the service
     */
    @Inject
    public UserResource(UserRepositoryInterface userRepo) {
        this.service = new UserService(userRepo);
    }

    /**
     * Retrieves all users in JSON format.
     *
     * @return a JSON string representing all users
     */
    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }

    /**
     * Retrieves a user by their ID in JSON format.
     *
     * @param id the ID of the user to retrieve
     * @return a JSON string representing the user with the specified ID
     * @throws NotFoundException if the user is not found
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") String id) {
        String result = service.getUserJSON(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }
}
