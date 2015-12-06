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
@WebServlet(urlPatterns = "/post2")
public class Post2Servlet extends HttpServlet {
    private static final long serialVersionUID = 6016412711402196671L;

    @Override
    protected void doGet(final HttpServletRequest pReq, final HttpServletResponse pResp) throws ServletException, IOException {
        pReq.getRequestDispatcher("WEB-INF/post2.jsp").forward(pReq, pResp); //$NON-NLS-1$
    }
}
