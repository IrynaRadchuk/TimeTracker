package ua.training.project.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

import static ua.training.project.constant.SessionCall.LANGUAGE;

/**
 * Filter to change language parameter
 *
 * @author Iryna Radchuk
 * @see Filter
 */
public class LocalizationFilter implements Filter {

    /**
     * Set language parameter to session
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (Objects.nonNull(request.getParameter(LANGUAGE))) {
            request.getSession().setAttribute(LANGUAGE, request.getParameter(LANGUAGE));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}