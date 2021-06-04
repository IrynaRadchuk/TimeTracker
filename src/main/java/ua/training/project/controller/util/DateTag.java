package ua.training.project.controller.util;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom tag that shows current date
 *
 * @author Iryna Radchuk
 * @see SimpleTagSupport
 */
public class DateTag extends SimpleTagSupport {

    /**
     * Show current date
     */
    public void doTag() throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        JspWriter out = getJspContext().getOut();
        out.println(dtf.format(now));
    }
}
