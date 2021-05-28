package ua.training.project.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Main controller Command interface
 */
public interface Command {
    /**
     * @param request http server request
     * @return page address
     */
    String execute(HttpServletRequest request);
}
