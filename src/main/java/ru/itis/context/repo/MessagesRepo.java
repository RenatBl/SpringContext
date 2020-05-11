package ru.itis.context.repo;

import ru.itis.context.models.Chat;
import ru.itis.context.models.Message;

import java.util.List;

public interface MessagesRepo extends CrudRepo<Message, Long> {
    List<Message> getAllByChat(Chat chat);
}
