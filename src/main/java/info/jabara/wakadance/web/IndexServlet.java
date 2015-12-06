/**
 *
 */
package info.jabara.wakadance.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.jabara.wakadance.service.UploadFileService;

/**
 * @author jabaraster
 */
@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = -532880564435202197L;

    @Inject
    UploadFileService         uploadFileService;

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(final HttpServletRequest pReq, final HttpServletResponse pResp) throws ServletException, IOException {
        final boolean hideDownloaded = Boolean.parseBoolean(pReq.getParameter("hidedownloaded")); //$NON-NLS-1$
        pReq.setAttribute("files", this.uploadFileService.getAll(hideDownloaded)); //$NON-NLS-1$
        pReq.getRequestDispatcher("WEB-INF/index.jsp").forward(pReq, pResp); //$NON-NLS-1$
    }
}
