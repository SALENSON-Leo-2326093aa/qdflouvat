package fr.univamu.iut.up.utilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/users")
@ApplicationScoped
public class UserResource {
    private UserService service;

    public UserResource() {}

    @Inject
    public UserResource(UserRepositoryInterface userRepo) {
        this.service = new UserService(userRepo);
    }

    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }

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
