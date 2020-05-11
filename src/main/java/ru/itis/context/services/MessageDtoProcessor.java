package ru.itis.context.services;

import ru.itis.context.dto.ChatMessageDto;
import ru.itis.context.models.Message;

import java.util.List;

public interface MessageDtoProcessor {
    List<ChatMessageDto> processMessage(List<Message> messages);
}
