package ru.itis.context.services;

import ru.itis.context.dto.UserDto;
import ru.itis.context.forms.UserForm;

import java.util.Optional;

public interface SignUpService {
    Optional<UserDto> addNewUser(UserForm form);
}
