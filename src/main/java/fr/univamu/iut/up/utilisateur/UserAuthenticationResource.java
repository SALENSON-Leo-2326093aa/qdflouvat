package fr.univamu.iut.up.utilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * The UserAuthenticationResource class is a Jakarta RESTful Web Services resource
 * that provides an endpoint for authenticating users.
 */
@Path("/authenticate")
@ApplicationScoped
public class UserAuthenticationResource {
    private UserAuthenticationService authService;

    /**
     * Default constructor for the UserAuthenticationResource class.
     */
    public UserAuthenticationResource() {}

    /**
     * Constructs a UserAuthenticationResource with the specified UserRepositoryInterface.
     *
     * @param userRepo the UserRepositoryInterface to be used by the authentication service
     */
    @Inject
    public UserAuthenticationResource(UserRepositoryInterface userRepo) {
        this.authService = new UserAuthenticationService(userRepo);
    }

    /**
     * Authenticates a user based on the provided Basic Authentication credentials.
     *
     * @param requestContext the context of the HTTP request
     * @return a Response indicating whether the authentication was successful
     * @throws UnsupportedEncodingException if the encoding is not supported
     */
    @GET
    @Produces("text/plain")
    public Response authenticate(@Context ContainerRequestContext requestContext) throws UnsupportedEncodingException {
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            return Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic").build();
        }

        String[] tokens = new String(Base64.getDecoder().decode(authHeader.split(" ")[1])).split(":");
        String id = tokens[0];
        String password = tokens[1];

        boolean isValid = authService.isValidUser(id, password);
        return Response.ok(String.valueOf(isValid)).build();
    }
}
