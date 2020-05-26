package ru.itis.context.services;

import ru.itis.context.dto.TokenDto;
import ru.itis.context.forms.AuthForm;

import javax.servlet.http.HttpServletResponse;

public interface SignInService {
    TokenDto signIn(AuthForm form, HttpServletResponse resp);
}
