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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.training.project.constant.Path.*;

public class AuthorizationFilter implements Filter {
    Map<Role, List<String>> acceptablePages = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        List<String> guestPages = Arrays.asList("/", LOGIN, LOGIN_PRG, REGISTRATION, REGISTRATION_PRG);
        acceptablePages.put(Role.GUEST, guestPages);
        List<String> userPages = Arrays.asList(USER, PROFILE, UPDATE, ACTIVITY_REQUEST, LOGOUT);
        acceptablePages.put(Role.USER, userPages);
        List<String> adminPages = Arrays.asList(MANAGE_USERS,MANAGE_ACTIVITIES,MANAGE_REQUESTS, LOGOUT, DELETE_ACTIVITIES, ADD_ACTIVITIES, ADD_USERS,DELETE_USERS);
        acceptablePages.put(Role.ADMIN, adminPages);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        ServletUtil servletUtil = new ServletUtil();

        Role role = servletUtil.getSessionRole(req);
        System.out.println(" FILTER HERE" + role + req.getRequestURI());
        String reqUri = req.getRequestURI().replace("/tracker", StringUtils.EMPTY);
        System.out.println("reqUri" + reqUri);
        if (session != null) {
            if (role == null) {
                role = Role.GUEST;
            }
            if (acceptablePages.get(role).contains(reqUri)) {
                System.out.println("acceptable uri  = " +reqUri +" role = " + role);
                chain.doFilter(req, resp);
            } else {
                System.err.println("YOU DONT HAVE PERMISSIONS TO BE HERE" + reqUri);
                request.setAttribute("error", ExceptionMessage.PERMISSION_DENIED);
                resp.sendRedirect("/tracker" + acceptablePages.get(role).get(0));
            }
        } else {
            System.out.println("SESSION IS DEAD");
            resp.sendRedirect("/tracker");
        }
    }

    @Override
    public void destroy() {
    }
}
