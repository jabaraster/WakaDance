/**
 *
 */
package info.jabara.wakadance.service.internal;

import info.jabara.wakadance.Environment;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author jabaraster
 */
@Dependent
public class EntityManagerProvider {

    @PersistenceContext(unitName = Environment.APPLICATION_NAME)
    @Produces
    EntityManager entityManager;
}
