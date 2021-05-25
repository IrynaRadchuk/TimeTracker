package ua.training.project.model.repository;

import ua.training.project.constant.DBStatement;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.exception.TimeTrackerException;
import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class UserRepository implements AutoCloseable {
    private static Connection connection = null;

    private UserRepository() {
    }

    public static Connection getConnection(String connectionUrl) throws SQLException, IOException {
        InputStream file = new FileInputStream("C:\\Users\\ira\\IdeaProjects\\TimeTracker\\src\\main\\resources\\db.properties");
        Properties prop = new Properties();
        prop.load(file);
        String property = prop.getProperty(connectionUrl);
        System.out.println(property);
        return DriverManager.getConnection(property);
    }

    public static UserRepository getInstance() {
        try {
            connection = UserRepository.getConnection("db.url");
        } catch (SQLException | IOException throwable) {
            throw new TimeTrackerException(500, ExceptionMessage.DB_CONNECTION);
        }
        return new UserRepository();
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
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_DELETE)) {
            statement.setString(1, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void changeEmail(String emailOld, String emailNew) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_CHANGE_EMAIL)) {
            statement.setString(1, emailNew);
            statement.setString(2, emailOld);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public User getUserFromDB(String email) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_FIND)){
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public int countUsers () {
        int result =0;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_COUNT)){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
           result = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
