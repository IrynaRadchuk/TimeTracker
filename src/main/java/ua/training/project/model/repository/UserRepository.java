package ua.training.project.model.repository;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.DBStatement;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.exception.TimeTrackerException;
import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepository implements AutoCloseable {
    private static final Logger log = LogManager.getLogger(UserRepository.class);
    private static Connection connection = null;

    private UserRepository() {
    }

    public static Connection getConnection(String connectionUrl) throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //InputStream file = new FileInputStream(ResourceBundle.getBundle("resources").getString("db.url"));
        InputStream file = new FileInputStream("C:\\Users\\ira\\IdeaProjects\\TimeTracker\\src\\main\\resources\\db.properties");
        Properties prop = new Properties();
        prop.load(file);
        String property = prop.getProperty(connectionUrl);
        return DriverManager.getConnection(property);
    }

    public static UserRepository getInstance() {
        try {
            connection = UserRepository.getConnection("db.url");
        } catch (SQLException | IOException throwable) {
            log.error(throwable.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return new UserRepository();
    }

    public boolean checkUserEmailInDB(String email) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_FIND_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return false;
    }

    public void insertUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getRoleId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.GET_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setEmail(resultSet.getString("user_email"));
                user.setPassword(resultSet.getString("user_password"));
                user.setFirstName(resultSet.getString("user_first_name"));
                user.setLastName(resultSet.getString("user_last_name"));
                user.setRole(Role.getRoleById(resultSet.getInt("role_id")));
                users.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return users;
    }

    public void deleteUser(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
    }

    public User getUserFromDB(int id) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_FIND)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("user_id"));
                user.setEmail(resultSet.getString("user_email"));
                user.setPassword(resultSet.getString("user_password"));
                user.setFirstName(resultSet.getString("user_first_name"));
                user.setLastName(resultSet.getString("user_last_name"));
                user.setRole(Role.getRoleById(resultSet.getInt("role_id")));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return user;
    }

    public User getUserFromDBByEmail(String email) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_FIND_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("user_id"));
                user.setEmail(resultSet.getString("user_email"));
                user.setPassword(resultSet.getString("user_password"));
                user.setFirstName(resultSet.getString("user_first_name"));
                user.setLastName(resultSet.getString("user_last_name"));
                user.setRole(Role.getRoleById(resultSet.getInt("role_id")));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return user;
    }

    public int countUsers() {
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_COUNT)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return result;
    }

    public void updateUser(User user, int id) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_UPDATE)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void changeUser(String email, String firstName, String lastName, String role, int id) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_UPDATE_BY_ADMIN)) {
            statement.setString(1, email);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, role);
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
    }

    public void addUser(String email, String firstName, String lastName, String role) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_CREATE_BY_ADMIN)) {
            statement.setString(1, email);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, role);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
    }
}

