package ua.training.project.controller;

import ua.training.project.exception.PermissionDeniedException;
import ua.training.project.model.repository.UserActivityRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.training.project.constant.Path.USER_PERSONAL_ACCOUNT;
import static ua.training.project.constant.SessionCall.USER_EMAIL;

public class ActivityTimePostCommand implements Command{
    UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("ACtivity comm"+ request);
        String activityName = request.getParameter("name");
        int duration = Integer.parseInt(request.getParameter("count"));
        String date = request.getParameter("date");
        String day = request.getParameter("day");
        String dateFormat = "";
        Pattern pattern = Pattern.compile ("[A-z]+\\s(?<key>\\b[A-z]+\\s+\\d+\\s+\\d+\\b)");
        Matcher matcher =pattern.matcher(date);
        while (matcher.find()) {
            dateFormat = matcher.group("key").replace("01",day);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US);
        LocalDate localDate = LocalDate.parse(dateFormat,formatter);
        System.out.println(date);
        System.out.println(activityName + " activity + duration  " + duration + " date " + localDate);
        HttpSession session = request.getSession();
        String email = (String) request.getSession().getAttribute(USER_EMAIL);
        System.out.println(userActivityRepository.getAllUserActivities("ivan@i.ua"));
        try {
            userActivityRepository.addActivityForUser(email,activityName,localDate,duration);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (PermissionDeniedException e){
            System.err.println(e.getMessage());
            request.setAttribute("error", e.getMessage());
        }
        return USER_PERSONAL_ACCOUNT;
    }
}
