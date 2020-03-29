package ru.itis.context.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String getRoot(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/user";
        } else {
            return "redirect:/signIn";
        }
    }
}
