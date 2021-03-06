package ua.training.project.constant;

public interface DBStatement {
    String USER_CREATE = "insert into user (user_email, user_password, user_first_name, user_last_name, role_id)  values (?,?,?,?,?);";
    String USER_DELETE = "delete from user where user_id = ?;";
    String USER_UPDATE = "update user set user_email = ?, user_password = ?, user_first_name = ?, user_last_name = ? where user_id =?";
    String USER_UPDATE_BY_ADMIN = "update user set user_email = ?, user_first_name = ?, user_last_name = ?, role_id = (select role_id from user_role where role_name = ?) where user_id =?";
    String USER_CREATE_BY_ADMIN = "insert into user (user_email, user_password, user_first_name, user_last_name, role_id) values (?,'4a7d1ed414474e4033ac29ccb8653d9b',?,?,(select role_id from user_role where role_name = ?));";
    String USER_FIND = "select*from user where user_id = ?;";
    String USER_FIND_BY_EMAIL = "select*from user where user_email = ?;";
    String GET_ALL_USERS = "select*from user";
    String ACTIVITY_CREATE = "insert into activity (activity_name, category_id, activity_ua)  values (?,(select category_id from activity_category where category_name = ?),?);";
    String ACTIVITY_DELETE = "delete from activity where activity_id = ?;";
    String ACTIVITY_CHANGE = "update activity set activity_name = ?, activity_ua = ?, category_id = (select category_id from activity_category where category_name = ?) where activity_id = ?;";
    String ACTIVITY_FIND = "select*from activity where activity_name = ?||activity_ua=?;";
    String GET_ALL_ACTIVITIES = "select*from activity";
    String GET_ALL_ACTIVITIES_WITH_CATEGORIES = "select activity.activity_id, activity.activity_name, activity.activity_ua, activity.category_id, activity_category.category_name from activity left join activity_category on activity.category_id = activity_category.category_id;";
    String CATEGORY_GET_ALL = "select*from activity_category";
    String CHECK_ACTIVITY_PRESENCE = "select activity_duration from user_activity where activity_id = ? && activity_date=? && user_id=?;";
    String USER_ACTIVITY_CREATE = "insert into user_activity (user_id, activity_id, activity_date, activity_duration) values (?,?,?,?);";
    String DELETE_ACTIVITY_TIME = "delete from user_activity where user_id = ? && activity_date = ?";
    String SHOW_USER_ACTIVITIES_BY_DATE = "select user_activity.activity_date, activity.activity_name, activity.activity_ua, user_activity.activity_duration FROM user_activity left join activity ON activity.activity_id=user_activity.activity_id left join user on user_activity.user_id = user.user_id where user.user_id = ?;";
    String SHOW_All_USER_ACTIVITIES = "select activity.activity_name, activity.activity_ua, user_allowed_activity.status from activity left join user_allowed_activity on activity.activity_id = user_allowed_activity.activity_id where user_allowed_activity.user_id = ?;";
    String USER_ALLOWED_ACTIVITY_FIND = "select user.user_email, user_allowed_activity.status, user_allowed_activity.activity_id, activity.activity_name from user_allowed_activity left join user ON user.user_id = user_allowed_activity.user_id left join activity ON activity.activity_id = user_allowed_activity.activity_id where user.user_id = ? AND (activity.activity_name = ?||activity.activity_ua = ?);";
    String USER_ALLOWED_ACTIVITY_CREATE = "insert into user_allowed_activity (user_id, activity_id, status) values (?,?,'PENDING');";
    String USER_ALLOWED_ACTIVITY_APPROVE = "update user_allowed_activity set status = 'APPROVED' where user_id=? and activity_id=?;";
    String USER_DENY_ACTIVITY = "delete from user_allowed_activity where user_id=? and activity_id=?;";
    String ACTIVITY_BY_DATE_DURATION = "select sum(activity_duration) from user_activity where activity_date = ? && user_id = ?;";
    String GET_ALL_PENDING_ACTIVITIES = "select user.user_id, user.user_email, user.user_first_name, user.user_last_name, activity.activity_name from activity left join user_allowed_activity on activity.activity_id = user_allowed_activity.activity_id left join user on user.user_id = user_allowed_activity.user_id where user_allowed_activity.status = 'PENDING';";
    String ACTIVITY_STATISTICS = "SELECT activity.activity_name, activity_category.category_name, count(user_allowed_activity.user_id) as \"user_quantity\" from activity left join activity_category on activity_category.category_id = activity.category_id left join user_allowed_activity on activity.activity_id = user_allowed_activity.activity_id group by user_allowed_activity.activity_id;";
    String USER_STATISTICS_LIMIT = "select user_activity.activity_date, user.user_email, user.user_first_name, user.user_last_name, activity.activity_name, user_activity.activity_duration from user_activity left join user on user_activity.user_id = user.user_id left join activity on activity.activity_id = user_activity.activity_id order by user_activity.activity_date DESC limit ?,?;";
    String ROWS_NUMBER = "select count(user_activity.activity_date), user.user_email, user.user_first_name, user.user_last_name, activity.activity_name, user_activity.activity_duration from user_activity left join user on user_activity.user_id = user.user_id left join activity on activity.activity_id = user_activity.activity_id;";
}
