package ru.itis.context.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.context.forms.UserForm;
import ru.itis.context.models.User;
import ru.itis.context.repo.UsersRepo;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void addNewUser(UserForm form) {
        usersRepo.save(User.builder()
                .username(form.getUserName())
                .password(encoder.encode(form.getPassword()))
                .email(form.getEmail())
                .build());
    }
}
