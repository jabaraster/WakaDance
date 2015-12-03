/**
 *
 */
package info.jabara.wakadance.service.internal;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import info.jabara.wakadance.Environment;

/**
 * @author jabaraster
 */
@Dependent
public class EntityManagerFactoryProvider {

    @PersistenceUnit(unitName = Environment.APPLICATION_NAME)
    @Produces
    EntityManagerFactory entityManagerFactory;

}
