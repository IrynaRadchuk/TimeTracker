package ua.training.project.controller.command;

import org.apache.commons.lang3.StringUtils;
import ua.training.project.controller.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;

public class PRG {
    protected ServletUtil servletUtil = new ServletUtil();

    protected boolean checkPRG(HttpServletRequest request, String url) {
        String prgUrl = servletUtil.getPrgUrl(request);
        return StringUtils.isNoneEmpty(prgUrl) && url.equals(prgUrl);
    }
    protected void executePRG(HttpServletRequest request) {
        String errorMessage = servletUtil.getErrorMessage(request);
        if (StringUtils.isNoneEmpty(errorMessage)) {
            request.setAttribute("error", errorMessage);
        }
    }
}
