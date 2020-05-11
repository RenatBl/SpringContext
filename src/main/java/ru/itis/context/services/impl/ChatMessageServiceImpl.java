package ru.itis.context.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.context.models.Chat;
import ru.itis.context.models.Message;
import ru.itis.context.models.User;
import ru.itis.context.repo.ChatRepo;
import ru.itis.context.repo.MessagesRepo;
import ru.itis.context.services.ChatMessageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    private final List<Message> messages = new ArrayList<>();

    @Autowired
    private MessagesRepo messagesRepo;

    @Autowired
    private ChatRepo chatRepo;

    @Override
    public List<Message> getAllMessagesByChat(Chat chat) {
        return messagesRepo.getAllByChat(chat);
    }

    @Override
    public List<Message> getMessages(String username) {
        synchronized (messages) {
            if (messages.isEmpty()) {
                try {
                    messages.wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        List<Message> list = messages.stream()
                .filter(message ->
                        message.getUser().getUsername().equals(username)
                ).collect(Collectors.toList());
        System.out.println("New message: " + messages.toString());
        messages.clear();
        return list;
    }

    @Override
    public void addNewMessage(Message message, User user, Long chatId) {
        synchronized (messages) {
            Chat chat = chatRepo.find(chatId).orElseThrow(() ->
                            new IllegalArgumentException("Chat doesn't exist")
                    );
            message.setUser(user);
            message.setChat(chat);
            message.setDateTime(LocalDateTime.now());
            messages.add(message);
            messagesRepo.save(message);
            messages.notifyAll();
        }
    }
}
