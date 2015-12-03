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
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import info.jabara.wakadance.model.UploadFileInfo;
import info.jabara.wakadance.model.UploadInfo;
import jabara.general.Empty;
import jabara.general.ExceptionUtil;
import jabara.general.IoUtil;

/**
 * @author jabaraster
 */
public final class WebUtil {

    private static final String KEY_SESSION_MESSAGE = WebUtil.class.getName() + ".SessionMaessage"; //$NON-NLS-1$

    private WebUtil() {
        // nop
    }

    /**
     * @param pRequest -
     * @return -
     */
    public static String popSessionMessage(final HttpServletRequest pRequest) {
        final HttpSession session = pRequest.getSession();
        final String msg = (String) session.getAttribute(KEY_SESSION_MESSAGE);
        session.removeAttribute(KEY_SESSION_MESSAGE);
        if (msg == null) {
            return Empty.STRING;
        }
        return msg;
    }

    static Charset getCharacterEncoding(final HttpServletRequest pRequest) {
        final String c = pRequest.getCharacterEncoding();
        return c == null ? StandardCharsets.UTF_8 : Charset.forName(c);
    }

    static UploadInfo getUploadInfo(final HttpServletRequest pRequest) throws IOException, ServletException {
        String personName = null;
        final List<UploadFileInfo> files = new ArrayList<>();
        for (final Part part : pRequest.getParts()) {
            if (part.getContentType() == null) {
                try (InputStream in = part.getInputStream()) {
                    personName = toString(in, getCharacterEncoding(pRequest));
                }
            } else {
                files.add(writeToTemporaryFile(part));
            }
        }
        return new UploadInfo(personName, files);
    }

    static void setSessionMessage(final HttpServletRequest pRequest, final String pMessage) {
        pRequest.getSession().setAttribute(KEY_SESSION_MESSAGE, pMessage);
    }

    private static File createTemporaryFile(final String suffix) {
        try {
            return File.createTempFile("waka-dance-", "." + suffix, getTemporaryDirectory().toFile()); //$NON-NLS-1$ //$NON-NLS-2$
        } catch (final IOException e) {
            throw ExceptionUtil.rethrow(e);
        }
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
