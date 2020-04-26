package ru.itis.context.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.context.dto.UserDto;
import ru.itis.context.models.User;
import ru.itis.context.repo.UsersRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public User getUserById(Long id) {
        Optional<User> userCandidate = usersRepo.find(id);
        if (userCandidate.isPresent()) {
            return userCandidate.get();
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userCandidate = usersRepo.findByUserName(username);
        if (userCandidate.isPresent()) {
            return userCandidate.get();
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public List<User> getAllUsers() {
        return usersRepo.findAll();
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = getUserById(userDto.getId());
        usersRepo.update(User.builder()
                .id(user.getId())
                .username(userDto.getUserName())
                .email(userDto.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .password(user.getPassword())
                .build());
    }

    @Override
    public Optional<User> find(String username) {
        return usersRepo.findByUserName(username);
    }
}
