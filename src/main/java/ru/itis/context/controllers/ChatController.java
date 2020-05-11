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
import ru.itis.context.security.jwt.details.UserDetailsImpl;
import ru.itis.context.services.ChatMessageService;
import ru.itis.context.services.ChatService;
import ru.itis.context.services.MessageDtoProcessor;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private MessageDtoProcessor processor;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getAdminChat(@RequestParam("user") String username,
                          Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getDetails();
        Chat chat =  chatService.getChatByUser(username);
        model.addAttribute("chat", chat);
        model.addAttribute("username", userDetails.getUsername());
        return "chat";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getChat(Model model) {
        Chat chat =  chatService.getChat();
        model.addAttribute("chat", chat);
        model.addAttribute("messages", processor.processMessage(chatMessageService.getAllMessagesByChat(chat)));
        return "chat";
    }
}
