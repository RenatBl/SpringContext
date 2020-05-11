package ru.itis.context.repo;

import ru.itis.context.models.Chat;
import ru.itis.context.models.User;

import java.util.Optional;

public interface ChatRepo extends CrudRepo<Chat, Long> {
    Optional<Chat> findByUser(User user);
}
