package ru.itis.context.services;

import ru.itis.context.dto.UserDto;
import ru.itis.context.models.User;

import java.util.List;

public interface UsersDtoProcessor {
    List<UserDto> getDto(List<User> users);
}
