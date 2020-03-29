package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.context.forms.UserForm;
import ru.itis.context.services.AuthService;

@Controller
public class SignUpController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getSignUpPage() {
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUpUser(@ModelAttribute("form") UserForm form) {
        authService.addNewUser(form);
        return "redirect:/signIn";
    }
}
