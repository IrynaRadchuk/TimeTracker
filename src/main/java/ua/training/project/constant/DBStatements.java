package ua.training.project.constant;

public interface DBStatements {
    String USER_CREATE = "insert into user (user_email, user_password, user_name, user_surname, role_id)  values (?,?,?,?,?);";
    String USER_DELETE = "delete from user where user_email = ?;";
    String USER_CHANGE_ROLE = "update user set role_id = ? where user_id = ?;";
    String USER_CHANGE_EMAIL = "update user set user_email = ? where user_email = ?;";
    String USER_CHANGE_NAME = "update user set user_name = ? where user_id = ?;";
    String USER_CHANGE_SURNAME = "update user set user_surname = ? where user_id = ?;";
    String USER_CHANGE_PASSWORD = "update user set user_password = ? where user_id = ?;";
    String USER_FIND = "select*from user where user_email = ?;";
    String USER_MATCH_PASSWORD = "select from user where user_email = ?";
    String USER_COUNT = "select count(*) from user;";
    String ROLE_CREATE = "insert into user_role (user_id, user_name) values (?,?);";
    String ACTIVITY_CREATE = "insert into activity (activity_name, category_id)  values (?,?);";
    String ACTIVITY_DELETE = "delete from activity where activity_id = ?;";
    String ACTIVITY_CHANGE_CATEGORY = "update activity set category_id = ? where activity_id = ?;";
    String ACTIVITY_CHANGE_NAME = "update activity set activity_name = ? where activity_id = ?;";
    String ACTIVITY_SORT_BY_NAME = "select * from activity order by activity_name;";
    String CATEGORY_CREATE = "insert into activity_category (category_id, category_name)  values (?,?);";
    String CATEGORY_DELETE = "delete from activity_category where category_id = ?;";
    String CATEGORY_CHANGE_NAME = "update activity_category set category_name = ? where category_id = ?;";
    String SHOW_USER_ACTIVITIES = "SELECT activity.activity_name, user_activity.activity_date, user_activity.activity_duration, user.user_name, user.user_surname FROM user_activity LEFT JOIN activity ON activity.activity_id=user_activity.activity_id LEFT JOIN user on user_activity.user_id = user.user_id;";
    String SHOW_USER_WORK_BY_DATE = "select user.user_name, sum(user_activity.activity_duration) as \"total_working_time\", count(user_activity.activity_id) as \"quantity_of_activities\" from user_activity LEFT JOIN user on user_activity.user_id = user.user_id where user_activity.activity_date = ? group by user.user_name;";
    String SHOW_ACTIVITIES_BY_USER_QUANTITY = "select activity.activity_name, count(user_activity.user_id) as \"quantity_of_users\" from user_activity LEFT JOIN activity on user_activity.activity_id = activity.activity_id group by user_activity.activity_id;";
}
