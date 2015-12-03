/**
 *
 */
package info.jabara.wakadance.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jabaraster
 */
@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = -6136769077095541112L;

    @Override
    protected void doGet(final HttpServletRequest pReq, final HttpServletResponse pResp) throws ServletException, IOException {
        pResp.sendRedirect("/post"); //$NON-NLS-1$
    }
}
