/**
 *
 */
package info.jabara.wakadance.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import info.jabara.wakadance.model.UploadFileInfo;
import info.jabara.wakadance.model.UploadInfo;
import info.jabara.wakadance.service.UploadFileService;
import jabara.general.ExceptionUtil;
import jabara.general.IoUtil;

/**
 * @author jabaraster
 */
@WebServlet(urlPatterns = "/post", asyncSupported = true)
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
        final UploadInfo uploadInfo = getUploadInfo(pRequest.getParts(), getCharacterEncoding(pRequest));
        this.uploadFileService.insert(uploadInfo);
    }

    private static File createTemporaryFile(final String suffix) {
        try {
            return File.createTempFile("waka-dance-", "." + suffix, getTemporaryDirectory().toFile()); //$NON-NLS-1$ //$NON-NLS-2$
        } catch (final IOException e) {
            throw ExceptionUtil.rethrow(e);
        }
    }

    private static Charset getCharacterEncoding(final HttpServletRequest pRequest) {
        final String c = pRequest.getCharacterEncoding();
        return c == null ? StandardCharsets.UTF_8 : Charset.forName(c);
    }

    private static String getSuffix(final String pFileName) {
        final int lastDotPosition = pFileName.lastIndexOf("."); //$NON-NLS-1$
        if (lastDotPosition != -1) {
            return pFileName.substring(lastDotPosition + 1);
        }
        return null;
    }

    private static Path getTemporaryDirectory() {
        final String path = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$
        final Path dirPath = Paths.get(path, "waka-dance"); //$NON-NLS-1$
        if (!Files.isDirectory(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (@SuppressWarnings("unused") final FileAlreadyExistsException e) {
                // nop
            } catch (final IOException e) {
                throw ExceptionUtil.rethrow(e);
            }
        }
        return dirPath;
    }

    private static UploadInfo getUploadInfo(final Collection<Part> pParts, final Charset pCharset) throws IOException {
        String personName = null;
        final List<UploadFileInfo> files = new ArrayList<>();
        for (final Part part : pParts) {
            if (part.getContentType() == null) {
                try (InputStream in = part.getInputStream()) {
                    personName = toString(in, pCharset);
                }
            } else {
                files.add(writeToTemporaryFile(part));
            }
        }
        return new UploadInfo(personName, files);
    }

    @SuppressWarnings("resource")
    private static String toString(final InputStream pInputStream, final Charset pCharset) throws IOException {
        try (ByteArrayOutputStream mem = new ByteArrayOutputStream()) {
            final InputStream in = IoUtil.toBuffered(pInputStream);
            final byte[] buf = new byte[4096];
            for (int d = in.read(buf); d != -1; d = in.read(buf)) {
                mem.write(buf, 0, d);
            }
            return mem.toString(pCharset.name());
        }
    }

    private static UploadFileInfo writeToTemporaryFile(final Part pPart) {
        final String suffix = getSuffix(pPart.getSubmittedFileName());
        final File outFile = createTemporaryFile(suffix);
        try (final InputStream partIn = pPart.getInputStream(); //
                final BufferedInputStream partBufIn = IoUtil.toBuffered(partIn); //
                final FileOutputStream fileOut = new FileOutputStream(outFile); //
                final BufferedOutputStream fileBufOut = IoUtil.toBuffered(fileOut); //
        ) {
            final byte[] buf = new byte[4096];
            for (int d = partBufIn.read(buf); d != -1; d = partBufIn.read(buf)) {
                fileBufOut.write(buf, 0, d);
            }
        } catch (final IOException e) {
            throw ExceptionUtil.rethrow(e);
        }
        return new UploadFileInfo(outFile.toPath(), pPart.getSubmittedFileName(), pPart.getContentType(), pPart.getSize(), null);
    }
}
