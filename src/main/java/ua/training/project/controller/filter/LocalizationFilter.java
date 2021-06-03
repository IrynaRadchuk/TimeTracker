package ua.training.project.controller.filter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter to change language parameter
 *
 * @author Iryna Radchuk
 * @see Filter
 */
public class LocalizationFilter implements Filter {
    private static Logger log = LogManager.getLogger(LocalizationFilter.class);

    /**
     * Set language parameter to session
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getParameter("lang") != null) {
            request.getSession().setAttribute("lang", request.getParameter("lang"));
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