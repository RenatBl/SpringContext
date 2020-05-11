package ru.itis.context.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.context.dto.UserDto;
import ru.itis.context.models.User;
import ru.itis.context.models.enums.Role;
import ru.itis.context.repo.PostsRepo;
import ru.itis.context.services.UsersDtoProcessor;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersDtoProcessorImpl implements UsersDtoProcessor {

    @Autowired
    private PostsRepo postsRepo;

    @Override
    public List<UserDto> getDto(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: users) {
            if (!user.getRole().equals(Role.ADMIN))
                userDtoList.add(UserDto.builder()
                        .id(user.getId())
                        .userName(user.getUsername())
                        .email(user.getEmail())
                        .postsQuantity(postsRepo.findAllByOwner(user.getId()).size())
                        .build());
        }
        return userDtoList;
    }
}
