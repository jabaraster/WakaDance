/**
 *
 */
package info.jabara.wakadance.service;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;

/**
 * @author jabaraster
 */
@Dependent
public class UploadFileService {

    private final EntityManager em;

    /**
     * @param pEntityManager -
     */
    public UploadFileService(final EntityManager pEntityManager) {
        this.em = pEntityManager;
    }

}
