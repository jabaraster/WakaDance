/**
 *
 */
package info.jabara.wakadance.service.internal;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import info.jabara.wakadance.Environment;

/**
 * @author jabaraster
 */
@RequestScoped
public class EntityManagerProvider {

    @PersistenceContext(unitName = Environment.APPLICATION_NAME)
    @Produces
    EntityManager entityManager;
}
