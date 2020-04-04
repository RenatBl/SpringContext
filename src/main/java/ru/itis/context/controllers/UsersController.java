package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.context.models.User;
import ru.itis.context.models.enums.Role;
import ru.itis.context.services.UsersDtoProcessor;
import ru.itis.context.services.UsersService;

import java.util.List;

@Controller
public class UsersController {
//TODO сделать RestController
    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersDtoProcessor usersDtoProcessor;

    @GetMapping("/users")
    public String getUsersPage(Model model, Authentication authentication) {
        model.addAttribute("authority", authentication
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(Role.ADMIN.name())));
        model.addAttribute("users", usersDtoProcessor.getDto(usersService.getAllUsers()));
        return "users";
    }
}
