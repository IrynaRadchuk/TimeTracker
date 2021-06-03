package ua.training.project.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Set characters encoding filtration
 * @author Iryna Radchuk
 * @see Filter
 */
public class EncodingFilter implements Filter {
    private String encoding;

    /**
     * Set character encoding of response and request
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String requestEncoding = servletRequest.getCharacterEncoding();
        if (requestEncoding == null) {
            servletRequest.setCharacterEncoding(encoding);
        }
        servletResponse.setContentType("text/html; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void destroy() {
    }
}
