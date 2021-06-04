package ua.training.project.model.repository;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.DBStatement;
import ua.training.project.exception.DBException;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ua.training.project.constant.Attributes.*;
import static ua.training.project.constant.Path.ERROR_PAGE;

/**
 * Class to handle statements to user database
 *
 * @author Iryna Radchuk
 * @see ConnectionHandler
 * @see AutoCloseable
 */
public class UserRepository extends ConnectionHandler implements AutoCloseable {
    private static final Logger log = LogManager.getLogger(UserRepository.class);
    private static Connection connection = null;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return new UserRepository();
    }

    /**
     * Check if email is used
     */
    public boolean checkUserEmailInDB(String email) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_FIND_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return false;
    }

    /**
     * Register new user
     */
    public void insertUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, DigestUtils.md5Hex(user.getPassword()));
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getRoleId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
    }

    /**
     * Get list of users
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.GET_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(USERS_ID));
                user.setEmail(resultSet.getString(USERS_EMAIL));
                user.setPassword(resultSet.getString(USERS_PASSWORD));
                user.setFirstName(resultSet.getString(USERS_FIRST_NAME));
                user.setLastName(resultSet.getString(USERS_LAST_NAME));
                user.setRole(Role.getRoleById(resultSet.getInt(ROLE_ID)));
                users.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return users;
    }

    /**
     * Delete user
     */
    public void deleteUser(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
    }

    /**
     * Get user by id
     */
    public User getUserFromDB(int id) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_FIND)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(USERS_ID));
                user.setEmail(resultSet.getString(USERS_EMAIL));
                user.setPassword(resultSet.getString(USERS_PASSWORD));
                user.setFirstName(resultSet.getString(USERS_FIRST_NAME));
                user.setLastName(resultSet.getString(USERS_LAST_NAME));
                user.setRole(Role.getRoleById(resultSet.getInt(ROLE_ID)));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return user;
    }

    /**
     * Get user by email
     */
    public User getUserFromDBByEmail(String email) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_FIND_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(USERS_ID));
                user.setEmail(resultSet.getString(USERS_EMAIL));
                user.setPassword(resultSet.getString(USERS_PASSWORD));
                user.setFirstName(resultSet.getString(USERS_FIRST_NAME));
                user.setLastName(resultSet.getString(USERS_LAST_NAME));
                user.setRole(Role.getRoleById(resultSet.getInt(ROLE_ID)));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return user;
    }

    /**
     * Update user information by user
     */
    public void updateUser(User user, int id) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_UPDATE)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, DigestUtils.md5Hex(user.getPassword()));
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
    }

    /**
     * Update user information by admin
     */
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
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
    }

    /**
     * Create user by admin
     */
    public void addUser(String email, String firstName, String lastName, String role) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_CREATE_BY_ADMIN)) {
            statement.setString(1, email);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, role);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
    }

    @Override
    public void close() throws Exception {
        if (Objects.nonNull(connection) && !connection.isClosed()) {
            connection.close();
        }
    }
}
