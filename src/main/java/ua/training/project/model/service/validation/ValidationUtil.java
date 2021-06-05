package ua.training.project.model.service.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to validate user input
 *
 * @author Iryna Radchuk
 */
public class ValidationUtil {

    /**
     * Check user input with regex
     *
     * @param regex Regex pattern
     * @param input User input
     */
    public static boolean validate(String regex, String input) {
        Pattern p = Pattern.compile(regex);
        if (StringUtils.isEmpty(input)) {
            return false;
        }
        Matcher m = p.matcher(input);
        return m.matches();
    }
}
