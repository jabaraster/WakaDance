/**
 *
 */
package info.jabara.wakadance.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.jabara.wakadance.model.UploadInfo;
import info.jabara.wakadance.service.UploadFileService;

/**
 * @author jabaraster
 */
@WebServlet(urlPatterns = "/ajax-post")
@MultipartConfig
public class AjaxPostServlet extends HttpServlet {
    private static final long serialVersionUID = -3544269426102528194L;

    @Inject
    UploadFileService         uploadFileService;

    @Override
    protected void doPost(final HttpServletRequest pRequest, final HttpServletResponse pResponse) throws ServletException, IOException {
        final UploadInfo uploadInfo = WebUtil.getUploadInfo(pRequest);
        this.uploadFileService.insert(uploadInfo);
    }
}
