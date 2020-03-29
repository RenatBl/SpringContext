package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.context.models.User;
import ru.itis.context.security.details.UserDetailsImpl;
import ru.itis.context.services.UsersService;

@Controller
public class UserPageController {

    @Autowired
    private UsersService service;

    @GetMapping("/user")
    public String getUserPage(Authentication authentication, Model model) {
        UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = service.getUserByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        return "userPage";
    }
}
