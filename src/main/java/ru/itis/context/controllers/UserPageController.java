package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.context.security.jwt.details.UserDetailsImpl;
import ru.itis.context.services.ChatService;
import ru.itis.context.services.UsersService;

@RestController
@RequestMapping("/user")
public class UserPageController {

    @Autowired
    private UsersService service;

    @Autowired
    private ChatService chatService;

    @GetMapping
    public String getUserPage(Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getDetails();

        model.addAttribute("user", service.getUserByUsername(userDetails.getUsername()));
        model.addAttribute("chat", chatService.getChat());
        return "userPage";
    }
}
