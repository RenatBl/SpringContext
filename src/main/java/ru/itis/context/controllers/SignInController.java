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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping
    public ResponseEntity<TokenDto> signIn(@RequestBody AuthForm form,
                                           HttpServletResponse resp) {
        return ResponseEntity.ok(signInService.signIn(form, resp));
    }
}
