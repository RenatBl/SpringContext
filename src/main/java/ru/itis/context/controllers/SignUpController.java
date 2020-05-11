package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.context.dto.UserDto;
import ru.itis.context.forms.UserForm;
import ru.itis.context.services.SignUpService;

import java.util.Optional;

@RestController
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public ResponseEntity signUpUser(@RequestBody UserForm form) {
        Optional<UserDto> userDto = signUpService.addNewUser(form);
        return userDto.isPresent() ? ResponseEntity.accepted().build() : ResponseEntity.status(401).build();
    }
}
