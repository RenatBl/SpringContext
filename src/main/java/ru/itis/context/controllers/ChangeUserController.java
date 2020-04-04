package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.context.dto.UserDto;
import ru.itis.context.services.UsersService;

@Controller
public class ChangeUserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/changeUser")
    public String getUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "changeUser";
    }

    @PostMapping("/change")
    public String changeUser(@ModelAttribute("form") UserDto form) {
        usersService.updateUser(form);
        return "redirect:/users";
    }
}
