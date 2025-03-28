package fr.univamu.iut.up.produit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * The ProductResource class is a Jakarta RESTful Web Services resource
 * that provides endpoints for managing products.
 */
@Path("/products")
@ApplicationScoped
public class ProductResource {
    private ProductService service;

    /**
     * Default constructor for the ProductResource class.
     */
    public ProductResource() {}

    /**
     * Constructs a ProductResource with the specified ProductRepositoryInterface.
     *
     * @param productRepo the ProductRepositoryInterface to be used by the service
     */
    @Inject
    public ProductResource(ProductRepositoryInterface productRepo) {
        this.service = new ProductService(productRepo);
    }

    /**
     * Retrieves all products in JSON format.
     *
     * @return a JSON string representing all products
     */
    @GET
    @Produces("application/json")
    public String getAllProducts() {
        return service.getAllProductsJSON();
    }

    /**
     * Retrieves a product by its ID in JSON format.
     *
     * @param id the ID of the product to retrieve
     * @return a JSON string representing the product with the specified ID
     * @throws NotFoundException if the product is not found
     */
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

    /**
     * Updates a product with the specified ID using the provided product data.
     *
     * @param id       the ID of the product to update
     * @param product  the Product object containing the updated data
     * @return a Response indicating the result of the update operation
     * @throws NotFoundException if the product is not found
     */
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
