package ru.itis.context.services;

import ru.itis.context.models.Chat;
import ru.itis.context.models.Message;
import ru.itis.context.models.User;

import java.util.List;

public interface ChatMessageService {
    List<Message> getAllMessagesByChat(Chat chat);
    List<Message> getMessages(String username);
    void addNewMessage(Message message, User user, Long chatId);

}
