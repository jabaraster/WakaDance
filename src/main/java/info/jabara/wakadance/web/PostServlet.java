/**
 *
 */
package info.jabara.wakadance.web;

import java.io.IOException;
import java.net.URLEncoder;

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
@WebServlet(urlPatterns = "/post")
@MultipartConfig
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = -1638257614302230158L;

    @Inject
    UploadFileService         uploadFileService;

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(final HttpServletRequest pReq, final HttpServletResponse pResp) throws ServletException, IOException {
        pReq.getRequestDispatcher("WEB-INF/post.jsp").forward(pReq, pResp); //$NON-NLS-1$
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(final HttpServletRequest pRequest, final HttpServletResponse pResponse) throws ServletException, IOException {
        final UploadInfo uploadInfo = WebUtil.getUploadInfo(pRequest);
        this.uploadFileService.insert(uploadInfo);
        final String redirectUrl;
        if (uploadInfo.getPersonName() == null || uploadInfo.getPersonName().length() == 0) {
            redirectUrl = "/post"; //$NON-NLS-1$
        } else {
            redirectUrl = "/post?person=" + URLEncoder.encode(uploadInfo.getPersonName(), WebUtil.getCharacterEncoding(pRequest).name()); //$NON-NLS-1$
        }
        WebUtil.setSessionMessage(pRequest, "動画をアップロードしました。ご協力ありがとうございました。"); //$NON-NLS-1$
        pResponse.sendRedirect(redirectUrl);
    }
}
