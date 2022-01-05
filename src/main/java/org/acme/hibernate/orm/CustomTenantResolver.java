package org.acme.hibernate.orm;

import io.quarkus.arc.Unremovable;
import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import org.jboss.logging.Logger;

@RequestScoped
@PersistenceUnitExtension
@Unremovable
public class CustomTenantResolver implements TenantResolver {

  private static final Logger LOG = Logger.getLogger(CustomTenantResolver.class);

  // Removed this and the code using it as the context is not available when receiving a message
  // via Kafka.
  //   @Inject
  //   RoutingContext context;

  @PostConstruct
  public void init() {
    LOG.info("CustomTenantResolver created");
  }

  @Override
  public String getDefaultTenantId() {
    return "base";
  }

  @Override
  public String resolveTenantId() {
    // This code should be replaced by a tenant selection based on a context that is available both
    // for both REST requests and Kafka messages.
    var tenantId = Math.random() > 0.5 ? "mycompany" : getDefaultTenantId();
    LOG.debugv("TenantId = {0}", tenantId);
    return tenantId;
  }
}
