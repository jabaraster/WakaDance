/**
 *
 */
package info.jabara.wakadance.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jabaraster
 */
@WebFilter(urlPatterns = { "/post" })
public class ChracterEncodingFilter implements Filter {

    private static final String ENC = StandardCharsets.UTF_8.name();

    @Override
    public void destroy() {
        // nop
    }

    @Override
    public void doFilter(final ServletRequest pRequest, final ServletResponse pResponse, final FilterChain pChain)
            throws IOException, ServletException {
        ((HttpServletRequest) pRequest).setCharacterEncoding(ENC);
        ((HttpServletResponse) pResponse).setCharacterEncoding(ENC);
        pChain.doFilter(pRequest, pResponse);
    }

    @Override
    public void init(final FilterConfig pArg0) throws ServletException {
        // nop
    }

}
