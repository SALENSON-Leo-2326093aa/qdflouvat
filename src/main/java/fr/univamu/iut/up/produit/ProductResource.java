package fr.univamu.iut.up.produit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/products")
@ApplicationScoped
public class ProductResource {
    private ProductService service;

    public ProductResource() {}

    @Inject
    public ProductResource(ProductRepositoryInterface productRepo) {
        this.service = new ProductService(productRepo);
    }

    @GET
    @Produces("application/json")
    public String getAllProducts() {
        return service.getAllProductsJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getProduct(@PathParam("id") String id) {
        String result = service.getProductJSON(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateProduct(@PathParam("id") String id, Product product) {
        if (service.updateProduct(id, product)) {
            return Response.ok("updated").build();
        } else {
            throw new NotFoundException();
        }
    }
}
