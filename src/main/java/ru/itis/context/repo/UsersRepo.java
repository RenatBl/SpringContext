package ru.itis.context.repo;

import ru.itis.context.models.User;

import java.util.Optional;

public interface UsersRepo extends CrudRepo<User, Long> {
    Optional<User> findByUserName(String login);
    void updateStatus(User user);
    void update(User user);
}
