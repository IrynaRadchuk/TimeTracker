package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Main controller Command interface
 * @author Iryna Radchuk
 */
public interface Command {
    /**
     * @param request http server request
     * @return page address
     */
    String execute(HttpServletRequest request);
}
