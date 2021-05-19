package ua.training.project.handler;

import ua.training.project.exception.TimeTrackerException;

public abstract class AbstractHandler {
    //api response instead string
    public String handleRequest(String s){
        try {
            String successResponse = handle(s);
            return successResponse;
        } catch (TimeTrackerException e){
            e.printStackTrace();
            return e.getMessage();
        }
        catch (Exception e){
            e.printStackTrace();
            return "something went wrong";
        }
    }
    abstract String handle(String s);
}
