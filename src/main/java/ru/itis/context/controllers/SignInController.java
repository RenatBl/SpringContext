package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.context.dto.TokenDto;
import ru.itis.context.forms.AuthForm;
import ru.itis.context.services.ChatService;
import ru.itis.context.services.SignInService;

@RestController
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<TokenDto> signIn(@RequestBody AuthForm form) {
        chatService.createNewChat(form.getUserName());
        return ResponseEntity.ok(signInService.signIn(form));
    }
}
