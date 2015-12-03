/**
 *
 */
package info.jabara.wakadance.web;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author jabaraster
 */
@WebServlet(urlPatterns = "/post")
@MultipartConfig
public class FormServlet extends HttpServlet {
    private static final long serialVersionUID = -1638257614302230158L;

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(final HttpServletRequest pReq, final HttpServletResponse pResp) throws ServletException, IOException {
        pReq.getRequestDispatcher("WEB-INF/post.jsp").forward(pReq, pResp); //$NON-NLS-1$
    }

    @Override
    protected void doPost(final HttpServletRequest pReq, final HttpServletResponse pResp) throws ServletException, IOException {
        final AsyncContext startAsync = pReq.startAsync();
        startAsync.addListener(new AsyncListener() {

            @Override
            public void onComplete(final AsyncEvent pArg0) throws IOException {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(final AsyncEvent pArg0) throws IOException {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartAsync(final AsyncEvent pArg0) throws IOException {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTimeout(final AsyncEvent pArg0) throws IOException {
                // TODO Auto-generated method stub

            }
        });
        startAsync.start(new Runnable() {

            @Override
            public void run() {
                try {
                    for (final Part part : pReq.getParts()) {
                        System.out.println(part.getSubmittedFileName());
                    }
                } catch (final IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (final ServletException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
