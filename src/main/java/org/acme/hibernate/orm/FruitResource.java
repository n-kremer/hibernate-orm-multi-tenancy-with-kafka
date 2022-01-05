package org.acme.hibernate.orm;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.jboss.logging.Logger;

@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@Path("/")
public class FruitResource {

  private static final Logger LOGGER = Logger.getLogger(FruitResource.class.getName());

  @Inject EntityManager entityManager;

  @GET
  @Path("fruits")
  public Fruit[] getDefault() {
    return get();
  }

  @GET
  @Path("{tenant}/fruits")
  public Fruit[] getTenant() {
    return get();
  }

  private Fruit[] get() {
    return entityManager
        .createNamedQuery("Fruits.findAll", Fruit.class)
        .getResultList()
        .toArray(new Fruit[0]);
  }
}
