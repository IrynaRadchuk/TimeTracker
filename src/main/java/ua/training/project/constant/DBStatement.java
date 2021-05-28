package ua.training.project.constant;

public interface DBStatement {
    String USER_CREATE = "insert into user (user_email, user_password, user_first_name, user_last_name, role_id)  values (?,?,?,?,?);";
    String USER_DELETE = "delete from user where user_email = ?;";
    String USER_CHANGE_ROLE = "update user set role_id = ? where user_id = ?;";
    String USER_CHANGE_EMAIL = "update user set user_email = ? where user_email = ?;";
    String USER_CHANGE_FIRST_NAME = "update user set user_first_name = ? where user_id = ?;";
    String USER_CHANGE_LAST_NAME = "update user set user_last_name = ? where user_id = ?;";
    String USER_CHANGE_PASSWORD = "update user set user_password = ? where user_id = ?;";
    String USER_UPDATE = "update user set user_email = ?, user_password = ?, user_first_name = ?, user_last_name = ? where user_id =?";
    String USER_FIND = "select*from user where user_id = ?;";
    String USER_FIND_BY_EMAIL = "select*from user where user_email = ?;";
    String GET_ALL_USERS = "select*from user";
    String USER_COUNT = "select count(*) from user;";
    String ROLE_CREATE = "insert into user_role (user_id, user_name) values (?,?);";
    String ACTIVITY_CREATE = "insert into activity (activity_name, category_id)  values (?,?);";
    String ACTIVITY_DELETE = "delete from activity where activity_id = ?;";
    String ACTIVITY_CHANGE_CATEGORY = "update activity set category_id = ? where activity_id = ?;";
    String ACTIVITY_CHANGE_NAME = "update activity set activity_name = ? where activity_id = ?;";
    String ACTIVITY_SORT_BY_NAME = "select * from activity order by activity_name;";
    String ACTIVITY_FIND = "select*from activity where activity_name = ?;";
    String GET_ALL_ACTIVITIES = "select*from activity";
    String CATEGORY_CREATE = "insert into activity_category (category_id, category_name)  values (?,?);";
    String CATEGORY_DELETE = "delete from activity_category where category_id = ?;";
    String CATEGORY_CHANGE_NAME = "update activity_category set category_name = ? where category_id = ?;";
    String USER_ACTIVITY_CREATE = "insert into user_activity (user_id, activity_id, activity_date, activity_duration) values (?,?,?,?);";
    String SHOW_USER_ACTIVITIES = "SELECT activity.activity_name, activity.activity_id, activity.category_id, user_activity.activity_date, user_activity.activity_duration, user.user_id, user.role_id, user.user_email, user.user_password, user.user_first_name, user.user_last_name FROM user_activity LEFT JOIN activity ON activity.activity_id=user_activity.activity_id LEFT JOIN user on user_activity.user_id = user.user_id WHERE user.user_email = ? group by activity.activity_name;";
    String SHOW_USER_WORK_BY_DATE = "select user.user_first_name, sum(user_activity.activity_duration) as \"total_working_time\", count(user_activity.activity_id) as \"quantity_of_activities\" from user_activity LEFT JOIN user on user_activity.user_id = user.user_id where user_activity.activity_date = ? group by user.user_first_name;";
    String SHOW_ACTIVITIES_BY_USER_QUANTITY = "select activity.activity_name, count(user_activity.user_id) as \"quantity_of_users\" from user_activity LEFT JOIN activity on user_activity.activity_id = activity.activity_id group by user_activity.activity_id;";
    String USER_ALLOWED_ACTIVITY_FIND = "SELECT user.user_email, user_allowed_activity.status, user_allowed_activity.activity_id, activity.activity_name FROM user_allowed_activity LEFT JOIN user ON user.user_id = user_allowed_activity.user_id LEFT JOIN activity ON activity.activity_id = user_allowed_activity.activity_id  WHERE user.user_id = ? AND activity.activity_name = ?;";
    String USER_ALLOWED_ACTIVITY_CREATE = "insert into user_allowed_activity (user_id, activity_id, status) values (?,?,'PENDING');";
    String USER_ALLOWED_ACTIVITY_APPROVE = "update user_allowed_activity set status = 'APPROVED' where user_id=? and activity_id=?;";
    String USER_DENY_ACTIVITY = "delete from user_allowed_activity where user_id=? and activity_id=?;";
    String ACTIVITY_BY_DATE_DURATION = "select sum(activity_duration) from user_activity where activity_date = ?;";
}
