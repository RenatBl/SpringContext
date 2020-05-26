package ru.itis.context.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.itis.context.models.Chat;
import ru.itis.context.models.User;
import ru.itis.context.models.enums.Role;
import ru.itis.context.repo.ChatRepo;
import ru.itis.context.repo.UsersRepo;
import ru.itis.context.security.jwt.details.UserDetailsImpl;
import ru.itis.context.services.ChatService;

import java.util.List;
import java.util.Optional;

@Component
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public Chat getChatByUser(String username) {
        return chatRepo.findByUser(
                usersRepo.findByUsername(username
                ).orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                )).orElseThrow(() ->
                new IllegalArgumentException("Chat not found")
        );
    }

    @Override
    public Chat getChat() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getDetails();

        return getChatByUser(userDetails.getUsername());
    }

    @Override
    public void createNewChat(String username) {
        User user = usersRepo.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("User not found")
        );

        if (user.getRole().equals(Role.USER)) {
            Optional<Chat> chat = chatRepo.findByUser(user);
            if (!chat.isPresent()) {
                chatRepo.save(Chat.builder()
                        .owner(user)
                        .build());
            }
        }
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepo.findAll();
    }
}
