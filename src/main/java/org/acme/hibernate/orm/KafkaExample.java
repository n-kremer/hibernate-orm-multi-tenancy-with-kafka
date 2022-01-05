package org.acme.hibernate.orm;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import java.time.Duration;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

@ApplicationScoped
public class KafkaExample {
  private static final Logger LOGGER = Logger.getLogger(KafkaExample.class.getName());
  private final Random random = new Random();

  @Inject EntityManager entityManager;

  @Outgoing("prices-out")
  public Multi<Double> generate() {
    return Multi.createFrom().ticks().every(Duration.ofSeconds(5)).map(x -> random.nextDouble());
  }

  @Incoming("prices")
  @Blocking
  public void receivePrice(Double price) {
    LOGGER.info("Received price " + price);
    // Expected to see different fruits, but it seems like the CustomTenantResoler is only executed
    // once. After that every request uses the same schema.
    LOGGER.info("Selected fruit " + get()[0].getName());
  }

  private Fruit[] get() {
    return entityManager
        .createNamedQuery("Fruits.findAll", Fruit.class)
        .getResultList()
        .toArray(new Fruit[0]);
  }
}
