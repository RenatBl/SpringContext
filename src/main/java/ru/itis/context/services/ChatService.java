package ru.itis.context.services;

import ru.itis.context.models.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    Chat getChatByUser(String username);
    Chat getChat();
    void createNewChat(String username);

    List<Chat> getAllChats();
}
