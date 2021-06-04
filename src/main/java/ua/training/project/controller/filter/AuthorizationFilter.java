package ua.training.project.controller.filter;


import org.apache.commons.lang3.StringUtils;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.ERROR;

/**
 * Filter class to check user role
 *
 * @author Iryna Radchuk
 */
public class AuthorizationFilter implements Filter {
    private Map<Role, List<String>> acceptablePages = new HashMap<>();

    /**
     * Set permission paths to users with different roles
     *
     * @param filterConfig Filter configuration object used by a servlet container
     */
    @Override
    public void init(FilterConfig filterConfig) {
        List<String> guestPages = Arrays.asList("/", LOGIN, REGISTRATION, ERROR_PAGE);
        acceptablePages.put(Role.GUEST, guestPages);
        List<String> userPages = Arrays.asList(ACTIVITY_TIME_CALENDAR, PROFILE, UPDATE, ACTIVITY_REQUEST,
                LOGOUT, SHOW, ERROR_PAGE);
        acceptablePages.put(Role.USER, userPages);
        List<String> adminPages = Arrays.asList(MANAGE_USERS, LOGOUT,
                DELETE_ACTIVITIES, ADD_ACTIVITIES, MANAGE_ACTIVITIES,
                ADD_USERS, DELETE_USERS, MANAGE_REQUESTS, APPROVE_REQUEST,
                DENY_REQUEST, USER_STAT, ACTIVITY_STAT, ERROR_PAGE);
        acceptablePages.put(Role.ADMIN, adminPages);
    }

    /**
     * Check user permission to visit path
     *
     * @param request  Http request to server
     * @param response Http response from server
     * @param chain    Chain of a filtered request for a resource
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        ServletUtil servletUtil = new ServletUtil();
        Role role = servletUtil.getSessionRole(req);
        String reqUri = req.getRequestURI().replace(TRACKER, StringUtils.EMPTY);
        if (Objects.nonNull(session)) {
            if (Objects.isNull(role)) {
                role = Role.GUEST;
            }
            if (acceptablePages.get(role).contains(reqUri)) {
                chain.doFilter(req, resp);
            } else {
                request.setAttribute(ERROR, ExceptionMessage.PERMISSION_DENIED);
                resp.sendRedirect(TRACKER + acceptablePages.get(role).get(0));
            }
        } else {
            resp.sendRedirect(TRACKER);
        }
    }

    @Override
    public void destroy() {
    }
}
