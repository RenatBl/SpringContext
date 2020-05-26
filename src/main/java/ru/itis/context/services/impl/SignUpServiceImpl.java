package ru.itis.context.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.context.dto.UserDto;
import ru.itis.context.forms.UserForm;
import ru.itis.context.models.User;
import ru.itis.context.repo.UsersRepo;
import ru.itis.context.services.SignUpService;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Optional<UserDto> addNewUser(UserForm form) {
        UserDto userDto;
        Optional<User> optionalUser = usersRepo.findByUsername(form.getUserName());
        if (optionalUser.isPresent()) {
            return Optional.empty();
        } else {
            User user = User.builder()
                    .username(form.getUserName())
                    .password(encoder.encode(form.getPassword()))
                    .build();
            usersRepo.save(user);
            userDto = UserDto.get(user, "name");
            return Optional.ofNullable(userDto);
        }
    }
}
