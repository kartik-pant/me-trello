package trello.services;

import java.util.Optional;

import trello.models.User;
import trello.repository.UserRepository;

public class UserService {
    UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(String username, String email, String password) {
        User user = new User(username, email, password);
        return userRepo.save(user);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepo.findById(userId);
    }

}
