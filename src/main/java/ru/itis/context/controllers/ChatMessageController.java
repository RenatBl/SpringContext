package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.context.dto.ChatMessageDto;
import ru.itis.context.models.Message;
import ru.itis.context.services.ChatMessageService;
import ru.itis.context.services.MessageDtoProcessor;

import java.util.List;

@RestController
public class ChatMessageController {

    @Autowired
    private MessageDtoProcessor processor;

    @Autowired
    private ChatMessageService messageService;

    @GetMapping("/messages")
    public ResponseEntity<List<ChatMessageDto>> getMessages(Model model,
                                                            @RequestParam("owner") String owner) {
        model.addAttribute("username", owner);
        List<ChatMessageDto> response = processor.processMessage(messageService.getMessages(owner));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/messages")
    public ResponseEntity sendMessage(@RequestBody Message message,
                                      @RequestParam("chatId") Long chatId) {
        messageService.addNewMessage(message, message.getUser(), chatId);
        return ResponseEntity.ok().build();
    }
}
