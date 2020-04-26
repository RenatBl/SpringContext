package ru.itis.context.services;

import ru.itis.context.dto.UserDto;
import ru.itis.context.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    User getUserById(Long id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    void updateUser(UserDto userDto);
    Optional<User> find(String username);
}
