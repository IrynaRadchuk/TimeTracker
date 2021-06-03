package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Main controller Command interface
 *
 * @author Iryna Radchuk
 */
public interface Command {

    /**
     * @param request Http server request
     * @return Page address
     */
    String execute(HttpServletRequest request);
}
