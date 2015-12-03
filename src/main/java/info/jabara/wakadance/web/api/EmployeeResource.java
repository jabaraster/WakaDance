/**
 * 
 */
package info.jabara.wakadance.web.api;

import info.jabara.wakadance.entity.EEmployee;
import info.jabara.wakadance.service.EmployeeService;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author jabaraster
 */
@Path("employee")
public class EmployeeResource {

    @Inject
    EmployeeService employeeService;

    /**
     * @return -
     */
    @Path("/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<EEmployee> getAll() {
        return this.employeeService.getAll();
    }
}
