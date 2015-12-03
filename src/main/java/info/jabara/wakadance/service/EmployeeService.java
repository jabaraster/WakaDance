/**
 * 
 */
package info.jabara.wakadance.service;

import info.jabara.wakadance.entity.EEmployee;

import java.util.List;

/**
 * @author jabaraster
 */
public interface EmployeeService {

    /**
     * @return -
     */
    List<EEmployee> getAll();

    /**
     * @param pName -
     * @return -
     */
    EEmployee insert(final String pName);

    /**
     * @param pDeleteTarget -
     */
    void delete(final EEmployee pDeleteTarget);
}
