package ua.training.project.model.services.validation;

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
        if (input == null) {
            return false;
        }
        Matcher m = p.matcher(input);
        return m.matches();
    }
}
