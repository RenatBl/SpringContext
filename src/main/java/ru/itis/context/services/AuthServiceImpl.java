package ru.itis.context.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.context.forms.AuthForm;
import ru.itis.context.forms.UserForm;
import ru.itis.context.models.User;
import ru.itis.context.repo.UsersRepo;

import java.util.Optional;

@Component
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder encoder;

    public void addNewUser(UserForm form) {
        usersRepo.save(User.builder()
                .userName(form.getUserName())
                .password(encoder.encode(form.getPassword()))
                .email(form.getEmail())
                .build());
    }

    public Optional<User> signIn(AuthForm form) {
        Optional<User> userCandidate = usersRepo.findByUserName(form.getUserName());
        if (userCandidate.isPresent() && encoder.matches(form.getPassword(), userCandidate.get().getPassword())) {
            return userCandidate;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> find(String username) {
        return usersRepo.findByUserName(username);
    }
}
