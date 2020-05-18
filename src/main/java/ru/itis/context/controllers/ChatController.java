package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.context.models.Chat;
import ru.itis.context.models.enums.Role;
import ru.itis.context.security.jwt.details.UserDetailsImpl;
import ru.itis.context.services.ChatMessageService;
import ru.itis.context.services.ChatService;
import ru.itis.context.services.MessageDtoProcessor;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private MessageDtoProcessor processor;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/chats")
    public String getAllChats(Model model) {
        List<Chat> chats =  chatService.getAllChats();
        model.addAttribute("chats", chats);
        return "chat";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/chat")
    public String getChat(Model model) {
        Chat chat =  chatService.getChat();
        model.addAttribute("chat", chat);
        model.addAttribute("messages", processor.processMessage(chatMessageService.getAllMessagesByChat(chat)));
        return "chat";
    }
}
