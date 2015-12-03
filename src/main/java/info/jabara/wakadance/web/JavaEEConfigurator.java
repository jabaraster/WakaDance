/**
 *
 */
package info.jabara.wakadance.web;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import jabara.servlet.HeartBeat;

/**
 * @author jabaraster
 */
@WebListener
public class JavaEEConfigurator implements ServletContextListener {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    /**
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(@SuppressWarnings("unused") final ServletContextEvent pEvent) {
        // nop
    }

    /**
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(final ServletContextEvent pEvent) {
        setUpHeartBeat(pEvent);
        setUpJpa();
    }

    private void setUpJpa() {
        // JPAメタデータクラスのstaticフィールドを初期化してもらう.
        // これをやっておかないと、アプリケーション中でメタデータクラスを利用している箇所で
        // NPEがスローされてしまう可能性が生じる.
        this.entityManagerFactory.createEntityManager().close();
    }

    private static void setUpHeartBeat(final ServletContextEvent pEvent) {
        pEvent.getServletContext().addServlet(HeartBeat.class.getName(), HeartBeat.class).addMapping("/ping"); //$NON-NLS-1$
    }

}
