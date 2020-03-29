package ru.itis.context.services;

import ru.itis.context.forms.AuthForm;
import ru.itis.context.forms.UserForm;
import ru.itis.context.models.User;

import java.util.Optional;

public interface AuthService {
    void addNewUser(UserForm form);
    Optional<User> signIn(AuthForm form);
    Optional<User> find(String username);
}
