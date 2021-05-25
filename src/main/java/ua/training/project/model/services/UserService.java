package ua.training.project.model.services;

import ua.training.project.model.repository.UserRepository;
import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;

public class UserService {
    private final UserRepository repository = UserRepository.getInstance();

    public User userRegistration(UserRegistrationDTO userDTO) {
        System.out.println("%%%%%%%%%%%%%%%");
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(Role.USER);
        repository.insertUser(user);
        System.out.println("RRRRRRRR");
        return user;
    }
}
