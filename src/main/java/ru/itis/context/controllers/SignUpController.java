package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.context.forms.UserForm;
import ru.itis.context.services.SignUpService;

@RestController
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public ResponseEntity signUpUser(@RequestBody UserForm form) {
        signUpService.addNewUser(form);
        return ResponseEntity.accepted().build();
    }
}
