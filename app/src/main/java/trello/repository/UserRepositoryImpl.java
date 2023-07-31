package trello.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import trello.models.User;

public class UserRepositoryImpl implements UserRepository {
    private static Map<Long, User> users = new HashMap<>();
    private static Long autoIncrementUserId = 1L;

    @Override
    public User save(User user) {
        User createdUser = new User(autoIncrementUserId, user);
        users.put(autoIncrementUserId, createdUser);
        autoIncrementUserId++;
        return createdUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

}
