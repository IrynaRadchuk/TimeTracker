package ua.training.project.model.services;

import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

public class UserUpdateService {
    private final UserRepository repository = UserRepository.getInstance();

    public User userUpdate(UserRegistrationDTO userDTO, int id) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        repository.updateUser(user, id);
        return user;
    }
}
