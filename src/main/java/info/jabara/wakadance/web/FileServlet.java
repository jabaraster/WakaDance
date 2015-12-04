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

import info.jabara.wakadance.entity.EUploadFile;
import info.jabara.wakadance.entity.SendState;
import info.jabara.wakadance.service.UploadFileService;
import jabara.general.NotFound;
import jabara.jpa.entity.Id;

/**
 * @author jabaraster
 */
@WebServlet(urlPatterns = "/file")
public class FileServlet extends HttpServlet {
    private static final long serialVersionUID = -3344592215212613546L;

    @Inject
    private UploadFileService uploadFileService;

    @Override
    protected void doPost(final HttpServletRequest pReq, final HttpServletResponse pResp) throws ServletException, IOException {
        try {
            final long id = Long.parseLong(pReq.getParameter("id")); //$NON-NLS-1$
            this.uploadFileService.updateState(new Id<EUploadFile>(id), SendState.DOWNLOADED);

        } catch (@SuppressWarnings("unused") final NumberFormatException | NotFound e) {
            pResp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
