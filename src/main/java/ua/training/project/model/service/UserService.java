package ua.training.project.model.service;

import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

/**
 * Class to create new user
 *
 * @author Iryna Radchuk
 */
public class UserService {
    private final UserRepository repository = UserRepository.getInstance();

    /**
     * Create new user by registration
     *
     * @param userDTO User DTO data
     * @return New user object
     */
    public User userRegistration(UserRegistrationDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(Role.USER);
        repository.insertUser(user);
        return user;
    }
}
