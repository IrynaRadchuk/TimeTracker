package ua.training.project.controller.command;

import org.apache.commons.lang3.StringUtils;
import ua.training.project.controller.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Parent class to realize PRG
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class PRG {
    protected ServletUtil servletUtil = new ServletUtil();
    /**
     * Check if redirected from post request
     * @param request Http request
     * @param url Post request url
     * @return True if redirected from post request
     */
    protected boolean checkPRG(HttpServletRequest request, String url) {
        String prgUrl = servletUtil.getPrgUrl(request);
        return StringUtils.isNoneEmpty(prgUrl) && url.equals(prgUrl);
    }

    /**
     * Set attribute error messages from post request
     * @param request Http request
     */
    protected void executePRG(HttpServletRequest request) {
        String errorMessage = servletUtil.getErrorMessage(request);
        if (StringUtils.isNoneEmpty(errorMessage)) {
            request.setAttribute("error", errorMessage);
        }
    }
}
