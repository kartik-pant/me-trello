package trello.repository;

import java.util.Optional;

import trello.models.User;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Long id);
}
