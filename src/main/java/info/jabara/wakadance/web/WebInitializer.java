/**
 *
 */
package info.jabara.wakadance.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import jabara.servlet.HeartBeat;

/**
 * @author jabaraster
 */
@WebListener
public class WebInitializer implements ServletContextListener {

    @Override
    public void contextDestroyed(final ServletContextEvent pEvent) {
        // nop
    }

    @Override
    public void contextInitialized(final ServletContextEvent pEvent) {
        pEvent.getServletContext().addServlet("/ping", HeartBeat.class); //$NON-NLS-1$
    }

}
